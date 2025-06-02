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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.auth.data.network.AuthApiProvider
import com.example.auth.data.AuthRepositoryImpl
import com.example.auth.presentation.AuthViewModel
import com.example.compose.AppTheme
import com.example.film.data.FilmRepositoryImpl
import com.example.film.data.network.FilmApiProvider
import com.example.film.presentation.FilmViewModel
import com.example.search.data.SearchRepositoryImpl
import com.example.search.data.network.SearchApiProvider
import com.example.search.presentation.SearchViewModel
import com.example.ui.ThemeViewModel
import org.example.filmoteka.phone.BottomNavigationBar
import org.example.filmoteka.phone.SetSystemBarsColor
import org.jetbrains.compose.resources.getString

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val themeViewModel = ThemeViewModel()
        val isDarkTheme by themeViewModel.isDarkTheme

        setContent{
            AppTheme (darkTheme = isDarkTheme) {
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