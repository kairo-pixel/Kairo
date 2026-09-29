package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ServiceItem
import com.example.ui.theme.KairoBorderGlow
import com.example.ui.theme.KairoBorderPurple
import com.example.ui.theme.KairoCardDark
import com.example.ui.theme.KairoCardElevated
import com.example.ui.theme.KairoCyberPurple
import com.example.ui.theme.KairoDeepNavy
import com.example.ui.theme.KairoElectricBlue
import com.example.ui.theme.KairoNeonCyan
import com.example.ui.theme.KairoPurpleBright
import com.example.ui.theme.KairoSilverLight
import com.example.ui.theme.KairoSilverMuted
import com.example.ui.theme.KairoTextMuted
import com.example.ui.theme.KairoVoidBlack
import com.example.ui.theme.KairoWhatsAppDark
import com.example.ui.theme.KairoWhatsAppGreen
import com.example.util.IntentHelper

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ServiceCard(
    service: ServiceItem,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isPressed by interactionSource.collectIsPressedAsState()

    // Smooth hover / touch animation states
    val scale by animateFloatAsState(
        targetValue = when {
            isPressed -> 0.985f
            isHovered -> 1.025f
            else -> 1f
        },
        animationSpec = tween(durationMillis = 220, easing = FastOutSlowInEasing),
        label = "card_scale"
    )

    val elevationDp by animateFloatAsState(
        targetValue = if (isHovered) 16f else 6f,
        animationSpec = tween(durationMillis = 220),
        label = "card_elevation"
    )

    // Continuous ambient neon border glow pulse
    val infiniteTransition = rememberInfiniteTransition(label = "service_neon_glow")
    val pulseGlow by infiniteTransition.animateFloat(
        initialValue = 0.35f,
        targetValue = 0.85f,
        animationSpec = infiniteRepeatable(
            animation = tween(2600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_glow"
    )

    // Sweep gradient rotation for neon border
    val angleOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "border_gradient_flow"
    )

    // Highlight glow intensity based on hover
    val borderAlpha = if (isHovered) 1f else pulseGlow
    val borderGradient = Brush.sweepGradient(
        colors = listOf(
            KairoNeonCyan.copy(alpha = borderAlpha),
            KairoCyberPurple.copy(alpha = borderAlpha * 0.9f),
            KairoElectricBlue.copy(alpha = borderAlpha * 0.85f),
            KairoNeonCyan.copy(alpha = borderAlpha)
        )
    )

    val cardShape = RoundedCornerShape(20.dp)

    Card(
        shape = cardShape,
        colors = CardDefaults.cardColors(
            // Translucent glassmorphism container
            containerColor = Color.Transparent
        ),
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .shadow(elevationDp.dp, cardShape, spotColor = KairoNeonCyan.copy(alpha = if (isHovered) 0.45f else 0.2f))
            .hoverable(interactionSource = interactionSource)
            .border(
                width = if (isHovered) 1.8.dp else 1.2.dp,
                brush = borderGradient,
                shape = cardShape
            )
            .testTag("service_card_${service.id}")
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                // Glassmorphism multi-layered background with diagonal glass reflection
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            KairoCardElevated.copy(alpha = 0.88f),
                            KairoDeepNavy.copy(alpha = 0.94f)
                        )
                    )
                )
                .drawBehind {
                    // Glass highlight diagonal sheen across top-left to center
                    drawLine(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.White.copy(alpha = if (isHovered) 0.12f else 0.05f),
                                Color.Transparent
                            ),
                            start = Offset(0f, 0f),
                            end = Offset(size.width * 0.8f, size.height * 0.4f)
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(size.width, size.height * 0.5f),
                        strokeWidth = 2f
                    )

                    // Ambient neon radial flare behind icon area
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                KairoNeonCyan.copy(alpha = if (isHovered) 0.22f else 0.12f),
                                Color.Transparent
                            ),
                            center = Offset(size.width - 60.dp.toPx(), 60.dp.toPx()),
                            radius = 100.dp.toPx()
                        ),
                        radius = 100.dp.toPx(),
                        center = Offset(size.width - 60.dp.toPx(), 60.dp.toPx())
                    )
                }
                .padding(22.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Top Header Row: Service Badge + Unique Icon
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Neon Number Badge & Tag
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(KairoDeepNavy)
                                .border(
                                    1.dp,
                                    if (isHovered) KairoNeonCyan else KairoBorderGlow,
                                    RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 10.dp, vertical = 5.dp)
                        ) {
                            Text(
                                text = "SERVICE ${service.number}",
                                color = KairoNeonCyan,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Black,
                                fontFamily = FontFamily.Monospace,
                                letterSpacing = 1.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = service.tag,
                            color = KairoCyberPurple,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace,
                            letterSpacing = 1.sp
                        )
                    }

                    // Futuristic Glowing Icon Halo
                    val iconHaloScale by animateFloatAsState(
                        targetValue = if (isHovered) 1.1f else 1f,
                        animationSpec = tween(200),
                        label = "icon_halo_scale"
                    )

                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .graphicsLayer {
                                scaleX = iconHaloScale
                                scaleY = iconHaloScale
                            }
                            .clip(CircleShape)
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        if (isHovered) KairoNeonCyan.copy(alpha = 0.35f) else KairoNeonCyan.copy(alpha = 0.18f),
                                        KairoDeepNavy.copy(alpha = 0.85f)
                                    )
                                )
                            )
                            .border(
                                width = 1.5.dp,
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        KairoNeonCyan,
                                        KairoCyberPurple
                                    )
                                ),
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = service.icon,
                            contentDescription = service.title,
                            tint = if (isHovered) Color.White else KairoNeonCyan,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Service Name with Metallic Silver Look
                Text(
                    text = service.title,
                    color = KairoSilverLight,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.6.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Short Description
                Text(
                    text = service.description,
                    color = KairoSilverMuted,
                    fontSize = 14.sp,
                    lineHeight = 21.sp
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Feature Highlights Pills
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    service.highlights.forEach { highlight ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(KairoCardDark.copy(alpha = 0.8f))
                                .border(1.dp, KairoBorderGlow, RoundedCornerShape(12.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = KairoNeonCyan,
                                modifier = Modifier.size(11.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = highlight,
                                color = KairoSilverLight,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Pricing Info Tag (Strictly NO fake prices)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(KairoDeepNavy)
                        .border(1.dp, KairoBorderPurple, RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 7.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = KairoCyberPurple,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Contact for current pricing",
                        color = KairoSilverLight,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Interactive 'ORDER NOW' Button
                val buttonInteraction = remember { MutableInteractionSource() }
                val isButtonHovered by buttonInteraction.collectIsHoveredAsState()
                val isButtonPressed by buttonInteraction.collectIsPressedAsState()

                val buttonScale by animateFloatAsState(
                    targetValue = if (isButtonPressed) 0.97f else if (isButtonHovered) 1.02f else 1f,
                    label = "order_btn_scale"
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .graphicsLayer {
                            scaleX = buttonScale
                            scaleY = buttonScale
                        }
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = if (isButtonHovered) {
                                    listOf(KairoNeonCyan, KairoPurpleBright)
                                } else {
                                    listOf(KairoNeonCyan, KairoElectricBlue)
                                }
                            )
                        )
                        .border(
                            width = 1.dp,
                            color = Color.White.copy(alpha = if (isButtonHovered) 0.6f else 0.2f),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .hoverable(interactionSource = buttonInteraction)
                        .clickable(interactionSource = buttonInteraction, indication = null) {
                            IntentHelper.launchWhatsApp(
                                context = context,
                                customMessage = service.defaultMessage
                            )
                        }
                        .testTag("order_now_button_${service.id}"),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Chat,
                            contentDescription = "WhatsApp Order",
                            tint = KairoVoidBlack,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "ORDER NOW",
                            color = KairoVoidBlack,
                            fontWeight = FontWeight.Black,
                            fontSize = 14.sp,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = KairoVoidBlack,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}
