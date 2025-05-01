package com.example.auth.presentation.phone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui_core.theme.ThemeViewModel

@Composable
fun AuthScreen(viewModel: AuthViewModel, themeViewModel: ThemeViewModel) {
    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column (modifier = Modifier
        .padding(20.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextField(text = login, placeholderText = "логин")
        TextField(text = password, placeholderText = "пароль", isPassword = true)

        Button(
            onClick = {
                viewModel.getUser(login.value, password.value)
            }
        ) {
            Text("Войти", fontSize = 15.sp)
        }
        Button(
            onClick = {
                viewModel.getUser(login.value, password.value)
            }
        ) {
            Text("Регистрация", fontSize = 15.sp)
        }

        Button(
            onClick = {
                themeViewModel.toggleTheme()
            }
        ) {
            Text("тема", fontSize = 15.sp)
        }
    }
}