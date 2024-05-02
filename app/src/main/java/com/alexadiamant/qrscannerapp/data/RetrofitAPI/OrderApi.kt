package com.alexadiamant.qrscannerapp.data.RetrofitAPI

import com.alexadiamant.qrscannerapp.data.dataClasses.Order
import retrofit2.http.GET
import retrofit2.http.Path

interface OrderApi {
//    @GET("https://api.mockfly.dev/mocks/060e9d53-0e78-4171-80cc-c4084031cad7/parcel")
    @GET("{endpoint}")
    suspend fun getOrder(@Path("endpoint") endpoint: String): Order
}