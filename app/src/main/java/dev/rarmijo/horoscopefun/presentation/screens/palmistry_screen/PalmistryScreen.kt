package dev.rarmijo.horoscopefun.presentation.screens.palmistry_screen

import android.Manifest
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import dev.rarmijo.horoscopefun.R


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PalmistryScreen(modifier: Modifier = Modifier) {


    val applicationContext = LocalContext.current.applicationContext

    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    LaunchedEffect(cameraPermissionState) {
        cameraPermissionState.launchPermissionRequest()
    }


    if (cameraPermissionState.status.isGranted) {
        val controller = remember {
            LifecycleCameraController(applicationContext).apply {
                setEnabledUseCases(
                    CameraController.IMAGE_CAPTURE or
                            CameraController.VIDEO_CAPTURE
                )
            }
        }

        MyCameraPreview(
            controller = controller,
            modifier = Modifier
                .fillMaxSize()
        )
        Box(modifier = modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.palmistry),
                contentDescription = "palmistry",
                modifier = modifier.fillMaxSize()
            )

        }
    }
    else {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Permission denied", style = MaterialTheme.typography.displayMedium)
        }
    }




}

