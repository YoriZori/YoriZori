package com.e.yorizori.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.e.yorizori.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button.setOnClickListener(object : View.OnClickListener
        {
            override fun onClick(v: View?) {
                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                finish()
            }
        })
    }
}