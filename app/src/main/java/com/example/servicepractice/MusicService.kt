package com.example.servicepractice

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MusicService : Service(), MediaPlayer.OnPreparedListener {

    private lateinit var mediaPlayer : MediaPlayer

    // Service - View 상호작용 시 사용, 그렇지 않으면 return null
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    // 가장 먼저 호출되는 생명주기 콜백으로, 최초 한 번만 호출됨
    override fun onCreate() {
        super.onCreate()

        mediaPlayer = MediaPlayer.create(this, R.raw.service_example) // -> call prepare() 즉, 동기적으로 돌아가

        // [비동기 프로그래밍]
        // mediaPlayer = MediaPlayer()
    }

    // 서비스가 호출될 때마다 호출되는 생명주기 콜백
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // [비동기 프로그래밍]
//        mediaPlayer.apply {
//            setDataSource(uri) // 가지고있는게 raw파일 뿐인데 raw파일은 setDataSource로 안됨
//            setOnPreparedListener(this@MusicService)
//            prepareAsync() // service는 main thread애서 돈다. 따라서, 비동기 처리를 해 메인스레드가 block되는 것을 막는
//        }

        mediaPlayer.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()

        // 서비스 종료 시 호출
        mediaPlayer.stop()
        mediaPlayer.release()
    }

    override fun onPrepared(p0: MediaPlayer?) {
        // [비동기 프로그래밍]
        mediaPlayer.start() // 비동기작업이 준비되었을 때 호출
    }
}