package com.k4tr1n4.mlteste.core.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.k4tr1n4.mlteste.R
@Composable
fun DefaultScreen(
    isLoading: Boolean = false,
    errorThrowable: Throwable? = null,
    onDismissError:() -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) { content() }
    }

    if(isLoading){ Loading() }

    errorThrowable?.message?.let { ErrorDialog(
        title = stringResource(id = R.string.search_screen_error_title),
        description = stringResource(id = R.string.search_screen_error_description),
        buttonText = stringResource(id = R.string.search_screen_error_button),
        onDismiss = onDismissError,
    ) }
}