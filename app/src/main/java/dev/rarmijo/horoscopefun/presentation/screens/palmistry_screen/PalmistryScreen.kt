package dev.rarmijo.horoscopefun.presentation.screens.palmistry_screen

import android.Manifest
import android.content.Context
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import dev.rarmijo.horoscopefun.R


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PalmistryScreen(modifier: Modifier = Modifier) {
    val applicationContext = LocalContext.current.applicationContext
    val alreadyAnswered = remember { mutableStateOf(false)}
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA){
        alreadyAnswered.value = true
    }


    LaunchedEffect(cameraPermissionState.status) {
        if (cameraPermissionState.status.isGranted.not()) {
            cameraPermissionState.launchPermissionRequest()

        }
    }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            cameraPermissionState.status.isGranted -> {
                CameraContent(applicationContext)
            }
            else -> {
                if(alreadyAnswered.value) {
                    Text(
                        text = "Permission denied. Please enable camera permission in the app settings.",
                        style = MaterialTheme.typography.displayMedium
                    )
                } else {
                    Text(
                        text = "Camera permission is needed to use this feature. Please grant the permission.",
                        style = MaterialTheme.typography.displayMedium
                    )
                }
            }
        }
    }
}

@Composable
fun CameraContent(context: Context) {
    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_CAPTURE or CameraController.VIDEO_CAPTURE)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        MyCameraPreview(
            controller = controller,
            modifier = Modifier.fillMaxSize()
        )
        Image(
            painter = painterResource(id = R.drawable.palmistry),
            contentDescription = "palmistry",
            modifier = Modifier.fillMaxSize()
        )
    }
}