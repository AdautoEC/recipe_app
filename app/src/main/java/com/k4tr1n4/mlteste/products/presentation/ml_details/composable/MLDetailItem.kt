package com.k4tr1n4.mlteste.products.presentation.ml_details.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.k4tr1n4.mlteste.core.ui.composable.DefaultDivider

@Composable
fun MLDetailItem(
    label: String,
    value: String,
    hasDivider: Boolean = true
){
    Row (
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp),
    ){
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.titleSmall,
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
    if(hasDivider) DefaultDivider()
}

@Preview(showBackground = true)
@Composable
fun MLDetailItemPreview(){
   Box (modifier = Modifier.padding(16.dp)){
       MLDetailItem(
           label = "label 1",
           value = "value 1",
       )
   }
}