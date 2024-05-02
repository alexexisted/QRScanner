package com.alexadiamant.qrscannerapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.alexadiamant.qrscannerapp.data.RetrofitAPI.OrderApi
import com.alexadiamant.qrscannerapp.databinding.FragmentResultFromQrBinding
import com.alexadiamant.qrscannerapp.logic.contracts.LinksContract
import com.alexadiamant.qrscannerapp.logic.implementations.LinksContractImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

        val retrofit = Retrofit.Builder()
//            .baseUrl("{$link}") //runtime error occurs
            .baseUrl("https://api.mockfly.dev/mocks/060e9d53-0e78-4171-80cc-c4084031cad7/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val orderApi = retrofit.create(OrderApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val order = orderApi.getOrder(endpoint)

            view?.post{
                binding.testTV.text = order.customer.toString()
            }
        }
    }
}