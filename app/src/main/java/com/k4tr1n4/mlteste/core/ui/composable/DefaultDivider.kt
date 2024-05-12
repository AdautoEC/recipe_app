package com.k4tr1n4.mlteste.core.ui.composable

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DefaultDivider() {
    HorizontalDivider(thickness = 0.5.dp, color = Color.Gray)
}
