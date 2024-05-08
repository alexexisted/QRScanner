package com.alexadiamant.qrscannerapp.logic.implementations

import android.content.Context
import com.journeyapps.barcodescanner.ScanOptions
import com.alexadiamant.qrscannerapp.logic.contracts.CameraContract

class CameraContractImplementation: CameraContract {

    //set up options for qrCode framework
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
}