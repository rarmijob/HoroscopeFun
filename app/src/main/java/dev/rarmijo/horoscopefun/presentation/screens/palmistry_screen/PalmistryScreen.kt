package dev.rarmijo.horoscopefun.presentation.screens.palmistry_screen

import android.Manifest
import android.content.Context
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import dev.rarmijo.horoscopefun.R


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PalmistryScreen(modifier: Modifier = Modifier) {
    val applicationContext = LocalContext.current.applicationContext
    val alreadyAnswered = remember { mutableStateOf(false) }
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA) {
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
            alreadyAnswered.value -> {
                PermissionMessage(modifier.align(Alignment.TopCenter),
                    stringResource(R.string.permision_denied))
            }
            else -> {
               PermissionMessage(modifier.align(Alignment.TopCenter),
                   stringResource(R.string.please_grant_permission))
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


@Composable
fun PermissionMessage(modifier: Modifier, message: String) {
    Box(
        modifier = modifier
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center
        )
    }
}