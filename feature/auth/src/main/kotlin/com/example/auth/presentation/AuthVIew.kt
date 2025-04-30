package com.example.auth.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthScreen(viewModel: AuthViewModel) {
    val authState by viewModel.auth.collectAsState()
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column (modifier = Modifier
        .padding(20.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextField(
            value = login,
            textStyle = TextStyle(fontSize = 20.sp),
            onValueChange = {login = it},
            placeholder = { Text("login") }
        )
        TextField(
            value = password,
            textStyle = TextStyle(fontSize = 20.sp),
            onValueChange = { password = it },
            placeholder = { Text("password") }
        )
        Button(
            onClick = {
                Log.d("login", login)
                Log.d("password", password)
                viewModel.getUser(login, password)
            }
        ) {
            Text("Login", fontSize = 15.sp)
        }
        Text(authState?.email ?: "null", fontSize = 15.sp)
    }
}