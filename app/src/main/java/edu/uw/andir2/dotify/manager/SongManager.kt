package edu.uw.andir2.dotify.manager

import android.content.Context
import android.text.format.Time
import androidx.work.*
import java.util.concurrent.TimeUnit

private const val SONG_WORK_TAG = "SONG_SYNC_TAG"

class SongManager(context: Context) {
    private val workManager: WorkManager = WorkManager.getInstance()

    fun syncSongs() {

        if (isSongSyncRunning()) {
            return
        }
        //val request = OneTimeWorkRequestBuilder<SongWorker>()
        val request = PeriodicWorkRequestBuilder<SongWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .addTag(SONG_WORK_TAG)
            .build()

        workManager.enqueue(request)
    }

    fun stopPeriodicRefresh() {
        workManager.cancelAllWorkByTag(SONG_WORK_TAG)
    }

    private fun isSongSyncRunning(): Boolean {
        return workManager.getWorkInfosByTag(SONG_WORK_TAG).get().any {
            when(it.state) {
                WorkInfo.State.RUNNING,
                WorkInfo.State.ENQUEUED -> true
                else -> false
            }
        }
    }
}
