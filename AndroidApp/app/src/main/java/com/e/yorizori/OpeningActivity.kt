package com.e.yorizori

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginLeft

class OpeningActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening)
        val mProgressBar: ProgressBar
        val mTurkey: ImageView
        val mCountDownTimer : CountDownTimer
        var i = 0
        mProgressBar = findViewById(R.id.progressBar)
        mTurkey = findViewById(R.id.Turkey)
        mProgressBar.progress=0
        val animSlide = AnimationUtils.loadAnimation(applicationContext,R.anim.slide)
        mTurkey.startAnimation(animSlide)
        mCountDownTimer = object : android.os.CountDownTimer(3000,60){
            override fun onTick(millisUntilFinished: Long){
                i++
                mProgressBar.progress = i * 100 / (3000 / 60)

            }

            override fun onFinish() {
                i++
                mProgressBar.progress = 100
                startActivity(Intent(this@OpeningActivity, MainActivity::class.java))
                finish()
            }
        }
        mCountDownTimer.start()
    }
}