package com.alexadiamant.qrscannerapp.data.dataClasses

data class Access(
    val detachedHouse: String,
    val entrance: String,
    val floor: Short,
    val intercom: String,
    val cargoElevator: Boolean,
    val comment: String,
    val apartment: String

)
