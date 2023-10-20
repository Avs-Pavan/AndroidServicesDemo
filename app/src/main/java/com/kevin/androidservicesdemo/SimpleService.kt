package com.kevin.androidservicesdemo

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import android.util.Log

class SimpleService : Service() {

    // MediaPlayer is used to play music
    private lateinit var mediaPlayer: MediaPlayer

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.d("SimpleService", "onStartCommand: ")

        // Play music
        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
        mediaPlayer.isLooping = true
        mediaPlayer.start()

        // START_STICKY is used to restart the service if it is killed by the system
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("SimpleService", "onCreate: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop music before destroying the service
        mediaPlayer.stop()
        Log.d("SimpleService", "onDestroy: ")
    }


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}