package org.example.filmoteka

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.auth.data.AuthRepositoryImpl
import com.example.auth.data.network.AuthApiProvider
import com.example.auth.presentation.AuthViewModel
import com.example.compose.AppTheme
import com.example.film.data.FilmRepositoryImpl
import com.example.film.data.network.FilmApiProvider
import com.example.film.presentation.FilmViewModel
import com.example.home.data.HomeRepositoryImpl
import com.example.home.data.network.HomeApiProvider
import com.example.home.presentation.HomeViewModel
import com.example.search.data.SearchRepositoryImpl
import com.example.search.data.network.SearchApiProvider
import com.example.search.presentation.SearchViewModel
import com.example.ui.ThemeViewModel
import org.example.filmoteka.phone.BottomNavigationBar
import org.example.filmoteka.phone.SetSystemBarsColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val themeViewModel: ThemeViewModel = viewModel()
            val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()

            AppTheme(darkTheme = isDarkTheme) {
                SetSystemBarsColor(isDarkTheme)
                Main(themeViewModel, this)
            }
        }
    }
}

@Composable
fun Main(themeViewModel: ThemeViewModel, context: Context) {
    val apiKey = context.getString(R.string.API_KEY)

    val filmViewModel = FilmViewModel(FilmRepositoryImpl(FilmApiProvider.create(apiKey)))
    val searchViewModel = SearchViewModel(SearchRepositoryImpl(SearchApiProvider.create(apiKey)))
    val authViewModel = AuthViewModel(AuthRepositoryImpl(AuthApiProvider.create()))
    val homeViewModel = HomeViewModel(HomeRepositoryImpl(HomeApiProvider.create(apiKey)))

    val controller = rememberNavController()
    Column (
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ){
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)) {
            Navigator(
                searchViewModel = searchViewModel,
                filmViewModel = filmViewModel,
                authViewModel = authViewModel,
                homeViewModel = homeViewModel,
                controller = controller,
                themeViewModel = themeViewModel
            )
        }
        BottomNavigationBar(controller = controller)
    }
}