package com.example.auth.presentation.phone.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.auth.presentation.AuthAction
import com.example.auth.presentation.AuthViewModel
import com.example.ui.ThemeViewModel

@Composable
fun SettingsDialog(authViewModel: AuthViewModel, themeViewModel: ThemeViewModel, logOut: () -> Unit) {
    val isDarkTheme = themeViewModel.isDarkTheme.collectAsState()
    val checkedState = remember { mutableStateOf(isDarkTheme.value) }

    AlertDialog(
        onDismissRequest = { authViewModel.submitAction(AuthAction.HideSettingsDialog) },
        confirmButton = {
            Button(
                onClick = { authViewModel.submitAction(AuthAction.HideSettingsDialog) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    contentColor = MaterialTheme.colorScheme.primaryContainer,
                ),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("ОК", style = MaterialTheme.typography.bodyMedium)
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    logOut()
                    authViewModel.submitAction(AuthAction.HideSettingsDialog)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError,
                ),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("Выйти", style = MaterialTheme.typography.bodyMedium)
            }
        },
        title = {
            Text(
                text = "Настройки",
                style = MaterialTheme.typography.bodyLarge.copy(MaterialTheme.colorScheme.onPrimaryContainer)
            )
        },
        text = {
            Column {
                SettingsRow(
                    "Темная тема",
                    checkedState,
                    onCheckedChange = {
                        checkedState.value = it
                        themeViewModel.toggleTheme()
                    }
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(5.dp)
    )
}