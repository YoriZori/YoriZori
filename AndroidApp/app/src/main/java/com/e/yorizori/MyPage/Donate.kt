package com.e.yorizori.MyPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.e.yorizori.R

class Donate : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate)

        val go_intent = findViewById(R.id.donate_btn) as Button
        go_intent.setOnClickListener {
            Toast.makeText(this, "후원 감사드립니다. 더 열심히 하겠습니다!", Toast.LENGTH_LONG).show();
            finish()
        }

        val go2_intent = findViewById(R.id.don_backBtn) as ImageButton
        go2_intent.setOnClickListener {
            finish()
        }

    }
}
