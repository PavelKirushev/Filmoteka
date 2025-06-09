package com.example.auth.presentation.phone.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.auth.domain.models.UserInfo
import com.example.auth.presentation.AuthAction
import com.example.auth.presentation.AuthViewModel
import com.example.auth.presentation.AuthViewState
import com.example.auth.presentation.phone.register.RegisterDialog

@Composable
fun LoginWindow(authViewModel: AuthViewModel, authViewState: AuthViewState) {

    val showDialog = authViewState is AuthViewState.ShowRegisterDialog
    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(0.75f)) {
            Text(
                text = "Вход",
                style = MaterialTheme.typography.titleLarge.copy(MaterialTheme.colorScheme.onPrimaryContainer)
            )
        }
        TextField(text = login, placeholderText = "Логин")
        TextField(text = password, placeholderText = "Пароль", isPassword = true)

        Button(
            onClick = { authViewModel.submitAction(AuthAction.LoginButtonClicked(UserInfo(login = login.value, password = password.value))) },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                contentColor = MaterialTheme.colorScheme.primaryContainer,
            )
        ) {
            Text("Войти", style = MaterialTheme.typography.titleMedium)
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Text(
                "Нет аккаунта? ",
                style = MaterialTheme.typography.bodyMedium.copy(MaterialTheme.colorScheme.onBackground)
            )
            Text(
                "Зарегистрироваться",
                style = MaterialTheme.typography.titleSmall.copy(MaterialTheme.colorScheme.onPrimaryContainer),
                modifier = Modifier.clickable { authViewModel.submitAction(AuthAction.ShowRegisterDialog) })
        }
        if (showDialog) {
            RegisterDialog(authViewModel = authViewModel)
        }
    }
}