package com.example.timerapp01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val handler = Handler()
    var timeValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val timeText = findViewById<TextView>(R.id.timeText)
        val startTap = findViewById<TextView>(R.id.tV)
        val stopTap = findViewById<TextView>(R.id.tV2)


        val runnable = object : Runnable {
            override fun run() {
                timeValue++

                timeToText(timeValue)?.let {
                    timeText.text = it
                }
                handler.postDelayed(this, 1000)
            }
        }

        startTap.setOnClickListener {
            handler.post(runnable)                // 最初のキュー登録
        }
        // stopボタン押された時の処理
        stopTap.setOnClickListener {
            handler.removeCallbacks(runnable)      // キューキャンセル
        }

    }


    fun timeToText(time: Int = 0): String? {
        return if (time < 0) {
            null
        } else if (time == 0) {
            "00:00:00"
        } else {
            val h = time / 3600
            val m = time % 3600 / 60
            val s = time % 60
            "%1$02d:%2$02d:%3$02d".format(h, m, s)
        }
    }

}




