package com.k4tr1n4.marvelcomics.core.ui.composable

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultScreen(
    isLoading: Boolean,
    errorThrowable: Throwable?,
    content: @Composable ColumnScope.() -> Unit
){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) { content() }
    }

    if(isLoading){ Loading() }

    errorThrowable?.message?.let { Log.e("DefaultError", it) }
}