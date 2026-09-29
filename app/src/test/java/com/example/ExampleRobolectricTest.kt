package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.model.KairoBrand
import com.example.model.KairoData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("KAIRO DIGITAL", appName)
    }

    @Test
    fun `verify kairo brand and services integrity`() {
        assertEquals("KAIRO DIGITAL SERVICES", KairoBrand.BRAND_NAME)
        assertEquals("03058368960", KairoBrand.WHATSAPP_DISPLAY)
        assertEquals("@aivideo2363", KairoBrand.TIKTOK_HANDLE)
        assertEquals(4, KairoData.services.size)
        assertEquals(5, KairoData.faqs.size)
        assertEquals(4, KairoData.whyChooseUs.size)
        assertEquals(3, KairoData.howToOrderSteps.size)
        assertEquals(4, KairoData.testimonials.size)

        // Verify testimonials consistency
        KairoData.testimonials.forEach {
            assertTrue(it.clientName.isNotEmpty())
            assertTrue(it.feedback.isNotEmpty())
            assertEquals(5, it.rating)
        }

        // Strict branding rule: Never write Cairo or KAIRO EDIT
        assertFalse(KairoBrand.BRAND_NAME.contains("Cairo", ignoreCase = true))
        assertFalse(KairoBrand.BRAND_NAME.contains("EDIT", ignoreCase = true))
        assertTrue(KairoBrand.BRAND_NAME.startsWith("KAIRO"))
    }
}
