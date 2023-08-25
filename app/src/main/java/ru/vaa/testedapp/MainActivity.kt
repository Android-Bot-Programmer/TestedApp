package ru.vaa.testedapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ru.vaa.testedapp.screens.MainScreen
import ru.vaa.testedapp.ui.theme.TestedAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestedAppTheme {
                MainScreen()
            }
        }
    }
}
