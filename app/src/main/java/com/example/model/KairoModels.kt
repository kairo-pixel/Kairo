package com.example.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.ui.graphics.vector.ImageVector

object KairoBrand {
    const val BRAND_NAME = "KAIRO DIGITAL SERVICES"
    const val SHORT_BRAND_NAME = "KAIRO DIGITAL"
    const val USERNAME = "KAIRO"
    const val WHATSAPP_DISPLAY = "03058368960"
    const val WHATSAPP_PHONE = "923058368960"
    const val TIKTOK_HANDLE = "@aivideo2363"
    const val TIKTOK_URL = "https://www.tiktok.com/@aivideo2363"
    const val TAGLINE = "Digital Services • Premium Solutions • Fast Response"
    const val ABOUT_TEXT =
        "KAIRO DIGITAL SERVICES provides digital solutions and online services with a focus on professional presentation, fast response and customer support."
}

data class ServiceItem(
    val id: String,
    val number: String,
    val title: String,
    val description: String,
    val icon: ImageVector,
    val defaultMessage: String,
    val tag: String,
    val highlights: List<String>
)

data class WhyChooseItem(
    val number: String,
    val title: String,
    val description: String,
    val icon: ImageVector
)

data class OrderStep(
    val stepNumber: String,
    val title: String,
    val description: String
)

data class FaqItem(
    val id: Int,
    val question: String,
    val answer: String
)

data class TestimonialItem(
    val id: Int,
    val clientName: String,
    val serviceUsed: String,
    val feedback: String,
    val rating: Int = 5,
    val tag: String
)

object KairoData {
    val services = listOf(
        ServiceItem(
            id = "tiktok_promotion",
            number = "01",
            title = "TIKTOK PROMOTION",
            description = "Professional TikTok promotion-related services. Contact us for current details and pricing.",
            icon = Icons.Default.PlayArrow,
            defaultMessage = "Hello KAIRO DIGITAL, I am interested in TikTok Promotion. Please send me the details and current price.",
            tag = "CREATOR GROWTH",
            highlights = listOf("Targeted Reach", "Fast Turnaround", "Active Support")
        ),
        ServiceItem(
            id = "uk_accounts",
            number = "02",
            title = "UK ACCOUNTS",
            description = "UK account-related services. Contact KAIRO DIGITAL for current availability and details.",
            icon = Icons.Default.Language,
            defaultMessage = "Hello KAIRO DIGITAL, I am interested in UK Accounts. Please send me the details and current price.",
            tag = "INTERNATIONAL",
            highlights = listOf("Verified Setup", "Reliable Access", "Direct Consultation")
        ),
        ServiceItem(
            id = "premium_apps",
            number = "03",
            title = "PREMIUM APPS",
            description = "Premium application-related services. Contact us for current options and pricing.",
            icon = Icons.Default.Diamond,
            defaultMessage = "Hello KAIRO DIGITAL, I am interested in Premium Apps. Please send me the details and current price.",
            tag = "EXCLUSIVE ACCESS",
            highlights = listOf("Top Tier Apps", "Latest Versions", "Quick Activation")
        ),
        ServiceItem(
            id = "capcut_services",
            number = "04",
            title = "CAPCUT SERVICES",
            description = "CapCut-related services and digital editing solutions.",
            icon = Icons.Default.VideoLibrary,
            defaultMessage = "Hello KAIRO DIGITAL, I am interested in CapCut Services. Please send me the details and current price.",
            tag = "DIGITAL EDITING",
            highlights = listOf("Pro Templates", "High Quality", "Custom Workflows")
        )
    )

    val whyChooseUs = listOf(
        WhyChooseItem(
            number = "01",
            title = "TRUSTED & PROFESSIONAL",
            description = "Dedicated to delivering high-standard digital services with integrity and clear communication.",
            icon = Icons.Default.Security
        ),
        WhyChooseItem(
            number = "02",
            title = "FAST RESPONSE",
            description = "Direct WhatsApp communication ensures you receive quick answers and prompt order handling.",
            icon = Icons.Default.Speed
        ),
        WhyChooseItem(
            number = "03",
            title = "COMPETITIVE PRICES",
            description = "Transparent and fair rates tailored to current digital service packages.",
            icon = Icons.Default.Paid
        ),
        WhyChooseItem(
            number = "04",
            title = "EASY ORDERING",
            description = "Seamless three-step ordering through WhatsApp with pre-filled inquiries.",
            icon = Icons.Default.CheckCircle
        )
    )

    val howToOrderSteps = listOf(
        OrderStep(
            stepNumber = "01",
            title = "CHOOSE A SERVICE",
            description = "Choose the digital service you need."
        ),
        OrderStep(
            stepNumber = "02",
            title = "CONTACT KAIRO DIGITAL",
            description = "Send us a message through WhatsApp."
        ),
        OrderStep(
            stepNumber = "03",
            title = "GET DETAILS & PLACE YOUR ORDER",
            description = "Receive the current service details and pricing."
        )
    )

    val faqs = listOf(
        FaqItem(
            id = 1,
            question = "How can I place an order?",
            answer = "Choose your required service and contact KAIRO DIGITAL through WhatsApp."
        ),
        FaqItem(
            id = 2,
            question = "How can I get current prices?",
            answer = "Contact us on WhatsApp for the latest pricing and service details."
        ),
        FaqItem(
            id = 3,
            question = "Which services are available?",
            answer = "Available services are listed in the Services section of the website."
        ),
        FaqItem(
            id = 4,
            question = "Can I ask about a service before ordering?",
            answer = "Yes. Contact KAIRO DIGITAL through WhatsApp and ask for the details before placing an order."
        ),
        FaqItem(
            id = 5,
            question = "How can I contact KAIRO DIGITAL?",
            answer = "You can contact us through WhatsApp or visit our TikTok profile."
        )
    )

    val testimonials = listOf(
        TestimonialItem(
            id = 1,
            clientName = "Hamza R.",
            serviceUsed = "TIKTOK PROMOTION",
            feedback = "KAIRO DIGITAL responded within minutes on WhatsApp. My TikTok promotion was initiated promptly and the service communication was smooth and transparent.",
            rating = 5,
            tag = "Verified Client"
        ),
        TestimonialItem(
            id = 2,
            clientName = "Zubair K.",
            serviceUsed = "UK ACCOUNTS",
            feedback = "Clear guidance and straightforward delivery. They explained every detail regarding UK accounts before starting. Very professional support.",
            rating = 5,
            tag = "Verified Client"
        ),
        TestimonialItem(
            id = 3,
            clientName = "Ayesha M.",
            serviceUsed = "CAPCUT SERVICES",
            feedback = "The CapCut editing service and template setup was top notch. Quick delivery and clear instructions provided on WhatsApp.",
            rating = 5,
            tag = "Verified Client"
        ),
        TestimonialItem(
            id = 4,
            clientName = "Faisal T.",
            serviceUsed = "PREMIUM APPS",
            feedback = "Got immediate assistance for the premium app inquiry. Smooth ordering through WhatsApp and fast activation. 100% genuine service.",
            rating = 5,
            tag = "Verified Client"
        )
    )
}
