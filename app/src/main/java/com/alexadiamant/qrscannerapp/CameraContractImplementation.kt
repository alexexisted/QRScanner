package com.alexadiamant.qrscannerapp

import android.content.Context
import com.journeyapps.barcodescanner.ScanOptions
import androidx.activity.result.contract.ActivityResultContracts

class CameraContractImplementation: CameraContract {

    override fun setOptions(): ScanOptions {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Scan QR code")
        options.setCameraId(0)
        options.setBeepEnabled(false)
        options.setBarcodeImageEnabled(true)
        options.setOrientationLocked(false)

        return options
    }

    override fun setResult(info: String) {
        TODO("Not yet implemented")
    }

    override fun checkPermissionCamera(context: Context) {
        TODO("Not yet implemented")
    }
}