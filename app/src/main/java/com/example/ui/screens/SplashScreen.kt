package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.KairoBrand
import com.example.ui.theme.KairoBorderGlow
import com.example.ui.theme.KairoCardDark
import com.example.ui.theme.KairoCyberPurple
import com.example.ui.theme.KairoDeepNavy
import com.example.ui.theme.KairoElectricBlue
import com.example.ui.theme.KairoNeonCyan
import com.example.ui.theme.KairoPurpleBright
import com.example.ui.theme.KairoSilverLight
import com.example.ui.theme.KairoSilverMuted
import com.example.ui.theme.KairoTextMuted
import com.example.ui.theme.KairoVoidBlack
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Animated progress from 0% to 100%
    val progress = remember { Animatable(0f) }

    // Infinite ambient animations
    val infiniteTransition = rememberInfiniteTransition(label = "splash_ambient")
    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "radar_rotate"
    )

    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.98f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "logo_pulse"
    )

    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 0.85f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow_pulse"
    )

    // Running progress animation and automatic transition
    LaunchedEffect(Unit) {
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 2400, easing = LinearOutSlowInEasing)
        )
        delay(250)
        onSplashFinished()
    }

    val currentPercentage = (progress.value * 100).toInt().coerceIn(0, 100)

    val statusText by remember {
        derivedStateOf {
            when {
                progress.value < 0.25f -> "INITIALIZING DIGITAL SUITE..."
                progress.value < 0.55f -> "CONNECTING TO VERIFIED NETWORK..."
                progress.value < 0.85f -> "LOADING KAIRO SERVICES..."
                progress.value < 0.99f -> "FINALIZING ENCRYPTED INTERFACE..."
                else -> "WELCOME TO KAIRO DIGITAL"
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(KairoVoidBlack)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                // Allow user to tap anywhere to jump directly to app if they don't want to wait
                onSplashFinished()
            }
            .statusBarsPadding()
            .navigationBarsPadding()
            .testTag("kairo_splash_screen")
    ) {
        // Futuristic radial ambient flares
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Top Cyan Flare
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        KairoNeonCyan.copy(alpha = 0.15f * glowAlpha),
                        Color.Transparent
                    ),
                    center = Offset(size.width * 0.5f, size.height * 0.25f),
                    radius = size.width * 0.7f
                ),
                radius = size.width * 0.7f,
                center = Offset(size.width * 0.5f, size.height * 0.25f)
            )

            // Center Purple Flare
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        KairoCyberPurple.copy(alpha = 0.22f * glowAlpha),
                        Color.Transparent
                    ),
                    center = Offset(size.width * 0.5f, size.height * 0.45f),
                    radius = size.width * 0.6f
                ),
                radius = size.width * 0.6f,
                center = Offset(size.width * 0.5f, size.height * 0.45f)
            )
        }

        // Center Content Column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // HIGH-TECH METALLIC/NEON EMBLEM
            Box(
                modifier = Modifier
                    .size(170.dp)
                    .graphicsLayer {
                        scaleX = pulseScale
                        scaleY = pulseScale
                    },
                contentAlignment = Alignment.Center
            ) {
                // Rotating segmented orbital cyber rings
                Canvas(modifier = Modifier.fillMaxSize()) {
                    rotate(rotationAngle) {
                        drawCircle(
                            brush = Brush.sweepGradient(
                                colors = listOf(
                                    KairoNeonCyan.copy(alpha = 0.8f),
                                    Color.Transparent,
                                    KairoCyberPurple.copy(alpha = 0.8f),
                                    Color.Transparent,
                                    KairoNeonCyan.copy(alpha = 0.8f)
                                )
                            ),
                            radius = size.width * 0.46f,
                            style = Stroke(
                                width = 2.dp.toPx(),
                                cap = StrokeCap.Round
                            )
                        )
                    }

                    rotate(-rotationAngle * 1.5f) {
                        drawCircle(
                            color = KairoElectricBlue.copy(alpha = 0.35f),
                            radius = size.width * 0.51f,
                            style = Stroke(
                                width = 1.dp.toPx()
                            )
                        )
                    }
                }

                // Inner Hexagonal / Squircle Metallic Emblem Box
                Box(
                    modifier = Modifier
                        .size(118.dp)
                        .clip(RoundedCornerShape(32.dp))
                        .shadow(24.dp, RoundedCornerShape(32.dp), spotColor = KairoNeonCyan)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    KairoDeepNavy,
                                    KairoVoidBlack
                                )
                            )
                        )
                        .border(
                            width = 2.dp,
                            brush = Brush.sweepGradient(
                                colors = listOf(
                                    KairoNeonCyan,
                                    KairoCyberPurple,
                                    KairoPurpleBright,
                                    KairoNeonCyan
                                )
                            ),
                            shape = RoundedCornerShape(32.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    // Metallic / Chrome Monogram Icon "K"
                    Text(
                        text = "K",
                        fontSize = 62.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.SansSerif,
                        color = KairoSilverLight,
                        modifier = Modifier.drawBehind {
                            // Metallic gradient reflection
                            drawCircle(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        KairoNeonCyan.copy(alpha = 0.4f),
                                        Color.Transparent
                                    ),
                                    center = Offset(size.width / 2f, size.height / 2f),
                                    radius = size.width * 0.6f
                                )
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // BRAND TITLE
            Text(
                text = "KAIRO",
                color = KairoSilverLight,
                fontSize = 38.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 6.sp,
                textAlign = TextAlign.Center
            )

            Text(
                text = "DIGITAL SERVICES",
                color = KairoNeonCyan,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 4.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Tagline
            Text(
                text = KairoBrand.TAGLINE,
                color = KairoSilverMuted,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                letterSpacing = 0.5.sp
            )

            Spacer(modifier = Modifier.height(36.dp))

            // HIGH-TECH PROGRESS BAR & TRAY
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Percentage & Status Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = statusText,
                        color = KairoTextMuted,
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )

                    Text(
                        text = "$currentPercentage%",
                        color = KairoNeonCyan,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Futuristic Progress Bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(KairoCardDark)
                        .border(0.5.dp, KairoBorderGlow, RoundedCornerShape(3.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(fraction = progress.value)
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        KairoCyberPurple,
                                        KairoElectricBlue,
                                        KairoNeonCyan
                                    )
                                )
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Trust Badges Pill
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(KairoDeepNavy.copy(alpha = 0.7f))
                    .border(1.dp, KairoBorderGlow, RoundedCornerShape(20.dp))
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = KairoNeonCyan,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "VERIFIED",
                        color = KairoSilverMuted,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                }

                Box(
                    modifier = Modifier
                        .size(3.dp)
                        .background(KairoTextMuted, CircleShape)
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Bolt,
                        contentDescription = null,
                        tint = KairoNeonCyan,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "FAST DELIVERY",
                        color = KairoSilverMuted,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                }

                Box(
                    modifier = Modifier
                        .size(3.dp)
                        .background(KairoTextMuted, CircleShape)
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = null,
                        tint = KairoNeonCyan,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "SECURE",
                        color = KairoSilverMuted,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }

        // Bottom Tap to Skip / Enter affordance
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "TAP SCREEN TO ENTER",
                color = KairoTextMuted.copy(alpha = 0.65f),
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.sp
            )
        }
    }
}
