package com.example.auth.presentation.phone

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.auth.domain.models.User
import com.example.auth.presentation.AuthAction
import com.example.auth.presentation.AuthViewModel
import com.example.auth.presentation.AuthViewState
import com.example.ui.ThemeViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthScreen(authViewModel: AuthViewModel, themeViewModel: ThemeViewModel) {
    val authViewState by authViewModel.authStateFlow.collectAsState()

    val showDialog = authViewState is AuthViewState.ShowRegisterDialog
    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val messageState = remember { mutableStateOf<String?>(null) }

    LaunchedEffect(authViewState) {
        when (authViewState) {
            is AuthViewState.Error -> {
                messageState.value = (authViewState as AuthViewState.Error).message
            }
            is AuthViewState.Success -> {
                messageState.value = "Пользуйтесь еп"
            }
            else -> {
            }
        }
    }

    messageState.value?.let { msg ->
        LaunchedEffect(msg) {
            scope.launch {
                snackbarHostState.showSnackbar(msg)
            }
            messageState.value = null
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().height(70.dp).padding(10.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "настройки",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.clickable {
                        themeViewModel.toggleTheme()
                    })
            }
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
                    onClick = { authViewModel.submitAction(AuthAction.LoginButtonClicked) },
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
                    val regLogin = remember { mutableStateOf("") }
                    val regPassword = remember { mutableStateOf("") }
                    val regCheckPassword = remember { mutableStateOf("") }
                    val regEmail = remember { mutableStateOf("") }
                    val regAge = remember { mutableStateOf("") }
                    AlertDialog(
                        onDismissRequest = { authViewModel.submitAction(AuthAction.HideRegisterDialog) },
                        confirmButton = {
                            Button(
                                onClick = {
                                    authViewModel.submitAction(
                                        AuthAction.RegisterButtonClicked(
                                            User(
                                                regLogin.value,
                                                regEmail.value,
                                                regPassword.value,
                                                regAge.value.toIntOrNull()
                                            )
                                        )
                                    )
                                    authViewModel.submitAction(AuthAction.HideRegisterDialog)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                    contentColor = MaterialTheme.colorScheme.primaryContainer,
                                ),
                                shape = RoundedCornerShape(5.dp)
                            ) {
                                Text("Подтвердить", style = MaterialTheme.typography.bodyMedium)
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = { authViewModel.submitAction(AuthAction.HideRegisterDialog) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                ),
                                shape = RoundedCornerShape(5.dp)
                            ) {
                                Text("Отменить", style = MaterialTheme.typography.bodyMedium)
                            }
                        },
                        title = {
                            Text(
                                text = "Регистрация пользователя",
                                style = MaterialTheme.typography.bodyLarge.copy(MaterialTheme.colorScheme.onPrimaryContainer)
                            )
                        },
                        text = {
                            Column {
                                RegisterTextField("Логин", login)
                                RegisterTextField("Пароль", password, true)
                                RegisterTextField(
                                    "Подтвердите пароль",
                                    regCheckPassword,
                                    true,
                                    password.value
                                )
                                RegisterTextField("Почта", regEmail)
                                RegisterTextField("Возраст", regAge)
                            }
                        },
                        containerColor = MaterialTheme.colorScheme.background,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(5.dp)
                    )
                }
            }
        }
    }
}