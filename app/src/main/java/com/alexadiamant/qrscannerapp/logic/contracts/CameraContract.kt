package com.alexadiamant.qrscannerapp.logic.contracts
import android.content.Context
import com.journeyapps.barcodescanner.ScanOptions

interface CameraContract {

    fun setOptions(): ScanOptions

}