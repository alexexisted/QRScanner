package com.alexadiamant.qrscannerapp.data.RetrofitAPI

import com.alexadiamant.qrscannerapp.data.dataClasses.Items
import com.alexadiamant.qrscannerapp.data.dataClasses.Order
import com.alexadiamant.qrscannerapp.data.dataClasses.OrderedItems
import retrofit2.http.GET
import retrofit2.http.Path

interface OrderApi {

    //get request to get order info from json
    @GET("{endpoint}")
    suspend fun getOrder(@Path("endpoint") endpoint: String): Order

    //get request to get order list of ordered items from json
    @GET("{endpoint}")
    suspend fun getOrderedItems(@Path("endpoint") endpoint: String): OrderedItems
}