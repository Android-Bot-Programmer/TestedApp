package ru.vaa.testedapp.data

import androidx.compose.runtime.Composable
import ru.vaa.testedapp.screens.CamerasScreen
import ru.vaa.testedapp.screens.DoorsScreen

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(val tabTitle: String, val screens: ComposableFun) {
    data class Cameras(val title: String) : TabItem(tabTitle = title, screens = { CamerasScreen() })
    data class Doors(val title: String) : TabItem(tabTitle = title, screens = { DoorsScreen() })
}