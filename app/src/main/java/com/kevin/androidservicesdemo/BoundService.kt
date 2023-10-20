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
        Log.e(  "BoundService", "onCreate: ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("BoundService", "onStartCommand: ")
        return super.onStartCommand(intent, flags, startId)
    }



    override fun onBind(intent: Intent?): IBinder? {
        Log.e("BoundService", "onBind: ")
        return myBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e("BoundService", "onUnbind: ")
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("BoundService", "onDestroy: ")
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.e("BoundService", "onRebind: ")
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

