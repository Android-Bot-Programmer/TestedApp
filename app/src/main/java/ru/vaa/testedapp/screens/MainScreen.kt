package ru.vaa.testedapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.vaa.testedapp.R
import ru.vaa.testedapp.components.HeadingTextComponent
import ru.vaa.testedapp.components.TabLayout
import ru.vaa.testedapp.data.TabItem

@Composable
fun MainScreen() {
    val tabs = listOf(
        TabItem.Cameras("Cameras"),
        TabItem.Doors("Doors")
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            HeadingTextComponent(value = stringResource(R.string.my_house))
            TabLayout(tabs)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewMainScreen() {
    MainScreen()
}