package org.example.filmoteka.phone

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SetSystemBarsColor() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()
    val statusBarColor = MaterialTheme.colorScheme.onPrimaryContainer
    val navigationBarColor = MaterialTheme.colorScheme.primaryContainer

    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = useDarkIcons
        )
        systemUiController.setNavigationBarColor(
            color = navigationBarColor,
            darkIcons = useDarkIcons
        )
    }
}