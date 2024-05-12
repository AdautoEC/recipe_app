package com.k4tr1n4.mlteste.core.ui.composable

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun AsyncImageShimmer(
    modifier: Modifier = Modifier,
    url: String,
    size: Dp = 64.dp
){
    var shimmerEffectState by remember {
        mutableStateOf(true)
    }
    Box(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(
                    corner = CornerSize(8.dp)
                )
            )
    ){
        if(shimmerEffectState){
            Box (
                modifier = Modifier
                    .size(size)
                    .shimmerEffect()
            )
        }

        AsyncImage(
            modifier = Modifier.size(size),
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            onLoading = {
                shimmerEffectState = true
            },
            onSuccess = {
                shimmerEffectState = false
            },
            contentDescription = null,
        )
    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1000)
        ),
        label = ""
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB4B3B3),
                Color(0xFFA09F9F),
                Color(0xFF929292)
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.width.toFloat())
        )
    ).onGloballyPositioned {
        size = it.size
    }

}