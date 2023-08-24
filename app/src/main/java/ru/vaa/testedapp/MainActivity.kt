package ru.vaa.testedapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.vaa.testedapp.screens.MainScreen
import ru.vaa.testedapp.ui.theme.TestedAppTheme

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
