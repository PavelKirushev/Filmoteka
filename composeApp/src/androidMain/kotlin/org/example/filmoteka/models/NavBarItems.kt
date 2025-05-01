package org.example.filmoteka.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search


object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Главное",
            image = Icons.Filled.Home,
            route = "homeScreen"
        ),
        BarItem(
            title = "Поиск",
            image = Icons.Filled.Search,
            route = "searchScreen"
        ),
        BarItem(
            title = "Профиль",
            image = Icons.Filled.AccountCircle,
            route = "authScreen"
        )
    )
}