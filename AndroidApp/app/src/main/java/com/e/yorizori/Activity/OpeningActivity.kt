package com.e.yorizori.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.e.yorizori.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class OpeningActivity : AppCompatActivity(){

    companion object {
        var ing_list = arrayListOf<String>()

        fun add(str : String) {
            ing_list.add(str)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening)
        val metrics = DisplayMetrics()
        val windowManager: WindowManager =
            applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val mTurkey: ImageView = findViewById(R.id.Turkey)
        val mBar : ImageView = findViewById(R.id.Bar)
        val animSlide = AnimationUtils.loadAnimation(applicationContext,
            R.anim.slide
        )
        val animScale = AnimationUtils.loadAnimation(applicationContext,
            R.anim.scale
        )
        var params = mTurkey.layoutParams

        /* get ingredients from the server. START */
        val rootRef = FirebaseDatabase.getInstance().reference
        val child = rootRef.child("재료")
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(applicationContext, R.string.connection_err, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(shot: DataSnapshot) {
                for (ing in shot.children) {
                    val child = ing.value.toString()
                    ing_list.add(child)
                }
            }
        }
        child.addListenerForSingleValueEvent(listener)
        /* get ingredients from the server. DONE */

        windowManager.defaultDisplay.getMetrics(metrics)
        val length = (maxOf(metrics.widthPixels, metrics.heightPixels) * 0.1).toInt()
        params.width = length
        params.height = length
        mTurkey.layoutParams = params

        animSlide.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(arg0: Animation) {
                mBar.startAnimation(animScale)
            }
            override fun onAnimationRepeat(arg0: Animation) {}
            override fun onAnimationEnd(arg0: Animation) {
                startActivity(Intent(this@OpeningActivity, LoginActivity::class.java))
                finish()
            }
        })
        mTurkey.startAnimation(animSlide)
    }
}