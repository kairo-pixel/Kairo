package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.KairoBrand
import com.example.ui.theme.KairoBorderGlow
import com.example.ui.theme.KairoBorderPurple
import com.example.ui.theme.KairoCardDark
import com.example.ui.theme.KairoCyberPurple
import com.example.ui.theme.KairoDeepNavy
import com.example.ui.theme.KairoNeonCyan
import com.example.ui.theme.KairoSilverLight
import com.example.ui.theme.KairoSilverMuted
import com.example.ui.theme.KairoTextMuted
import com.example.ui.theme.KairoVoidBlack
import com.example.ui.theme.KairoWhatsAppGreen
import com.example.util.IntentHelper

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FooterSection(
    onNavigateToSection: (KairoSection) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(KairoVoidBlack)
            .border(
                width = 1.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        KairoBorderGlow,
                        KairoVoidBlack
                    )
                ),
                shape = RectangleShape
            )
            .padding(horizontal = 20.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Monogram logo
        Box(
            modifier = Modifier
                .size(42.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(KairoNeonCyan, KairoCyberPurple)
                    ),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(1.5.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .background(KairoVoidBlack, shape = RoundedCornerShape(9.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "K",
                    color = KairoNeonCyan,
                    fontWeight = FontWeight.Black,
                    fontSize = 24.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = KairoBrand.BRAND_NAME,
            color = KairoSilverLight,
            fontSize = 17.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 1.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = KairoBrand.TAGLINE,
            color = KairoNeonCyan,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        HorizontalDivider(color = KairoBorderGlow, thickness = 1.dp)

        Spacer(modifier = Modifier.height(20.dp))

        // Quick Links Title
        Text(
            text = "QUICK LINKS",
            color = KairoSilverMuted,
            fontSize = 11.sp,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Quick Links Row
        FlowRow(
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            val navItems = listOf(
                KairoSection.HOME to "Home",
                KairoSection.SERVICES to "Services",
                KairoSection.ABOUT to "About",
                KairoSection.WHY_US to "Why Us",
                KairoSection.TESTIMONIALS to "Reviews",
                KairoSection.FAQ to "FAQ",
                KairoSection.CONTACT to "Contact"
            )

            navItems.forEach { (section, label) ->
                Text(
                    text = label,
                    color = KairoSilverLight,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .clickable { onNavigateToSection(section) }
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Social Links (TikTok & WhatsApp)
        Text(
            text = "SOCIAL & CHANNELS",
            color = KairoSilverMuted,
            fontSize = 11.sp,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // WhatsApp Button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(KairoCardDark)
                    .border(1.dp, KairoWhatsAppGreen.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                    .clickable {
                        IntentHelper.launchWhatsApp(context, "Hello KAIRO DIGITAL, I found you on your platform.")
                    }
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Chat,
                    contentDescription = "WhatsApp",
                    tint = KairoWhatsAppGreen,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "WhatsApp",
                    color = KairoSilverLight,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // TikTok Button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(KairoCardDark)
                    .border(1.dp, KairoCyberPurple.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                    .clickable {
                        IntentHelper.launchTikTok(context)
                    }
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "TikTok",
                    tint = KairoCyberPurple,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "TikTok",
                    color = KairoSilverLight,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Copyright Notice
        Text(
            text = "© 2026 KAIRO DIGITAL SERVICES. All rights reserved.",
            color = KairoTextMuted,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}
