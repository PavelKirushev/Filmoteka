package com.example.film.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import androidx.navigation.NavController
import com.example.film.data.FilmRepositoryImpl
import com.example.film.data.network.FilmApiProvider
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val filmRetrofitClient = FilmApiProvider().create("1c4b2b3b-2536-40a4-a5c8-00666a9418c3")
    val filmViewModel = FilmViewModel(FilmRepositoryImpl(filmRetrofitClient))

    ComposeViewport(document.body!!) {
//        FilmScreen(filmViewModel, 12412, rememberNavController())

    }
}

@Composable
fun FilmScreen(viewModel: FilmViewModel, id: Int, controller: NavController) {
    val filmState by viewModel.film.collectAsState()

    LaunchedEffect(id) {
        viewModel.loadFilm(id)
    }

    Text(text = viewModel.film.value?.nameRu ?: "123124124")
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//        LazyColumn {
////            item {
////                TopAppBar(
////                    modifier = TODO(),
////                    navigationIcon = TODO(),
////                    actions = {
////                        IconButton(
////                            {
////                                controller.navigateUp()
////                            }
////                        ) {
////                            Icon(
////                                Icons.
////                            )
////                        }
////                    },
////                    expandedHeight = TODO(),
////                    windowInsets = TODO(),
////                    colors = TODO(),
////                    scrollBehavior = TODO()
////                )
//////            }
////            item {
////                AsyncImage(
////                    model = filmState?.imageUrl,
////                    contentDescription = "Movie Poster",
////                    modifier = Modifier
////                        .fillMaxWidth(0.6f),
////                    contentScale = ContentScale.Crop,
//////                    alignment = Al,
////                )
////            }
//            item {
//                filmState?.let { film ->
//                    Text(
//                        text = film.nameRu ?: "Название недоступно",
//                        style = MaterialTheme.typography.headlineMedium,
//                        modifier = Modifier.padding(bottom = 8.dp)
//                    )
//
//                    film.nameOriginal?.let { originalName ->
//                        Text(
//                            text = originalName,
//                            style = MaterialTheme.typography.titleMedium,
//                            modifier = Modifier.padding(bottom = 8.dp)
//                        )
//                    }
//
//                    Row(modifier = Modifier.padding(bottom = 8.dp)) {
//                        film.rating.let {
//                            Text(
//                                text = "Рейтинг: $it",
//                                style = MaterialTheme.typography.bodyLarge,
//                                modifier = Modifier.padding(end = 16.dp)
//                            )
//                        }
//                        film.year?.let {
//                            Text(
//                                text = "Год: $it",
//                                style = MaterialTheme.typography.bodyLarge
//                            )
//                        }
//                    }
//
//                    film.countries?.takeIf { it.isNotEmpty() }?.let { countries ->
//                        Text(
//                            text = "Страны: ${countries.joinToString()}",
//                            style = MaterialTheme.typography.bodyLarge,
//                            modifier = Modifier.padding(bottom = 8.dp)
//                        )
//                    }
//
//                    Text(
//                        text = film.description ?: "Описание недоступно",
//                        style = MaterialTheme.typography.bodyLarge
//                    )
//                } ?: run {
//                    Text(
//                        text = "Данные не загружены",
//                        style = MaterialTheme.typography.bodyLarge
//                    )
//                }
//            }
//        }
//    }
}