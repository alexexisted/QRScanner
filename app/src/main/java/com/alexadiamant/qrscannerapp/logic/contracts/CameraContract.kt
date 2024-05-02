package com.alexadiamant.qrscannerapp.logic.contracts
import android.content.Context
import com.journeyapps.barcodescanner.ScanOptions

interface CameraContract {

    fun setOptions(): ScanOptions

    fun setResult(info: String)

    fun checkPermissionCamera(context: Context)

}