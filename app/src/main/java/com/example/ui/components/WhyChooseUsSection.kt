package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.KairoData
import com.example.model.WhyChooseItem
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

@Composable
fun WhyChooseUsSection(
    isWideScreen: Boolean,
    modifier: Modifier = Modifier
) {
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
                text = "STANDOUT ADVANTAGES",
                color = KairoNeonCyan,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 1.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "WHY CHOOSE US",
            color = KairoSilverLight,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 0.5.sp
        )

        Spacer(modifier = Modifier.height(18.dp))

        if (isWideScreen) {
            // 2x2 Grid for wide screens / tablets
            val items = KairoData.whyChooseUs
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    WhyChooseCard(item = items[0], modifier = Modifier.weight(1f))
                    WhyChooseCard(item = items[1], modifier = Modifier.weight(1f))
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    WhyChooseCard(item = items[2], modifier = Modifier.weight(1f))
                    WhyChooseCard(item = items[3], modifier = Modifier.weight(1f))
                }
            }
        } else {
            // Stacked vertically on mobile
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                KairoData.whyChooseUs.forEach { item ->
                    WhyChooseCard(item = item)
                }
            }
        }
    }
}

@Composable
fun WhyChooseCard(
    item: WhyChooseItem,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = KairoCardDark.copy(alpha = 0.85f)),
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        KairoNeonCyan.copy(alpha = 0.35f),
                        KairoCyberPurple.copy(alpha = 0.2f)
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Neon Number
                Text(
                    text = item.number,
                    color = KairoNeonCyan,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily.Monospace
                )

                // Icon Halo
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(KairoDeepNavy)
                        .border(1.dp, KairoBorderGlow, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = KairoNeonCyan,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = item.title,
                color = KairoSilverLight,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = item.description,
                color = KairoSilverMuted,
                fontSize = 13.sp,
                lineHeight = 18.sp
            )
        }
    }
}
