package com.example.auth.presentation.phone

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun TextField(text: MutableState<String>, placeholderText: String, isPassword: Boolean = false) {
    OutlinedTextField(
        value = text.value,
        onValueChange = { text.value = it },
        Modifier
            .fillMaxWidth(0.8f)
            .padding(5.dp),
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyLarge,
        placeholder = {
            Text(
                text = placeholderText,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,

            unfocusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
            focusedTextColor = MaterialTheme.colorScheme.primaryContainer,

            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.5f),
            focusedPlaceholderColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),

            ),
        visualTransformation = if (isPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
    )
}