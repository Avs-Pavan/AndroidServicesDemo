package com.kevin.androidservicesdemo

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import com.kevin.androidservicesdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // ViewBinding
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    // Service Intent
    private val serviceIntent by lazy { Intent(this, SimpleService::class.java) }


    // Bound service
    private var isBound = false
    private var boundService: BoundService? = null


    private val boundServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as BoundService.MyBinder
            boundService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            boundService = null
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Bind Service
        val intent = Intent(this, BoundService::class.java)
        bindService(intent, boundServiceConnection, BIND_AUTO_CREATE)


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
            boundService?.start()
            if (isBound) {
                binding.randomTxt.text = "Service is bound"
            } else {
                binding.randomTxt.text = "Service is not bound"
            }
        }


        // Fetch Random Number
        binding.fetchRandomBtn.setOnClickListener {
            binding.randomTxt.text =
                "Random number from service is ${boundService?.getRandomNumber().toString()}"
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