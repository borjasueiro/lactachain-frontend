package com.fic.muei.lactachain.ui

import android.os.Bundle
import android.util.Size
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.fic.muei.lactachain.databinding.FragmentScanqrBinding
import com.fic.muei.lactachain.ui.*
import com.fic.muei.lactachain.utils.QrCodeAnalyzer
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ScanQRFragment: Fragment() {
    private lateinit var binding: FragmentScanqrBinding
    private val viewModel: LactachainViewModel by activityViewModels()

    private lateinit var cameraProviderFuture : ListenableFuture<ProcessCameraProvider>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentScanqrBinding.inflate(layoutInflater)
        cameraProviderFuture = ProcessCameraProvider.getInstance(context!!)
        val preview = binding.previewView
        val imageAnalysis = ImageAnalysis
            .Builder()
            .setTargetResolution(Size(preview.width,preview.height))
            .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
            .build()
        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(context!!),QrCodeAnalyzer{result ->
            viewModel.getTraceDataById(result)
            imageAnalysis.clearAnalyzer()
        }

        )
        cameraProviderFuture.addListener(Runnable {
            val cameraProvider = cameraProviderFuture.get()
            bindPreview(preview,cameraProvider,imageAnalysis)
        }, ContextCompat.getMainExecutor(context!!))


      lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.qrState.collectLatest {uiState ->
                    when (uiState) {
                        is QrUIState.Success -> {
                            view?.findNavController()?.navigate(ScanQRFragmentDirections.actionScanQRToTraceData())
                        }
                        is QrUIState.Error -> Toast.makeText(context,uiState.exception.message, Toast.LENGTH_SHORT).show()
                        is QrUIState.Empty -> {}
                    }
                }
            }
        }
        return binding.root
    }

    fun bindPreview(previewView: PreviewView, cameraProvider : ProcessCameraProvider,imageAnalysis: ImageAnalysis) {
        var preview : Preview = Preview.Builder()
            .build()

        var cameraSelector : CameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()

        preview.setSurfaceProvider(previewView.getSurfaceProvider())

        var camera = cameraProvider.bindToLifecycle(this as LifecycleOwner, cameraSelector, preview, imageAnalysis)
    }

    companion object{
        @JvmStatic
        fun newInstance() =
            SiloFragment()
    }

}