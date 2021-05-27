package edu.uw.andir2.dotify.manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import edu.uw.andir2.dotify.PlayerActivity
import edu.uw.andir2.dotify.R
import kotlin.random.Random
import com.ericchee.songdataprovider.SongDataProvider

private const val NEW_SONG_CHANNEL_ID = "NEW_SONG_CHANNEL_ID"
private const val SONG_KEY = "song"

class SongNotificationManager(
    private val context: Context
    )  {
    private val notificationManager = NotificationManagerCompat.from(context)

    init {
        initNotificationChannel()
    }

    fun publishNewSongNotification() {

        var song = SongDataProvider.getAllSongs().random()

        val intent = Intent(context, PlayerActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(SONG_KEY, song)
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        // build information to show
        var builder = NotificationCompat.Builder(context, NEW_SONG_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_song)
            .setContentTitle(song.artist + "just released a new song!!!")
            .setContentText("Listen to " + song.title + " now on Dotify")
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // tell the os to publish the notification
        with(notificationManager) {
            // notificationId is a unique int for each notification that you must define
            val notificationId = Random.nextInt()
            notify(notificationId, builder.build())
        }
    }

    private fun initNotificationChannel() {
        initNewSongChannel()
    }

    private fun initNewSongChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Info about the channel
            val name = context.getString(R.string.new_songs)
            val descriptionText = context.getString(R.string.new_songs_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            // Create channel object
            val channel = NotificationChannel(NEW_SONG_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            // Tell the Android OS to create a channel
            notificationManager.createNotificationChannel(channel)
        }
    }


}