package com.example.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.model.KairoBrand
import java.net.URLEncoder

object IntentHelper {

    fun launchWhatsApp(context: Context, customMessage: String? = null) {
        val message = customMessage ?: "Hello KAIRO DIGITAL, I have an inquiry about your digital services."
        val encodedMessage = try {
            URLEncoder.encode(message, "UTF-8")
        } catch (_: Exception) {
            message.replace(" ", "%20")
        }

        val url = "https://wa.me/${KairoBrand.WHATSAPP_PHONE}?text=$encodedMessage"
        val webUri = Uri.parse(url)

        try {
            val intent = Intent(Intent.ACTION_VIEW, webUri).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (_: Exception) {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, webUri).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(browserIntent)
            } catch (_: Exception) {
                copyToClipboard(context, KairoBrand.WHATSAPP_DISPLAY, "WhatsApp number copied")
            }
        }
    }

    fun launchTikTok(context: Context) {
        val url = KairoBrand.TIKTOK_URL
        try {
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (_: Exception) {
            copyToClipboard(context, KairoBrand.TIKTOK_HANDLE, "TikTok handle copied")
        }
    }

    fun copyToClipboard(context: Context, text: String, label: String = "Copied") {
        try {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(label, text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, "$label: $text", Toast.LENGTH_SHORT).show()
        } catch (_: Exception) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }
}
