package edu.uw.andir2.dotify.manager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import edu.uw.andir2.dotify.DotifyApplication

class SongWorker(
    private val context: Context,
    workerParamters: WorkerParameters
): CoroutineWorker(context, workerParamters) {

    private val dotifyApp by lazy { context.applicationContext as DotifyApplication }
    private val songNotificationManager by lazy { dotifyApp.SongNotificationManager }

    override suspend fun doWork(): Result {
        songNotificationManager.publishNewSongNotification()
        Log.i("SongSyncWorker", "syncing song now")

        return Result.success()
    }
}