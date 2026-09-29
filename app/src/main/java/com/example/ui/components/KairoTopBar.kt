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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.KairoBrand
import com.example.ui.theme.*
import com.example.util.IntentHelper
import kotlinx.coroutines.launch

enum class KairoSection(val title: String) {
    HOME("Home"),
    SERVICES("Services"),
    ABOUT("About"),
    WHY_US("Why Us"),
    HOW_TO_ORDER("Order Flow"),
    TESTIMONIALS("Reviews"),
    FAQ("FAQ"),
    CONTACT("Contact")
}

@androidx.compose.material3.ExperimentalMaterial3Api
@Composable
fun KairoTopBar(
    isWideScreen: Boolean,
    onNavigateToSection: (KairoSection) -> Unit,
    modifier: Modifier = Modifier
) {
    var isMobileMenuOpen by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Surface(
        color = KairoDeepNavy.copy(alpha = 0.94f),
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        KairoNeonCyan.copy(alpha = 0.35f),
                        KairoCyberPurple.copy(alpha = 0.15f)
                    )
                ),
                shape = RectangleShape
            ),
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Brand Logo & Text
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onNavigateToSection(KairoSection.HOME) }
                    .padding(vertical = 4.dp, horizontal = 4.dp)
            ) {
                // Futuristic Glowing Monogram "K"
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(KairoNeonCyan, KairoCyberPurple)
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(1.5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(36.dp)
                            .background(KairoVoidBlack, shape = RoundedCornerShape(7.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "K",
                            color = KairoNeonCyan,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 20.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        text = KairoBrand.SHORT_BRAND_NAME,
                        color = KairoSilverLight,
                        fontWeight = FontWeight.Black,
                        fontSize = 17.sp,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "DIGITAL SERVICES",
                        color = KairoNeonCyan,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 9.sp,
                        letterSpacing = 1.2.sp
                    )
                }
            }

            if (isWideScreen) {
                // Desktop navigation links
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    listOf(
                        KairoSection.HOME,
                        KairoSection.SERVICES,
                        KairoSection.ABOUT,
                        KairoSection.WHY_US,
                        KairoSection.TESTIMONIALS,
                        KairoSection.FAQ,
                        KairoSection.CONTACT
                    ).forEach { section ->
                        Text(
                            text = section.title,
                            color = KairoSilverMuted,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .clickable { onNavigateToSection(section) }
                                .padding(horizontal = 8.dp, vertical = 6.dp)
                        )
                    }

                    // Order on WhatsApp mini button
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(KairoNeonCyan, KairoElectricBlue)
                                )
                            )
                            .clickable {
                                IntentHelper.launchWhatsApp(context, "Hello KAIRO DIGITAL, I want to order a service.")
                            }
                            .padding(horizontal = 14.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "ORDER NOW",
                            color = KairoVoidBlack,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            letterSpacing = 0.5.sp
                        )
                    }
                }
            } else {
                // Mobile Hamburger Button
                IconButton(
                    onClick = { isMobileMenuOpen = true },
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(KairoCardDark)
                        .border(1.dp, KairoBorderGlow, RoundedCornerShape(8.dp))
                        .testTag("hamburger_menu_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Open Navigation Menu",
                        tint = KairoNeonCyan
                    )
                }
            }
        }
    }

    if (isMobileMenuOpen) {
        MobileNavigationDrawer(
            onDismiss = { isMobileMenuOpen = false },
            onSelectSection = { section ->
                isMobileMenuOpen = false
                onNavigateToSection(section)
            }
        )
    }
}

@androidx.compose.material3.ExperimentalMaterial3Api
@Composable
private fun MobileNavigationDrawer(
    onDismiss: () -> Unit,
    onSelectSection: (KairoSection) -> Unit
) {
    val context = LocalContext.current

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = KairoDeepNavy,
        contentColor = KairoSilverLight,
        dragHandle = null,
        modifier = Modifier.padding(top = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = KairoBrand.BRAND_NAME,
                        color = KairoSilverLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Navigation Menu",
                        color = KairoNeonCyan,
                        fontSize = 12.sp
                    )
                }

                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Menu",
                        tint = KairoSilverMuted
                    )
                }
            }

            HorizontalDivider(
                color = KairoBorderGlow,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            val menuItems = listOf(
                KairoSection.HOME to "Home",
                KairoSection.SERVICES to "Services",
                KairoSection.ABOUT to "About",
                KairoSection.WHY_US to "Why Us",
                KairoSection.HOW_TO_ORDER to "How to Order",
                KairoSection.TESTIMONIALS to "What Clients Say",
                KairoSection.FAQ to "FAQ",
                KairoSection.CONTACT to "Contact"
            )

            menuItems.forEach { (section, label) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onSelectSection(section) }
                        .padding(vertical = 12.dp, horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(KairoNeonCyan, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = label,
                        color = KairoSilverLight,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Action: Order on WhatsApp
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(KairoWhatsAppGreen, KairoWhatsAppDark)
                        )
                    )
                    .clickable {
                        onDismiss()
                        IntentHelper.launchWhatsApp(context, "Hello KAIRO DIGITAL, I want to order a service.")
                    }
                    .padding(vertical = 14.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "ORDER ON WHATSAPP",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    letterSpacing = 0.5.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Action: Visit TikTok
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(KairoCardDark)
                    .border(1.dp, KairoBorderPurple, RoundedCornerShape(12.dp))
                    .clickable {
                        onDismiss()
                        IntentHelper.launchTikTok(context)
                    }
                    .padding(vertical = 14.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "VISIT TIKTOK (@aivideo2363)",
                    color = KairoSilverLight,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
