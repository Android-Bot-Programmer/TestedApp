package ru.vaa.testedapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import ru.vaa.testedapp.R
import ru.vaa.testedapp.components.CameraCardComponent
import ru.vaa.testedapp.components.NormalTextComponent
import ru.vaa.testedapp.data.cameras.CamerasViewModel
import ru.vaa.testedapp.repository.model.Camera

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CamerasScreen(camerasViewModel: CamerasViewModel = hiltViewModel()) {
    val listCam = camerasViewModel.listCameras.observeAsState()
    val refreshScope = rememberCoroutineScope()
    fun refresh() = refreshScope.launch { camerasViewModel.getCameras() }
    val state = rememberPullRefreshState(camerasViewModel.loadProgress.value, ::refresh)

    Column(modifier = Modifier.fillMaxSize()) {

        NormalTextComponent(value = stringResource(R.string.lounge))

        Box(modifier = Modifier.pullRefresh(state)) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                listCam.value?.let {
                    items(it) { item: Camera ->
                        CameraCardComponent(item = item)
                    }
                }
            }

            PullRefreshIndicator(
                camerasViewModel.loadProgress.value,
                state,
                Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCamerasScreen() {
    CamerasScreen()
}