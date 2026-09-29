package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Verified
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.KairoBrand
import com.example.ui.theme.*
import com.example.util.IntentHelper

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HeroSection(
    onExploreServicesClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Continuous ambient glow transition
    val infiniteTransition = rememberInfiniteTransition(label = "hero_glow")
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 0.95f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow_pulse"
    )

    val sweepAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "sweep_angle"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Status Pill: Online & Ready
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .background(KairoCardDark.copy(alpha = 0.9f))
                .border(
                    width = 1.dp,
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            KairoNeonCyan.copy(alpha = glowAlpha),
                            KairoCyberPurple.copy(alpha = glowAlpha * 0.7f)
                        )
                    ),
                    shape = RoundedCornerShape(30.dp)
                )
                .padding(horizontal = 14.dp, vertical = 6.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(KairoNeonCyan, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "KAIRO DIGITAL // VERIFIED PLATFORM",
                color = KairoNeonCyan,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 1.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // High-Impact Futuristic KAIRO Visual Banner Showcase
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(22.dp))
                .background(KairoCardElevated)
                .shadow(16.dp, RoundedCornerShape(22.dp), spotColor = KairoNeonCyan.copy(alpha = 0.35f))
                .border(
                    width = 1.5.dp,
                    brush = Brush.sweepGradient(
                        colors = listOf(
                            KairoNeonCyan.copy(alpha = glowAlpha),
                            KairoCyberPurple.copy(alpha = glowAlpha * 0.9f),
                            KairoElectricBlue.copy(alpha = glowAlpha * 0.8f),
                            KairoNeonCyan.copy(alpha = glowAlpha)
                        )
                    ),
                    shape = RoundedCornerShape(22.dp)
                )
        ) {
            // Cyber artwork
            Image(
                painter = painterResource(id = R.drawable.img_hero_banner),
                contentDescription = "Futuristic Cyber Banner",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // Deep overlay with metallic typography and cyber glow
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                KairoVoidBlack.copy(alpha = 0.4f),
                                KairoDeepNavy.copy(alpha = 0.88f)
                            )
                        )
                    )
                    .drawBehind {
                        // Ambient luminous radial center flare
                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    KairoNeonCyan.copy(alpha = 0.35f * glowAlpha),
                                    KairoCyberPurple.copy(alpha = 0.15f * glowAlpha),
                                    Color.Transparent
                                ),
                                center = Offset(size.width / 2f, size.height / 2f),
                                radius = size.width * 0.45f
                            )
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "KAIRO",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = 6.sp,
                        color = KairoSilverLight,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "DIGITAL SERVICES",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        letterSpacing = 4.sp,
                        color = KairoNeonCyan
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(KairoVoidBlack.copy(alpha = 0.6f))
                            .border(1.dp, KairoBorderGlow, RoundedCornerShape(12.dp))
                            .padding(horizontal = 10.dp, vertical = 3.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = null,
                            tint = KairoNeonCyan,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "PREMIUM DIGITAL SOLUTIONS",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.2.sp,
                            color = KairoSilverMuted
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // BOLD MAIN TITLE
        Text(
            text = KairoBrand.BRAND_NAME,
            color = KairoSilverLight,
            fontSize = 30.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 1.sp,
            textAlign = TextAlign.Center,
            lineHeight = 36.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Value Proposition Subtitle
        Text(
            text = "“${KairoBrand.TAGLINE}”",
            color = KairoNeonCyan,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Value Proposition Description
        Text(
            text = "Empowering creators and digital professionals with dedicated TikTok growth, UK accounts, premium apps, and CapCut editing solutions.",
            color = KairoSilverMuted,
            fontSize = 14.sp,
            lineHeight = 21.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Three Value Pillars
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PillarBadge(icon = Icons.Default.Bolt, title = "Fast Response")
            PillarBadge(icon = Icons.Default.Security, title = "Trusted Support")
            PillarBadge(icon = Icons.Default.Diamond, title = "Premium Tier")
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Feature Tags Row
        FlowRow(
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            listOf("TikTok Promotion", "UK Accounts", "Premium Apps", "CapCut Services").forEach { tag ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(KairoCardDark.copy(alpha = 0.85f))
                        .border(1.dp, KairoBorderGlow, RoundedCornerShape(16.dp))
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = KairoNeonCyan,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = tag,
                        color = KairoSilverMuted,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // PRIMARY CTA BUTTON & SECONDARY ACTION
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // PRIMARY CTA: EXPLORE SERVICES (Smooth scrolls to services)
            val exploreInteraction = remember { MutableInteractionSource() }
            val isExploreHovered by exploreInteraction.collectIsHoveredAsState()
            val isExplorePressed by exploreInteraction.collectIsPressedAsState()

            val exploreScale by animateFloatAsState(
                targetValue = if (isExplorePressed) 0.97f else if (isExploreHovered) 1.02f else 1f,
                animationSpec = tween(180),
                label = "explore_btn_scale"
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .graphicsLayer {
                        scaleX = exploreScale
                        scaleY = exploreScale
                    }
                    .shadow(12.dp, RoundedCornerShape(14.dp), spotColor = KairoNeonCyan.copy(alpha = 0.5f))
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = if (isExploreHovered) {
                                listOf(KairoNeonCyan, KairoPurpleBright)
                            } else {
                                listOf(KairoNeonCyan, KairoElectricBlue)
                            }
                        )
                    )
                    .border(
                        width = 1.dp,
                        color = Color.White.copy(alpha = if (isExploreHovered) 0.6f else 0.25f),
                        shape = RoundedCornerShape(14.dp)
                    )
                    .hoverable(interactionSource = exploreInteraction)
                    .clickable(
                        interactionSource = exploreInteraction,
                        indication = null
                    ) {
                        onExploreServicesClick()
                    }
                    .testTag("explore_services_button"),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "EXPLORE SERVICES",
                        color = KairoVoidBlack,
                        fontWeight = FontWeight.Black,
                        fontSize = 15.sp,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Scroll to services",
                        tint = KairoVoidBlack,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            // SECONDARY CTA: ORDER ON WHATSAPP
            val whatsappInteraction = remember { MutableInteractionSource() }
            val isWhatsappHovered by whatsappInteraction.collectIsHoveredAsState()
            val isWhatsappPressed by whatsappInteraction.collectIsPressedAsState()

            val whatsappScale by animateFloatAsState(
                targetValue = if (isWhatsappPressed) 0.97f else if (isWhatsappHovered) 1.02f else 1f,
                animationSpec = tween(180),
                label = "whatsapp_btn_scale"
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .graphicsLayer {
                        scaleX = whatsappScale
                        scaleY = whatsappScale
                    }
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(KairoWhatsAppGreen, KairoWhatsAppDark)
                        )
                    )
                    .border(
                        width = 1.dp,
                        color = Color.White.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(14.dp)
                    )
                    .hoverable(interactionSource = whatsappInteraction)
                    .clickable(
                        interactionSource = whatsappInteraction,
                        indication = null
                    ) {
                        IntentHelper.launchWhatsApp(
                            context = context,
                            customMessage = "Hello KAIRO DIGITAL, I would like to place an order."
                        )
                    }
                    .testTag("order_on_whatsapp_hero_button"),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Chat,
                        contentDescription = "WhatsApp Order",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "ORDER ON WHATSAPP",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        letterSpacing = 0.5.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun PillarBadge(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(KairoDeepNavy.copy(alpha = 0.7f))
            .border(1.dp, KairoBorderGlow, RoundedCornerShape(10.dp))
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = KairoNeonCyan,
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = title,
            color = KairoSilverLight,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
