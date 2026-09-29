package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.KairoData
import com.example.model.TestimonialItem
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
import kotlinx.coroutines.launch

@Composable
fun TestimonialsSection(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val testimonials = KairoData.testimonials
    val pagerState = rememberPagerState(pageCount = { testimonials.size })
    val coroutineScope = rememberCoroutineScope()

    val infiniteTransition = rememberInfiniteTransition(label = "testimonial_glow")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(2400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_testimonial"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        // Tag
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(KairoDeepNavy)
                .border(1.dp, KairoBorderGlow, RoundedCornerShape(20.dp))
                .padding(horizontal = 12.dp, vertical = 5.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .background(KairoNeonCyan, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "CLIENT EXPERIENCES",
                color = KairoNeonCyan,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 1.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Heading
        Text(
            text = "WHAT CLIENTS SAY",
            color = KairoSilverLight,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 0.5.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Direct feedback from clients who ordered our digital services.",
            color = KairoSilverMuted,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Carousel Container
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("testimonials_carousel")
        ) { page ->
            TestimonialCard(
                testimonial = testimonials[page],
                pulseAlpha = pulseAlpha
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Navigation Controls: Prev, Dots, Next
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Prev Button
            IconButton(
                onClick = {
                    if (pagerState.currentPage > 0) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    } else {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(testimonials.lastIndex)
                        }
                    }
                },
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(KairoCardDark)
                    .border(1.dp, KairoBorderGlow, CircleShape)
                    .testTag("testimonial_prev_btn")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Previous Testimonial",
                    tint = KairoNeonCyan,
                    modifier = Modifier.size(18.dp)
                )
            }

            // Indicator Dots
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                testimonials.forEachIndexed { index, _ ->
                    val isSelected = pagerState.currentPage == index
                    val dotWidth by animateDpAsState(
                        targetValue = if (isSelected) 24.dp else 8.dp,
                        label = "dot_width"
                    )

                    Box(
                        modifier = Modifier
                            .height(8.dp)
                            .width(dotWidth)
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                if (isSelected) {
                                    Brush.horizontalGradient(
                                        listOf(KairoNeonCyan, KairoCyberPurple)
                                    )
                                } else {
                                    Brush.linearGradient(
                                        listOf(KairoDeepNavy, KairoCardElevated)
                                    )
                                }
                            )
                            .clickable {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            }
                    )
                }
            }

            // Next Button
            IconButton(
                onClick = {
                    if (pagerState.currentPage < testimonials.lastIndex) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(0)
                        }
                    }
                },
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(KairoCardDark)
                    .border(1.dp, KairoBorderGlow, CircleShape)
                    .testTag("testimonial_next_btn")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Next Testimonial",
                    tint = KairoNeonCyan,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // WhatsApp Inquire / Feedback Prompt
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(KairoDeepNavy.copy(alpha = 0.6f))
                .border(1.dp, KairoBorderPurple, RoundedCornerShape(12.dp))
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Have feedback or a new inquiry?",
                        color = KairoSilverLight,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Message KAIRO DIGITAL directly on WhatsApp.",
                        color = KairoSilverMuted,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(KairoWhatsAppGreen, KairoWhatsAppDark)
                            )
                        )
                        .clickable {
                            IntentHelper.launchWhatsApp(
                                context,
                                "Hello KAIRO DIGITAL, I have feedback / inquiry regarding your services."
                            )
                        }
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Chat,
                            contentDescription = "WhatsApp",
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "CHAT",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            letterSpacing = 0.5.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TestimonialCard(
    testimonial: TestimonialItem,
    pulseAlpha: Float
) {
    val cardShape = RoundedCornerShape(20.dp)

    Card(
        shape = cardShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(10.dp, cardShape, spotColor = KairoNeonCyan.copy(alpha = 0.25f))
            .border(
                width = 1.2.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        KairoNeonCyan.copy(alpha = pulseAlpha),
                        KairoCyberPurple.copy(alpha = 0.6f),
                        KairoElectricBlue.copy(alpha = 0.4f)
                    )
                ),
                shape = cardShape
            )
            .testTag("testimonial_card_${testimonial.id}")
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            KairoCardElevated.copy(alpha = 0.88f),
                            KairoDeepNavy.copy(alpha = 0.95f)
                        )
                    )
                )
                .drawBehind {
                    // Ambient glow around top right
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                KairoCyberPurple.copy(alpha = 0.15f),
                                Color.Transparent
                            ),
                            center = Offset(size.width - 40.dp.toPx(), 40.dp.toPx()),
                            radius = 90.dp.toPx()
                        ),
                        radius = 90.dp.toPx(),
                        center = Offset(size.width - 40.dp.toPx(), 40.dp.toPx())
                    )
                }
                .padding(22.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Top Row: Quote Icon + Rating Stars
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        KairoNeonCyan.copy(alpha = 0.2f),
                                        KairoCyberPurple.copy(alpha = 0.3f)
                                    )
                                )
                            )
                            .border(1.dp, KairoBorderGlow, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.FormatQuote,
                            contentDescription = "Quote",
                            tint = KairoNeonCyan,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    // 5-Star Indicator
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(testimonial.rating) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = KairoNeonCyan,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Testimonial quote text
                Text(
                    text = "“${testimonial.feedback}”",
                    color = KairoSilverLight,
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Bottom Client Metadata Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Avatar Monogram
                        val initials = testimonial.clientName.take(2).uppercase()
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(KairoNeonCyan, KairoCyberPurple)
                                    )
                                )
                                .padding(1.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(38.dp)
                                    .background(KairoVoidBlack, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = initials,
                                    color = KairoNeonCyan,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = testimonial.clientName,
                                    color = KairoSilverLight,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Verified",
                                    tint = KairoNeonCyan,
                                    modifier = Modifier.size(13.dp)
                                )
                            }
                            Text(
                                text = testimonial.tag,
                                color = KairoTextMuted,
                                fontSize = 11.sp
                            )
                        }
                    }

                    // Service Used Pill
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(KairoDeepNavy)
                            .border(1.dp, KairoBorderPurple, RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = testimonial.serviceUsed,
                            color = KairoCyberPurple,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
        }
    }
}
