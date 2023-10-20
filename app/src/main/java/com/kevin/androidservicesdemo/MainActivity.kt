package com.kevin.androidservicesdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kevin.androidservicesdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // ViewBinding
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    // Service Intent
    private val serviceIntent by lazy { Intent(this, SimpleService::class.java) }

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