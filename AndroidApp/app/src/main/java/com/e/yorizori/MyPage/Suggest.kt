package com.e.yorizori.MyPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.e.yorizori.R

class Suggest : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggest)

        val go_intent = findViewById(R.id.sug_sendBtn) as ImageButton
        go_intent.setOnClickListener {
            //val intent = Intent(this@Suggest, HomeActivity::class.java)
            //startActivity(intent)
            Toast.makeText(this, "운영진에게 메세지를 보냈습니다. 서비스 이용에 반영하도록 하겠습니다!", Toast.LENGTH_LONG).show();
            finish()
        }

        val go2_intent = findViewById(R.id.sug_backBtn) as ImageButton
        go2_intent.setOnClickListener {
            finish()
        }
    }




}
