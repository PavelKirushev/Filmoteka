package com.example.auth.presentation.phone

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.auth.presentation.AuthViewModel
import com.example.auth.presentation.AuthViewState
import com.example.ui.ThemeViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthScreen(authViewModel: AuthViewModel, themeViewModel: ThemeViewModel, navigateHome: () -> Unit) {
    val authViewState by authViewModel.authStateFlow.collectAsState()

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
            LoginWindow(authViewModel, authViewState)
//            val user = authViewModel.getCurrentUser()
//            if (user != null) {
//                AuthorizedWindow(user)
//            } else {
//                LoginWindow(authViewModel, authViewState)
//            }
        }
    }
}