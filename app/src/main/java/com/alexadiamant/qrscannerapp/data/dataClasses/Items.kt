package com.alexadiamant.qrscannerapp.data.dataClasses

data class Items(
    val itemId: String,
    val offerId: String,
    val itemName: String,
    val price: Int, //Maybe float but IDK in JSON it is Int
    val finalPrice: Int,
    val quantity: Int,
    val internalHWB: String,
    val externalHWB: String,
)
