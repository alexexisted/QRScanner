package com.alexadiamant.qrscannerapp.logic.contracts

import com.journeyapps.barcodescanner.ScanOptions

interface CameraContract {

    fun setOptions(): ScanOptions

}