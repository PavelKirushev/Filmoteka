package com.example.auth.presentation.phone.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun RegisterTextField(form: String,
                      text: MutableState<String>,
                      isPassword: Boolean = false,
                      checkPassword: String? = null
) {
    val showPassword = remember { mutableStateOf(false) }
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ){
        Text(text = form,
            style = MaterialTheme.typography.bodySmall.copy(MaterialTheme.colorScheme.onPrimaryContainer),
            modifier = Modifier.fillMaxWidth(0.3f)
        )
        Spacer(modifier = Modifier.size(20.dp))
        TextField(
            value = text.value,
            onValueChange = { text.value = it },
            singleLine = true,
            modifier = Modifier.height(70.dp).width(220.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedContainerColor = MaterialTheme.colorScheme.background,

                unfocusedIndicatorColor = if (checkPassword == null && isPassword && text.value.length < 8 && text.value.length != 0 || checkPassword != null && checkPassword != text.value) { MaterialTheme.colorScheme.error } else { MaterialTheme.colorScheme.primary },
                focusedIndicatorColor = if (checkPassword == null && isPassword && text.value.length < 8 || checkPassword != null && checkPassword != text.value) { MaterialTheme.colorScheme.error } else { MaterialTheme.colorScheme.onPrimaryContainer },
            ),
            textStyle = MaterialTheme.typography.bodyMedium.copy(MaterialTheme.colorScheme.primary),
            visualTransformation = if (isPassword && !showPassword.value) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            trailingIcon = {
                if (isPassword) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "показать пароль",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.clickable { showPassword.value = !showPassword.value }
                    )
                }
                },
            supportingText = {
                if (isPassword && text.value.length != 0 && text.value.length < 8 && checkPassword == null) {
                    Text("Не менее символов", style = MaterialTheme.typography.bodySmall.copy(MaterialTheme.colorScheme.error))
                } else if (checkPassword != null && checkPassword != text.value && text.value.length != 0) {
                    Text("Пароли не совпадают", style = MaterialTheme.typography.bodySmall.copy(MaterialTheme.colorScheme.error))
                }},
        )
    }
}