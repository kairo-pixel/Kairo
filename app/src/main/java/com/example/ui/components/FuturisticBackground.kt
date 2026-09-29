package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.ui.theme.KairoCyberPurple
import com.example.ui.theme.KairoDeepNavy
import com.example.ui.theme.KairoNeonCyan
import com.example.ui.theme.KairoVoidBlack

@Composable
fun FuturisticBackground(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        // Deep void background with radial neon cyan and purple glows
        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(
                    KairoVoidBlack,
                    KairoDeepNavy,
                    KairoVoidBlack
                )
            )
        )

        // Top-right cyan aura
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    KairoNeonCyan.copy(alpha = 0.08f),
                    Color.Transparent
                ),
                center = Offset(width * 0.85f, height * 0.08f),
                radius = width * 0.65f
            ),
            radius = width * 0.65f,
            center = Offset(width * 0.85f, height * 0.08f)
        )

        // Mid-left purple aura
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    KairoCyberPurple.copy(alpha = 0.07f),
                    Color.Transparent
                ),
                center = Offset(width * 0.15f, height * 0.45f),
                radius = width * 0.7f
            ),
            radius = width * 0.7f,
            center = Offset(width * 0.15f, height * 0.45f)
        )

        // Bottom-right cyan aura
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    KairoNeonCyan.copy(alpha = 0.06f),
                    Color.Transparent
                ),
                center = Offset(width * 0.85f, height * 0.85f),
                radius = width * 0.75f
            ),
            radius = width * 0.75f,
            center = Offset(width * 0.85f, height * 0.85f)
        )

        // Subtle futuristic grid lines
        val gridSpacing = 80f
        val gridColor = KairoNeonCyan.copy(alpha = 0.025f)
        var x = 0f
        while (x < width) {
            drawLine(
                color = gridColor,
                start = Offset(x, 0f),
                end = Offset(x, height),
                strokeWidth = 1f
            )
            x += gridSpacing
        }

        var y = 0f
        while (y < height) {
            drawLine(
                color = gridColor,
                start = Offset(0f, y),
                end = Offset(width, y),
                strokeWidth = 1f
            )
            y += gridSpacing
        }
    }
}
