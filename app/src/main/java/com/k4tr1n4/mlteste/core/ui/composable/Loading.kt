package com.k4tr1n4.mlteste.core.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.k4tr1n4.mlteste.R


@Composable
fun Loading(){
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Box(modifier = Modifier
            .background(color = colorResource(id = R.color.bluer))
            .fillMaxSize()
        )
        CircularProgressIndicator(
            modifier = Modifier
                .background(
                    color = Color.Black,
                    shape = RoundedCornerShape(
                        corner = CornerSize(8.dp)
                    )
                )
                .padding(8.dp)
                .size(48.dp),
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview(){
    Box(modifier = Modifier.padding(16.dp)){
        Loading()
    }
}