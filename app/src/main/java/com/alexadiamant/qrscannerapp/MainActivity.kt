package com.alexadiamant.qrscannerapp

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.alexadiamant.qrscannerapp.databinding.ActivityMainBinding
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions

class MainActivity : AppCompatActivity() {
//
//    private val contract = CameraContractImplementation()
//
//    //request for camera permission through registerForActivityResult API
//    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
//        isGranted: Boolean -> //if permission was given - start the camera
//        if (isGranted) {
//           showCamera()
//
//        } //overwise show toast that access denied
//        else {
//            TODO("tell that it is necessary")
//        }
//    }

//    private val scanLauncher = registerForActivityResult(ScanContract()) {
//         result: ScanIntentResult ->
//        run { //run the lambda fun to get the result of scanning
//            if (result.contents == null) { //if there are no any content in qr show the toast with message
//                Toast.makeText(this, "Try To Scan Better", Toast.LENGTH_SHORT).show()
//            } else { //if there is some content - set it to the result var
//                setResult(result.contents)
//                //TODO("set result from qr(link) to some method of retrofit")
//            }
//        }
//    }

//    private lateinit var binding: ActivityMainBinding //init viewBinding
//    // TODO("remove lateinit and use it with null safety")
//
//    //method to set the result to textView in XML
//    private fun setResult(string: String) {
//        binding.TVtextResult.text = string
//        //TODO("paste parsed info to TextView")
//    }

    //setup camera
//    private fun showCamera() {
////        val options = ScanOptions()
////        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
////        options.setPrompt("Scan QR code")
////        options.setCameraId(0)
////        options.setBeepEnabled(false)
////        options.setBarcodeImageEnabled(true)
////        options.setOrientationLocked(false)
//        val options = contract.setOptions()
//        scanLauncher.launch(options)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        initBinding()
//        initViews()

    }

//    private fun initViews() {
//        binding.fab.setOnClickListener {
//            checkPermissionCamera(this)
//        }
//    }

//    private fun checkPermissionCamera(context: Context) {
//        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//            showCamera()
//        }
//        else if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
//            Toast.makeText(context, "camera permission required", Toast.LENGTH_SHORT).show()
//        }
//        else {
//            requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
//        }
//    }
//
//    //inflate activity with layout
//    private fun initBinding() {
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//    }


}