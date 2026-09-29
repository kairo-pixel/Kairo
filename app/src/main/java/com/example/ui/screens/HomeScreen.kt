package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.KairoData
import com.example.ui.components.AboutSection
import com.example.ui.components.ContactSection
import com.example.ui.components.FaqSection
import com.example.ui.components.FloatingWhatsAppButton
import com.example.ui.components.FooterSection
import com.example.ui.components.FuturisticBackground
import com.example.ui.components.HeroSection
import com.example.ui.components.HowToOrderSection
import com.example.ui.components.KairoSection
import com.example.ui.components.KairoTopBar
import com.example.ui.components.ServiceCard
import com.example.ui.components.TestimonialsSection
import com.example.ui.components.WhyChooseUsSection
import com.example.ui.theme.KairoBorderGlow
import com.example.ui.theme.KairoDeepNavy
import com.example.ui.theme.KairoNeonCyan
import com.example.ui.theme.KairoSilverLight
import com.example.ui.theme.KairoVoidBlack
import kotlinx.coroutines.launch

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isWideScreen = maxWidth >= 650.dp

        Scaffold(
            topBar = {
                KairoTopBar(
                    isWideScreen = isWideScreen,
                    onNavigateToSection = { section ->
                        val targetIndex = when (section) {
                            KairoSection.HOME -> 0
                            KairoSection.SERVICES -> 1
                            KairoSection.ABOUT -> 2
                            KairoSection.WHY_US -> 3
                            KairoSection.HOW_TO_ORDER -> 4
                            KairoSection.TESTIMONIALS -> 5
                            KairoSection.FAQ -> 6
                            KairoSection.CONTACT -> 7
                        }
                        coroutineScope.launch {
                            listState.animateScrollToItem(targetIndex)
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingWhatsAppButton()
            },
            containerColor = KairoVoidBlack
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // Futuristic Glowing Cyber Background
                FuturisticBackground()

                // Content centered on wide screens with maximum constraint
                LazyColumn(
                    state = listState,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Item 0: Hero Section (HOME)
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .widthIn(max = 900.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            HeroSection(
                                onExploreServicesClick = {
                                    coroutineScope.launch {
                                        listState.animateScrollToItem(1)
                                    }
                                }
                            )
                        }
                    }

                    // Item 1: SERVICES Section
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .widthIn(max = 900.dp)
                                .padding(horizontal = 20.dp, vertical = 24.dp)
                        ) {
                            // Section Tag
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
                                    text = "OFFICIAL CATALOG",
                                    color = KairoNeonCyan,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Monospace,
                                    letterSpacing = 1.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "OUR SERVICES",
                                color = KairoSilverLight,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 0.5.sp
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "Select your desired digital service and contact us directly on WhatsApp.",
                                color = KairoSilverLight.copy(alpha = 0.75f),
                                fontSize = 14.sp
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            if (isWideScreen) {
                                // 2-Column Grid on Wide screens
                                val services = KairoData.services
                                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                                    ) {
                                        ServiceCard(service = services[0], modifier = Modifier.weight(1f))
                                        ServiceCard(service = services[1], modifier = Modifier.weight(1f))
                                    }
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                                    ) {
                                        ServiceCard(service = services[2], modifier = Modifier.weight(1f))
                                        ServiceCard(service = services[3], modifier = Modifier.weight(1f))
                                    }
                                }
                            } else {
                                // Stacked Vertically on Mobile
                                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                                    KairoData.services.forEach { service ->
                                        ServiceCard(service = service)
                                    }
                                }
                            }
                        }
                    }

                    // Item 2: ABOUT Section
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .widthIn(max = 900.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            AboutSection()
                        }
                    }

                    // Item 3: WHY CHOOSE US Section
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .widthIn(max = 900.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            WhyChooseUsSection(isWideScreen = isWideScreen)
                        }
                    }

                    // Item 4: HOW TO ORDER Section
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .widthIn(max = 900.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            HowToOrderSection()
                        }
                    }

                    // Item 5: WHAT CLIENTS SAY (Testimonials Carousel)
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .widthIn(max = 900.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            TestimonialsSection()
                        }
                    }

                    // Item 6: FAQ Section
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .widthIn(max = 900.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            FaqSection()
                        }
                    }

                    // Item 7: CONTACT Section
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .widthIn(max = 900.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            ContactSection()
                        }
                    }

                    // Item 8: FOOTER Section
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .widthIn(max = 900.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            FooterSection(
                                onNavigateToSection = { section ->
                                    val targetIndex = when (section) {
                                        KairoSection.HOME -> 0
                                        KairoSection.SERVICES -> 1
                                        KairoSection.ABOUT -> 2
                                        KairoSection.WHY_US -> 3
                                        KairoSection.HOW_TO_ORDER -> 4
                                        KairoSection.TESTIMONIALS -> 5
                                        KairoSection.FAQ -> 6
                                        KairoSection.CONTACT -> 7
                                    }
                                    coroutineScope.launch {
                                        listState.animateScrollToItem(targetIndex)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
