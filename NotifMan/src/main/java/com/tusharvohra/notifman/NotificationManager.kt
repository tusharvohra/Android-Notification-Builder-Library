package com.tusharvohra.notifman

import android.app.Notification
import android.app.NotificationChannel
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

/**
 * Created by tusharvohra on 29/9/20.
 */
class NotificationManager {

    private var notificationChannelId: String? = null
    private var notificationChannelName: String? = null

    private var notificationTitle: String? = null
    private var notificationContent: String? = null
    private var smallIcon: Int? = null

    private var htmlTitle: Spanned? = null
    private var htmlContent: Spanned? = null

    private var bigPicture: Boolean = false
    private var largeIcon: Bitmap? = null
    private var largePicture: Bitmap? = null

    var context: Context

    private constructor(
        context: Context,
        notificationChannelId: String?,
        notificationChannelName: String?,
        notificationTitle: String?,
        notificationContent: String?,
        smallIcon: Int?,
        htmlTitle: Spanned?,
        htmlContent: Spanned?,
        bigPicture: Boolean,
        largeIcon: Bitmap?,
        largePicture: Bitmap?
    ) {
        this.notificationChannelId = notificationChannelId
        this.notificationChannelName = notificationChannelName
        this.notificationTitle = notificationTitle
        this.notificationContent = notificationContent
        this.smallIcon = smallIcon
        this.context = context
        this.htmlTitle = htmlTitle
        this.htmlContent = htmlContent
        this.bigPicture = bigPicture
        this.largeIcon = largeIcon
        this.largePicture = largePicture
    }

    data class Builder(
        // mandatory
        var context: Context,
        var notificationChannelId: String,
        var notificationChannelName: String
    ) {

        //optional
        private var notificationTitle: String? = null
        private var notificationContent: String? = null
        private var smallIcon: Int? = null
        private var htmlTitle: Spanned? = null
        private var htmlContent: Spanned? = null
        private var bigPicture: Boolean = false
        private var largeIcon: Bitmap? = null
        private var largePicture: Bitmap? = null


        fun notificationTitle(notificationTitle: String) =
            apply { this.notificationTitle = notificationTitle }

        fun notificationContent(notificationContent: String) =
            apply { this.notificationContent = notificationContent }

        fun smallIcon(smallIcon: Int) = apply { this.smallIcon = smallIcon }

        @RequiresApi(Build.VERSION_CODES.N)
        fun htmlTitle(htmlTitle: String) =
            apply { this.htmlTitle = Html.fromHtml(htmlTitle, Html.FROM_HTML_MODE_LEGACY) }

        @RequiresApi(Build.VERSION_CODES.N)
        fun htmlContent(htmlContent: String) =
            apply { this.htmlTitle = Html.fromHtml(htmlContent, Html.FROM_HTML_MODE_LEGACY) }

        fun bigPicture(largeIcon: Bitmap, largePicture: Bitmap) = apply {
            this.bigPicture = true
            this.largeIcon = largeIcon
            this.largePicture = largePicture
        }

        fun build(): Notification {
            val notificationManager =
                NotificationManager(
                    context,
                    notificationChannelId,
                    notificationChannelName,
                    notificationTitle,
                    notificationContent,
                    smallIcon,
                    htmlTitle,
                    htmlContent,
                    bigPicture,
                    largeIcon,
                    largePicture
                )
            return notificationManager.createNotification()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel() {

        val notificationChannel = NotificationChannel(
            notificationChannelId,
            notificationChannelName,
            android.app.NotificationManager.IMPORTANCE_NONE
        )

        notificationChannel.enableLights(true)
        notificationChannel.enableVibration(true)

        val manager =
            (context.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager)
        manager.createNotificationChannel(notificationChannel)
    }

    fun createNotification(): Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            createNotificationChannel()
        }
        val notificationBuilder = NotificationCompat.Builder(context, notificationChannelId!!)
        val notification: Notification?

        if (bigPicture) {
            notification = notificationBuilder
                .setOngoing(true)
                .setSmallIcon(smallIcon!!)
                .setContentTitle(notificationTitle)
                .setContentText(notificationContent)
                .setLargeIcon(largeIcon)
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(largePicture)
                        .bigLargeIcon(null)
                )
                .build()
        } else {
            notification = notificationBuilder
                .setOngoing(true)
                .setSmallIcon(smallIcon!!)
                .setContentTitle(notificationTitle)
                .setContentText(notificationContent)
                .build()
        }
        return notification!!
    }

}