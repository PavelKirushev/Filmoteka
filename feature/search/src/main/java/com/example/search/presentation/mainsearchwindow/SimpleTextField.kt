package com.example.search.presentation.mainsearchwindow

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.TextUnit

@Composable
fun SimpleTextField(
    onValueChange: (String) -> Unit,
    fontSize: TextUnit,
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
        textStyle = TextStyle(color = Color.Black, fontSize = fontSize),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = keyboardActions,
        modifier = modifier
    )
}