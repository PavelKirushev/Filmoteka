package org.example.filmoteka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.auth.data.AuthApiProvider
import com.example.auth.data.AuthRepositoryImpl
import com.example.auth.presentation.AuthViewModel
import com.example.film.data.FilmRepositoryImpl
import com.example.film.data.network.FilmApiProvider
import com.example.film.presentation.FilmViewModel
import com.example.search.data.SearchRepositoryImpl
import com.example.search.data.network.SearchApiProvider
import com.example.search.presentation.mainsearchwindow.MainSearchViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            Main()
        }

    }
}

@Composable
fun Main() {
    val filmRetrofitClient = FilmApiProvider.filmApi
    val filmViewModel = FilmViewModel(FilmRepositoryImpl(filmRetrofitClient))

    val searchRetrofitClient = SearchApiProvider.searchApi
    val searchViewModel = MainSearchViewModel(SearchRepositoryImpl(searchRetrofitClient))

    val authRetrofitClient = AuthApiProvider.authApi
    val authViewModel = AuthViewModel(AuthRepositoryImpl(authRetrofitClient))

    val controller = rememberNavController()
    Column {
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(0.9f)) {
            Navigator(
                searchViewModel = searchViewModel,
                filmViewModel = filmViewModel,
                authViewModel = authViewModel,
                controller = controller
            )
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(0.1f)) {
            BottomNavigationBar(controller = controller)
        }


    }
}


@Composable
fun BottomNavigationBar(controller: NavController) {
    NavigationBar {
        val backStackEntry by controller.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    controller.navigate(navItem.route) {
                        popUpTo(controller.graph.findStartDestination().id) {saveState = true}
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(imageVector = navItem.image,
                        contentDescription = navItem.title)
                },
                label = {
                    Text(text = navItem.title)
                }
            )
        }
    }
}

data class BarItem(
    val title: String,
    val image: ImageVector,
    val route: String
)

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Home",
            image = Icons.Filled.Home,
            route = "home"
        ),
        BarItem(
            title = "searchScreen",
            image = Icons.Filled.Face,
            route = "searchScreen"
        ),
        BarItem(
            title = "authScreen",
            image = Icons.Filled.Info,
            route = "authScreen"
        )
    )
}