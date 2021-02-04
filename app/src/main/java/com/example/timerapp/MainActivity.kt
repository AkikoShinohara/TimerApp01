package com.example.timerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView

abstract class MainActivity : AppCompatActivity() {

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

        fun startTap(view: View) {
            handler.post(runnable)
        }

        fun stopTap(view: View) {
            handler.removeCallbacks(runnable)
        }
    }

}
// 数値を00:00:00形式の文字列に変換する関数
// 引数timeはデフォルト値0を設定、返却する型はnullableなString?型

private fun timeToText(time: Int = 0): String? {
    // if式はたいを返すため、そのままreturnできる
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
