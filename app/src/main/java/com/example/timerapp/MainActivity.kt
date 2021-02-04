package com.example.timerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    // 一度だけ代入するものはvalを使う //
    val handler = Handler()
    // 繰り返し代入するためvarを使う //
    var timeValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // View要素を変数に代入 //
        val timeText = findViewById(R.id.timeText) as TextView
        val start = findViewById(R.id.start) as TextView
        val stop = findViewById(R.id.stop) as TextView

        // 1秒ごとに処理を実行
        val runnable = object : Runnable {
            override fun run() {
                timeValue++
                // TextViewを更新
                // ?.letを用いて、nullではない場合のみ更新
                timeToText()?.let {
                    // timeToText(timeValue)の値がlet内ではitとして使える
                    timeText.text = it as CharSequence?
                }
                handler.postDelayed(this, 1000)
            }

            private fun timeToText() {

            }
        }


    }


}