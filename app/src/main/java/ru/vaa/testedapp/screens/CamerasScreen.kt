package ru.vaa.testedapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.vaa.testedapp.data.cameras.CamerasViewModel

@Composable
fun CamerasScreen(camerasViewModel: CamerasViewModel = viewModel()) {
    camerasViewModel.getCameras()
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Cameras Screen")
    }
}