package org.example.filmoteka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
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
    val controller = rememberNavController()

    Surface {
        Navigator(
            searchViewModel = searchViewModel,
            filmViewModel = filmViewModel,
            controller = controller
            )
    }
}