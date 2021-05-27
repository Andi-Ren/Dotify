package edu.uw.andir2.dotify

import android.app.Application
import edu.uw.andir2.dotify.manager.SongManager
import edu.uw.andir2.dotify.manager.SongNotificationManager

class DotifyApplication: Application() {
    lateinit var dataRepository: DataRepository
    lateinit var SongManager: SongManager
    lateinit var SongNotificationManager: SongNotificationManager

    override fun onCreate() {
        super.onCreate()
        dataRepository = DataRepository()
        this.SongManager = SongManager(this)
        this.SongNotificationManager = SongNotificationManager(this)
    }
}

