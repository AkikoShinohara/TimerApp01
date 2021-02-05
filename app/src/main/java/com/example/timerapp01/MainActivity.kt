package com.example.timerapp01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val handler = Handler() // 1度だけ移入するものはvalを使う
    var timeValue = 0       // 繰り返し代入するためvarを使う

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // View(xml)要素を変数に代入
        val timeText = findViewById<TextView>(R.id.timeText)
        val startTap = findViewById<TextView>(R.id.tV)
        val stopTap = findViewById<TextView>(R.id.tV2)

        //1秒ごとに処理を実行
        val runnable = object : Runnable {
            override fun run() {
                timeValue++
                // textView を更新
                // ?.letを用いて、nullではない場合のみ更新
                timeToText(timeValue)?.let {
                    // timeToText(timeValue)の値がlet内ではitとして使える
                    timeText.text = it
                }
                handler.postDelayed(this, 1000)
            }
        }

        startTap.setOnClickListener {
            handler.post(runnable)                // start クリック処理されると 23秒から実行される
        }
        // stopボタン押された時の処理
        stopTap.setOnClickListener {
            handler.removeCallbacks(runnable)      // stop クリック処理
        }

    }

    // 数値を00:00:00形式の文字列に変換する関数
    // 引数timeにはデフォルト値0を設定、返却する型はnullableなString?型
    fun timeToText(time: Int = 0): String? {
        // if式は値を返すため、そのままreturnできる
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




