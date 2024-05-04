package com.alexadiamant.qrscannerapp.data.RetrofitAPI

import com.alexadiamant.qrscannerapp.data.dataClasses.Items
import com.alexadiamant.qrscannerapp.data.dataClasses.Order
import com.alexadiamant.qrscannerapp.data.dataClasses.OrderedItems
import retrofit2.http.GET
import retrofit2.http.Path

interface OrderApi {
//    @GET("https://api.mockfly.dev/mocks/060e9d53-0e78-4171-80cc-c4084031cad7/parcel")

    //get request to get order info from json
    @GET("{endpoint}")
    suspend fun getOrder(@Path("endpoint") endpoint: String): Order

    //get request to get order list of ordered items from json
    @GET("{endpoint}")
    suspend fun getOrderedItems(@Path("endpoint") endpoint: String): OrderedItems

    //get request to get ordered item from json
    @GET("{endpoint}")
    suspend fun getItems(@Path("endpoint") endpoint: String): Items

}