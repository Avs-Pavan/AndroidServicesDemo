package com.kevin.androidservicesdemo

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class BoundService : Service() {

    private lateinit var myBinder: MyBinder

    private var myRandomNumber: Int = 0
    override fun onCreate() {
        super.onCreate()
        myBinder = MyBinder()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return myBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return true
    }


    fun start() {
        Log.e("BoundService", "start: ")
    }

    fun getRandomNumber(): Int {
        Log.e("BoundService", "getRandomNumber: ")
        myRandomNumber = (0..100).random()
        return myRandomNumber
    }

    inner class MyBinder : Binder() {
        fun getService(): BoundService {
            return this@BoundService
        }
    }
}

