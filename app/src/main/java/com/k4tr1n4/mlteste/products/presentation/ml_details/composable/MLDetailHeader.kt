package com.k4tr1n4.mlteste.products.presentation.ml_details.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MLDetailHeader(
    title: String,
    onBackClick: () -> Unit
){
    Box{
        Icon(
            modifier = Modifier.clickable { onBackClick() },
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = "Botão de voltar"
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 0.dp,
                    bottom = 8.dp,
                    start = 32.dp,
                    end = 32.dp
                ),
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MLDetailHeaderPreview(){
    Box (modifier = Modifier.fillMaxWidth().padding(16.dp)){
        MLDetailHeader(
            title = "Um item muito bunito e muito legal",
            onBackClick = {},
        )
    }
}