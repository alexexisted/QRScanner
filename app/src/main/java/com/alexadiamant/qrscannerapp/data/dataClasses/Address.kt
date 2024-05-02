package com.alexadiamant.qrscannerapp.data.dataClasses

data class Address(
    val source: String,
    val postalCode: String,
    val fias: Fias,
    val geo: Geo,
    val access: Access,
    val regionKladrId: String,
    val house: String,
    val block: String?,
    val flat: String,
    val regionWithType: String,
    val cityWithType: String,
    val cityArea: String,
    val streetWithType: String,
)


