package org.example.filmoteka.phone

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SetSystemBarsColor(isDarkTheme: Boolean) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isDarkTheme
    val statusBarColor = if (isDarkTheme) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.primaryContainer

    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = useDarkIcons
        )
        systemUiController.setNavigationBarColor(
            color = statusBarColor,
            darkIcons = useDarkIcons
        )
    }
}
