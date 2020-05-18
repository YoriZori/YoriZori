package com.e.yorizori

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Window
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.zip.Inflater

class TMPActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // for making this activity as a dialog
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_my)

        // set the textView
        val textView = findViewById<TextView>(R.id.text_my)
        val intent = getIntent()
        val show_me = intent.getStringExtra("ing_name")
        textView.text = show_me
    }

}