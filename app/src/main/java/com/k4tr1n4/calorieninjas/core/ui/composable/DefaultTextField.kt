package com.k4tr1n4.calorieninjas.core.ui.composable

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@Composable
fun DefaultTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit
) {
    var textFieldValue by remember {
        mutableStateOf(value)
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = modifier,
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onValueChange(it)
        },
        keyboardActions = KeyboardActions(onDone = {
            onDone()
            keyboardController?.hide()
        }),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
    )
}