package com.example.momo.fragment

import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.example.momo.databinding.FragmentQrcodeBinding

class QrCodeFragment : BaseFragment<FragmentQrcodeBinding>() {
    override fun getViewBinding(): FragmentQrcodeBinding {
        return FragmentQrcodeBinding.inflate(layoutInflater)
    }

    private var imageCapture: ImageCapture? = null

    companion object {
        var close: MutableLiveData<Boolean> = MutableLiveData()
    }

    override fun setup() {

        close.value = false



        binding.ivClose.setOnClickListener {
            close.value = true
        }
        binding.scan.setOnClickListener {
            close.value = true
        }
    }

    override fun onStart() {
        super.onStart()
        startCamera()
    }

    private fun startCamera() {

        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener(Runnable {

            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )

            } catch (exc: Exception) {
                Log.e("====", "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }

}
