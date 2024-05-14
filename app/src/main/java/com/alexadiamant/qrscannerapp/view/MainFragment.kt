package com.alexadiamant.qrscannerapp.view

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.alexadiamant.qrscannerapp.data.secure.HiddenLink
import com.alexadiamant.qrscannerapp.databinding.FragmentMainBinding
import com.alexadiamant.qrscannerapp.logic.implementations.CameraContractImplementation
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult


class MainFragment : Fragment() {
    //contract to work with camera's method
    private val contract = CameraContractImplementation()
    private val mainLink = HiddenLink.instance.getLink()

    //request for camera permission through registerForActivityResult API
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            isGranted: Boolean -> //if permission was given - start the camera
        if (isGranted) {
            showCamera()

        }
        else {
            //pop up message if user did not give permission to use the camera
            Toast.makeText(requireContext(), "You can not use the app without camera!", Toast.LENGTH_SHORT).show()
        }
    }

    private val scanLauncher = registerForActivityResult(ScanContract()) {
            result: ScanIntentResult ->
        run { //run the lambda fun to get the result of scanning
            if (result.contents == null) { //if there are no any content in qr show the toast with message
                Toast.makeText(requireContext(), "Try To Scan Better", Toast.LENGTH_SHORT).show()
            } else { //if there is some content - set it to the result var
                setResult(result.contents)
            }
        }
    }


    //init viewBinding
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    //set the result to navigation safe args to get it from next fragment
    //only if link is correct
    private fun setResult(linkFromCamera: String) {
        //check if link is equal to mainLink
        if (linkFromCamera.substringBeforeLast("/")
            == mainLink) {

            //use directions and navController to navigate in case link is correct
            val action = MainFragmentDirections.actionMainFragmentToResultFromQRFragment(linkFromCamera)
            view?.findNavController()?.navigate(action)
        }
        else {
            //if link is incorrect - make a toast and user is able to try scan again
            Toast.makeText(requireContext(), "Wrong link from QR", Toast.LENGTH_SHORT).show()
        }

    }

    //setup camera
    private fun showCamera() {
        //use contract with ready settings
        val options = contract.setOptions()
        scanLauncher.launch(options)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //inflate the view
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        //check if permission was given
        binding.fab.setOnClickListener {
            checkPermissionCamera(this)
        }


        return view
    }

    //method to check camera permission
    private fun checkPermissionCamera(fragment: Fragment) {
        //if permission was given -> show the camera
        if (ContextCompat.checkSelfPermission(fragment.requireContext(), android.Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED) {
            showCamera()
        }
        //use shouldShowRequestPermissionRationale to show explanation about necessary permission
        else if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
            Toast.makeText(fragment.requireContext(), "Camera permission required", Toast.LENGTH_SHORT).show()
        }
        else {
            //else launch request launcher
            requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    //destroy view after user leave it
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}