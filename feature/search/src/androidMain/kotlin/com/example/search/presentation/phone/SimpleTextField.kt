package com.example.search.presentation.phone

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction

@Composable
fun SimpleTextField(
    onValueChange: (String) -> Unit,
    modifier: Modifier
) {
    val textState = remember { mutableStateOf("") }

    BasicTextField(
        value = textState.value,
        onValueChange = { newText ->
            textState.value = newText
            onValueChange(newText)
        },
        modifier = modifier,
        textStyle = MaterialTheme.typography.bodyLarge.copy(MaterialTheme.colorScheme.primary),
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        decorationBox = {innerTextField ->
            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                if (textState.value.isEmpty()) {
                    Text("movie title", style = MaterialTheme.typography.bodyLarge.copy(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)))
                }
                innerTextField()
            }
        }
    )
}