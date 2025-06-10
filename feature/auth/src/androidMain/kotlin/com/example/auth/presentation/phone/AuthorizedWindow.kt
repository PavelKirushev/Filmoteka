package com.example.auth.presentation.phone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.auth.domain.models.User

@Composable
fun AuthorizedWindow(currentUser: User?) {
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize().padding(30.dp)) {
        Text(text = "Ваши данные", style = MaterialTheme.typography.titleLarge.copy(MaterialTheme.colorScheme.onPrimaryContainer))
        Text(text = "Логин: ${currentUser?.login}", style = MaterialTheme.typography.bodyLarge.copy(MaterialTheme.colorScheme.onPrimaryContainer))
        Text(text = "Почта: ${currentUser?.email}", style = MaterialTheme.typography.bodyLarge.copy(MaterialTheme.colorScheme.onPrimaryContainer))
        Text(text = "Возраст: ${currentUser?.age}", style = MaterialTheme.typography.bodyLarge.copy(MaterialTheme.colorScheme.onPrimaryContainer))
    }
}