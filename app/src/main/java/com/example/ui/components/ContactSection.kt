package com.example.ui.components

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.KairoBrand
import com.example.ui.theme.KairoBorderGlow
import com.example.ui.theme.KairoBorderPurple
import com.example.ui.theme.KairoCardDark
import com.example.ui.theme.KairoCardElevated
import com.example.ui.theme.KairoCyberPurple
import com.example.ui.theme.KairoDeepNavy
import com.example.ui.theme.KairoElectricBlue
import com.example.ui.theme.KairoNeonCyan
import com.example.ui.theme.KairoSilverLight
import com.example.ui.theme.KairoSilverMuted
import com.example.ui.theme.KairoTextMuted
import com.example.ui.theme.KairoVoidBlack
import com.example.ui.theme.KairoWhatsAppDark
import com.example.ui.theme.KairoWhatsAppGreen
import com.example.util.IntentHelper

@Composable
fun ContactSection(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp)
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
                text = "DIRECT COMMUNICATION",
                color = KairoNeonCyan,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 1.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "CONTACT",
            color = KairoSilverLight,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 0.5.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Have a question about a service? Contact KAIRO DIGITAL for details.",
            color = KairoSilverMuted,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Premium Contact Info Box
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = KairoCardDark.copy(alpha = 0.9f)),
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            KairoNeonCyan.copy(alpha = 0.45f),
                            KairoCyberPurple.copy(alpha = 0.35f)
                        )
                    ),
                    shape = RoundedCornerShape(18.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = KairoBrand.BRAND_NAME,
                    color = KairoSilverLight,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 0.5.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // WhatsApp Row
                ContactDetailRow(
                    icon = Icons.Default.Phone,
                    iconTint = KairoWhatsAppGreen,
                    label = "WhatsApp",
                    value = KairoBrand.WHATSAPP_DISPLAY,
                    onCopy = {
                        IntentHelper.copyToClipboard(context, KairoBrand.WHATSAPP_DISPLAY, "WhatsApp number copied")
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Username Row
                ContactDetailRow(
                    icon = Icons.Default.Person,
                    iconTint = KairoNeonCyan,
                    label = "Username",
                    value = KairoBrand.USERNAME,
                    onCopy = {
                        IntentHelper.copyToClipboard(context, KairoBrand.USERNAME, "Username copied")
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                // TikTok Row
                ContactDetailRow(
                    icon = Icons.Default.PlayArrow,
                    iconTint = KairoCyberPurple,
                    label = "TikTok",
                    value = KairoBrand.TIKTOK_HANDLE,
                    onCopy = {
                        IntentHelper.copyToClipboard(context, KairoBrand.TIKTOK_HANDLE, "TikTok handle copied")
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Buttons: CHAT ON WHATSAPP & VISIT TIKTOK
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // CHAT ON WHATSAPP
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(KairoWhatsAppGreen, KairoWhatsAppDark)
                                )
                            )
                            .clickable {
                                IntentHelper.launchWhatsApp(
                                    context = context,
                                    customMessage = "Hello KAIRO DIGITAL, I have an inquiry about your digital services."
                                )
                            }
                            .testTag("contact_whatsapp_button"),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Chat,
                                contentDescription = "WhatsApp",
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "CHAT ON WHATSAPP",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                letterSpacing = 0.5.sp
                            )
                        }
                    }

                    // VISIT TIKTOK
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(KairoCardElevated)
                            .border(1.dp, KairoBorderPurple, RoundedCornerShape(12.dp))
                            .clickable {
                                IntentHelper.launchTikTok(context)
                            }
                            .testTag("contact_tiktok_button"),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "TikTok",
                                tint = KairoNeonCyan,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "VISIT TIKTOK",
                                color = KairoSilverLight,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                letterSpacing = 0.5.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ContactDetailRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    label: String,
    value: String,
    onCopy: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(KairoDeepNavy)
            .border(1.dp, KairoBorderGlow, RoundedCornerShape(10.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(iconTint.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(16.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = label,
                    color = KairoTextMuted,
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace
                )
                Text(
                    text = value,
                    color = KairoSilverLight,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        IconButton(
            onClick = onCopy,
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ContentCopy,
                contentDescription = "Copy $label",
                tint = KairoSilverMuted,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
