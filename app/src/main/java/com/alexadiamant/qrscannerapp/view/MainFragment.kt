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
import com.alexadiamant.qrscannerapp.databinding.FragmentMainBinding
import com.alexadiamant.qrscannerapp.logic.implementations.CameraContractImplementation
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult


class MainFragment : Fragment() {


    private val contract = CameraContractImplementation()



    //request for camera permission through registerForActivityResult API
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            isGranted: Boolean -> //if permission was given - start the camera
        if (isGranted) {
            showCamera()

        } //overwise show toast that access denied
        else {
            TODO("tell that it is necessary")
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
    private fun setResult(linkFromCamera: String) {
        val action = MainFragmentDirections.actionMainFragmentToResultFromQRFragment(linkFromCamera)

        view?.findNavController()?.navigate(action)
    }

    //setup camera
    private fun showCamera() {
        val options = contract.setOptions()
        scanLauncher.launch(options)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        //check if permission was given
        binding.fab.setOnClickListener {
            checkPermissionCamera(this)
        }


        return view
    }

    private fun checkPermissionCamera(fragment: Fragment) {
        if (ContextCompat.checkSelfPermission(fragment.requireContext(), android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            showCamera()
        }
        else if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
            Toast.makeText(fragment.requireContext(), "camera permission required", Toast.LENGTH_SHORT).show()
        }
        else {
            requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}