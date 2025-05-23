package com.example.auth.presentation.phone

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.auth.domain.models.User
import com.example.ui.ThemeViewModel

@Composable
fun AuthScreen(viewModel: AuthViewModel, themeViewModel: ThemeViewModel) {
    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val showDialog = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
        .fillMaxSize()
    ) {
        Row(modifier = Modifier.fillMaxWidth().height(70.dp).padding(10.dp), horizontalArrangement = Arrangement.End){
            Icon(imageVector = Icons.Filled.Settings,
                contentDescription = "настройки",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.clickable {
                    themeViewModel.toggleTheme()
                })
        }
        Column (modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row(modifier = Modifier.fillMaxWidth(0.75f)) {
                Text(text = "Вход", style = MaterialTheme.typography.titleLarge.copy(MaterialTheme.colorScheme.onPrimaryContainer))
            }
            TextField(text = login, placeholderText = "Логин")
            TextField(text = password, placeholderText = "Пароль", isPassword = true)

            Button(
                onClick = {
                    viewModel.getUser(login.value, password.value)
                },
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    contentColor = MaterialTheme.colorScheme.primaryContainer,
                )
            ) {
                Text("Войти", style = MaterialTheme.typography.titleMedium)
            }
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                Text("Нет аккаунта? ", style = MaterialTheme.typography.bodyMedium.copy(MaterialTheme.colorScheme.onBackground))
                Text("Зарегистрироваться", style = MaterialTheme.typography.titleSmall.copy(MaterialTheme.colorScheme.onPrimaryContainer),
                    modifier = Modifier.clickable {
                        showDialog.value = true
                    })
            }
            if (showDialog.value) {
                val login = remember { mutableStateOf("") }
                val password = remember { mutableStateOf("") }
                val checkPassword = remember { mutableStateOf("") }
                val email = remember { mutableStateOf("") }
                val age = remember { mutableStateOf("") }
                AlertDialog(
                    onDismissRequest = { showDialog.value = false },
                    confirmButton = {
                        Button(onClick = {
                            viewModel.setUser(User(login.value, email.value, password.value, age.value.toInt()))
                            showDialog.value = !showDialog.value
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
                        Button(onClick = {
                            showDialog.value = false
                        },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            ),
                            shape = RoundedCornerShape(5.dp)
                        ) {
                            Text("Отменить", style = MaterialTheme.typography.bodyMedium)
                        }
                    },
                    title = { Text(text = "Регистрация пользователя",
                        style = MaterialTheme.typography.bodyLarge.copy(MaterialTheme.colorScheme.onPrimaryContainer)) },
                    text = {
                        Column {
                            RegisterTextField("Логин", login)
                            RegisterTextField("Пароль", password, true)
                            RegisterTextField("Подтвердите пароль", checkPassword, true, password.value)
                            RegisterTextField("Почта", email)
                            RegisterTextField("Возраст", age)
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