package ru.vaa.testedapp.screens

import androidx.compose.foundation.layout.Box
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
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import ru.vaa.testedapp.components.DoorCardComponent
import ru.vaa.testedapp.data.doors.DoorsViewModel
import ru.vaa.testedapp.repository.model.Door

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DoorsScreen(doorsViewModel: DoorsViewModel = hiltViewModel()) {
    val listDoors = doorsViewModel.listDoors.observeAsState()
    val refreshScope = rememberCoroutineScope()
    fun refresh() = refreshScope.launch { doorsViewModel.clearAll() }
    val state = rememberPullRefreshState(doorsViewModel.loadProgress.value, ::refresh)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(state),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            listDoors.value?.let {
                items(it) {item: Door ->
                    DoorCardComponent(item = item) { name ->
                        doorsViewModel.updateDoorName(item._id, name)
                    }
                }
            }
        }
        PullRefreshIndicator(
            doorsViewModel.loadProgress.value,
            state,
            Modifier.align(Alignment.TopCenter)
        )
    }
}