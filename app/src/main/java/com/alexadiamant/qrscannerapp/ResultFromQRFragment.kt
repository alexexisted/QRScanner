package com.alexadiamant.qrscannerapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.alexadiamant.qrscannerapp.data.RetrofitAPI.OrderApi
import com.alexadiamant.qrscannerapp.data.dataClasses.Items
import com.alexadiamant.qrscannerapp.data.dataClasses.Order
import com.alexadiamant.qrscannerapp.databinding.FragmentResultFromQrBinding
import com.alexadiamant.qrscannerapp.logic.implementations.LinksContractImpl
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ResultFromQRFragment : Fragment() {

    //init view binding
    private var _binding: FragmentResultFromQrBinding? = null
    private val binding get() = _binding!!

    private val args: ResultFromQRFragmentArgs by navArgs()

    private val linkContract = LinksContractImpl()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentResultFromQrBinding.inflate(inflater, container, false)
        val view = binding.root

        //scanned link
        val link = args.QRLink.toString()

        val endpoint = linkContract.getEndpoint(link)

        runRetrofit(endpoint)


        return view
    }

    private fun runRetrofit(endpoint: String) {

        val TAG = "ResultFragment"

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
//            .baseUrl("{$link}") //runtime error occurs
            .baseUrl("https://api.mockfly.dev/mocks/060e9d53-0e78-4171-80cc-c4084031cad7/").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val orderApi = retrofit.create(OrderApi::class.java)

        //create coroutine
        CoroutineScope(Dispatchers.IO).launch {
            //async request to get main info about order
            val orderDeferred: Deferred<Order> = async {
                orderApi.getOrder(endpoint)
            }
            //async request to get list of ordered items
//            val orderedItemsDeferred: Deferred<OrderedItems> = async {
//                orderApi.getOrderedItems(endpoint)
//            }
//            Log.i(TAG, "$orderedItemsDeferred")

            //async request to get ordered item
            val orderedItemsDeferred: Deferred<Items> = async {
                orderApi.getItems(endpoint)
            }
            Log.i(TAG, "$orderedItemsDeferred")


            //await result
            val order: Order = orderDeferred.await()
//            val orderedItems: OrderedItems = orderedItemsDeferred.await()
            val orderedItems: Items = orderedItemsDeferred.await()
            Log.i(TAG, "$orderedItems")

            //ui thread to set info from request
            withContext(Dispatchers.Main) {
                binding.testTV.text = order.customer.toString()
//                binding.TVOrderedItems.text =  orderedItems.toString()
                binding.TVOrderedItems.text = orderedItems.toString()
            }
        }
    }
}