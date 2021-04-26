package com.example.servicepractice

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun playMusic(v: View) {
        Intent(this, MusicService::class.java).also { intent ->
            startService(intent)
        }
    }

    fun stopMusic(v: View) {
        Intent(this, MusicService::class.java).also { intent ->
            stopService(intent)
        }
    }
}