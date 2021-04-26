package com.example.servicepractice

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MusicService : Service() {

    private lateinit var mediaPlayer : MediaPlayer

    override fun onBind(p0: Intent?): IBinder? {
        return null // Service - View 상호작용 시 사용, 그렇지 않으면 return null
    }

    override fun onCreate() {
        super.onCreate()

        // 가장 먼저 호출되는 생명주기 콜백으로, 최초 한 번만 호출됨
        mediaPlayer = MediaPlayer.create(this, R.raw.service_example)
        mediaPlayer.start()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // 서비스가 호출될 때마다 호출되는 생명주기 콜백
        mediaPlayer.start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()

        // 서비스 종료 시 호출
        mediaPlayer.stop()
    }
}