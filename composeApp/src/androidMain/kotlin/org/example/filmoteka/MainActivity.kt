package org.example.filmoteka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.auth.data.network.AuthApiProvider
import com.example.auth.data.AuthRepositoryImpl
import com.example.auth.presentation.phone.AuthViewModel
import com.example.auth.data.FilmRepositoryImpl
import com.example.auth.data.network.FilmApiProvider
import com.example.search.data.SearchRepositoryImpl
import com.example.search.data.network.SearchApiProvider
import com.example.search.presentation.phone.mainsearchwindow.MainSearchViewModel
import com.example.ui.ThemeViewModel
import org.example.filmoteka.phone.BottomNavigationBar
import org.example.filmoteka.phone.SetSystemBarsColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val themeViewModel = ThemeViewModel()
        val isDarkTheme by themeViewModel.isDarkTheme

        setContent{
            AppTheme (darkTheme = isDarkTheme) {
                SetSystemBarsColor(isDarkTheme)
                Main(themeViewModel)
            }

        }

    }
}

@Composable
fun Main(themeViewModel: ThemeViewModel) {
    val filmRetrofitClient = FilmApiProvider.filmApi
    val filmViewModel = FilmViewModel(FilmRepositoryImpl(filmRetrofitClient))

    val searchRetrofitClient = SearchApiProvider.searchApi
    val searchViewModel = MainSearchViewModel(SearchRepositoryImpl(searchRetrofitClient))

    val authRetrofitClient = AuthApiProvider.authApi
    val authViewModel = AuthViewModel(AuthRepositoryImpl(authRetrofitClient))


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
                controller = controller,
                themeViewModel = themeViewModel
            )
        }
        BottomNavigationBar(controller = controller)
    }
}