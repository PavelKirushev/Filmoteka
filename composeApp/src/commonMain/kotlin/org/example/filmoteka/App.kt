//package org.example.filmoteka
//
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import org.jetbrains.compose.resources.painterResource
//import org.jetbrains.compose.ui.tooling.preview.Preview
//import filmoteka.composeapp.generated.resources.Res
//import filmoteka.composeapp.generated.resources.compose_multiplatform
//
//expect fun getPlatformName(): String
//
//@Composable
//expect fun AppTheme(
//    darkTheme: Boolean,
//    content: @Composable () -> Unit
//)
//
//@Composable
//fun App() {
//    val themeViewModel = remember { ThemeViewModel() }
//    val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()
//
//    AppTheme(darkTheme = isDarkTheme) {
//        MainContent(themeViewModel)
//    }
//}
//
//@Composable
//fun MainContent(themeViewModel: ThemeViewModel) {
//    val filmViewModel = remember { FilmViewModel(FilmRepositoryImpl(getFilmApi())) }
//    val searchViewModel = remember { MainSearchViewModel(SearchRepositoryImpl(getSearchApi())) }
//    val authViewModel = remember { AuthViewModel(AuthRepositoryImpl(getAuthApi())) }
//
//    val controller = rememberNavController()
//
//    Column(
//        modifier = Modifier.background(MaterialTheme.colorScheme.background)
//    ) {
//        Box(modifier = Modifier
//            .fillMaxWidth()
//            .weight(1f)) {
//            Navigator(
//                searchViewModel = searchViewModel,
//                filmViewModel = filmViewModel,
//                authViewModel = authViewModel,
//                controller = controller,
//                themeViewModel = themeViewModel
//            )
//        }
//        BottomNavigationBar(controller = controller)
//    }
//}
//
//expect fun getFilmApi(): FilmApi
//expect fun getSearchApi(): SearchApi
//expect fun getAuthApi(): AuthApi