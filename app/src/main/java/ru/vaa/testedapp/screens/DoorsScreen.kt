package ru.vaa.testedapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.vaa.testedapp.data.doors.DoorsViewModel

@Composable
fun DoorsScreen(doorsViewModel: DoorsViewModel = viewModel()) {
    doorsViewModel.getDoors()
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Doors Screen")
    }
}