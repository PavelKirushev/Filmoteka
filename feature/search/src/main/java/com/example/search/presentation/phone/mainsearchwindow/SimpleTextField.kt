package com.example.search.presentation.phone.mainsearchwindow

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.TextUnit

@Composable
fun SimpleTextField(
    onValueChange: (String) -> Unit,
    keyboardActions: KeyboardActions,
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
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = keyboardActions,
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