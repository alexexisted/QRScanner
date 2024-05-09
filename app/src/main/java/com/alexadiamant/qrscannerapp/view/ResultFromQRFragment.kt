package com.alexadiamant.qrscannerapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexadiamant.qrscannerapp.data.RetrofitAPI.OrderApi
import com.alexadiamant.qrscannerapp.data.dataClasses.Order
import com.alexadiamant.qrscannerapp.data.dataClasses.OrderedItems
import com.alexadiamant.qrscannerapp.databinding.FragmentResultFromQrBinding
import com.alexadiamant.qrscannerapp.logic.implementations.LinksContractImpl
import com.alexadiamant.qrscannerapp.view.adapters.ItemsAdapter
import com.alexadiamant.qrscannerapp.view.adapters.OrderInfoAdapter

import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ResultFromQRFragment : Fragment() {

    //init view binding
    private var _binding: FragmentResultFromQrBinding? = null
    private val binding get() = _binding!!

    //lateinit of adapters
    private lateinit var itemsAdapter: ItemsAdapter
    private lateinit var orderAdapter: OrderInfoAdapter

    //save args
    private val args: ResultFromQRFragmentArgs by navArgs()

    //contract to get endpoint from link
    private val linkContract = LinksContractImpl()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //binding of fragment to display results
        _binding = FragmentResultFromQrBinding.inflate(inflater, container, false)
        val view = binding.root

        //adapter for item's recycler view
        itemsAdapter = ItemsAdapter()
        binding.RCViewItems.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, //horizontal displaying of ordered items
            false)
        binding.RCViewItems.adapter = itemsAdapter

        //adapter for main order's info recycler view
        orderAdapter = OrderInfoAdapter()
        binding.RCViewOrder.layoutManager = LinearLayoutManager(requireContext())
        binding.RCViewOrder.adapter = orderAdapter

        //get the scanned link from safe args
        val link = args.QRLink.toString()

        //put the link to method in contract to get link without endpoint
        val cutLink = linkContract.getLink(link)

        //put the link to method in contract to get endpoint
        val endpoint = linkContract.getEndpoint(link)

        //run retrofit method to work with net
        runRetrofit(endpoint, cutLink)

        return view
    }

    private fun runRetrofit(endpoint: String, link: String) {

        //add interceptor to get logs
        val interceptor = HttpLoggingInterceptor()

        //set up level of logs(in my case i want to see body only)
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        //creating the client through OkHttp
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
//            .baseUrl("{$link}") //runtime error occurs
            //add client to retrofit instance
//            .baseUrl("https://api.mockfly.dev/mocks/060e9d53-0e78-4171-80cc-c4084031cad7/").client(client)
            .baseUrl(link)
            .addConverterFactory(GsonConverterFactory.create()).build()

        //creating implementation of endpoints
        val orderApi = retrofit.create(OrderApi::class.java)

        //launch coroutine to get request
        CoroutineScope(Dispatchers.IO).launch {
            //async request to get main info about order
            val orderDeferred: Deferred<Order> = async {
                orderApi.getOrder(endpoint)
            }
            //async request to get list of ordered items
            val orderedItemsDeferred: Deferred<OrderedItems> = async {
                orderApi.getOrderedItems(endpoint)
            }

            //await result
            val order: Order = orderDeferred.await() //get main info about the order
            val orderedItems: OrderedItems = orderedItemsDeferred.await() //get object with list of ordered items
            val finalListOfOrder = listOf(order)    //make list of order

            //ui thread to set info from request to view
            withContext(Dispatchers.Main) {
                //setting data about order in general to tableView
                orderAdapter.submitList(finalListOfOrder)

                //setting data about ordered items in cardView
                itemsAdapter.submitList(orderedItems.items)

            }
        }
    }
}




