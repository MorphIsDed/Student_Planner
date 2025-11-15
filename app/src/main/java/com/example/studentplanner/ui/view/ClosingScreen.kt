package com.example.studentplanner.ui.view

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ClosingScreen() {
    val infiniteTransition = rememberInfiniteTransition(label = "dots transition")
    val yOffsets = (0..2).map { index ->
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = -15f, // Move dots up
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 300, easing = LinearOutSlowInEasing),
                repeatMode = RepeatMode.Reverse,
                initialStartOffset = StartOffset(index * 100)
            ),
            label = "dot-offset-$index"
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Closing App")
            yOffsets.forEach { yOffset ->
                Text(
                    text = ".",
                    modifier = Modifier.offset(y = yOffset.value.dp)
                )
            }
        }
    }
}
