package com.kevin.androidservicesdemo

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.kevin.androidservicesdemo.databinding.ActivityMainBinding
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {

    // ViewBinding
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    // Service Intent
    private val serviceIntent by lazy { Intent(this, SimpleService::class.java) }


    // Bound service
    private var isBound = false
    private var boundService: BoundService? = null

    // Intent for Bound Service
    private val boundServiceIntent by lazy { Intent(this, BoundService::class.java) }


    // Service Connection
    private val boundServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            // Cast IBinder to MyBinder
            val binder = service as BoundService.MyBinder
            // Get Service
            boundService = binder.getService()
            // Set isBound to true
            isBound = true
            binding.randomTxt.text = "Service is bound"
            Log.e("MainActivity", "onServiceConnected: ")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            boundService = null
            Log.e("MainActivity", "onServiceDisconnected: ")
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Init Views
        initViews()
    }

    // Init Views
    private fun initViews() {

        // Start and Stop Music
        binding.startBtn.setOnClickListener {
            startMusic()
        }
        binding.stopBtn.setOnClickListener {
            stopMusic()
        }

        // Bind and Unbind Service
        binding.startBoundBtn.setOnClickListener {
            // Bind Service
            bindService(boundServiceIntent, boundServiceConnection, BIND_AUTO_CREATE)

        }


        // Fetch Random Number
        binding.fetchRandomBtn.setOnClickListener {
            // check if service is bound
            if (!isBound) {
                binding.randomTxt.text = "Service is not bound"
                return@setOnClickListener
            }
            binding.randomTxt.text =
                "Random number from service is ${boundService?.getRandomNumber().toString()}"
        }

        // Unbind Service
        binding.unbindBtn.setOnClickListener {
            // check if service is bound
            if (!isBound) {
               binding.randomTxt.text = "Service is not bound/already unbound"
                return@setOnClickListener
            }
            unbindService(boundServiceConnection)
            isBound = false
            binding.randomTxt.text = "Service is unbound"
        }
    }

    private fun startMusic() {
        // Start Service
        startService(serviceIntent)
    }

    private fun stopMusic() {
        // Stop Service
        stopService(serviceIntent)
    }
}