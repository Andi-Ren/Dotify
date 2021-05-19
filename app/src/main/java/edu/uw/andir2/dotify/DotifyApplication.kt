package edu.uw.andir2.dotify

import android.app.Application


class DotifyApplication: Application() {
    lateinit var dataRepository: DataRepository

    override fun onCreate() {
        super.onCreate()
        dataRepository = DataRepository()
    }
}