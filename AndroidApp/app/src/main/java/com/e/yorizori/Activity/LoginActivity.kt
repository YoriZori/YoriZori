package com.e.yorizori.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.e.yorizori.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button.setOnClickListener(object : View.OnClickListener
        {
            override fun onClick(v: View?) {
                database = FirebaseDatabase.getInstance().reference
                database.child("test").child("string").setValue("Hello, World!")
                    .addOnSuccessListener {
                        val toast = Toast.makeText(applicationContext, "suceed!", Toast.LENGTH_SHORT)
                        toast.show()
                    }
                    .addOnFailureListener {
                        val toast = Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT)
                        toast.show()
                    }

                startActivity(Intent(applicationContext, HomeActivity::class.java))
                finish()
            }
        })
    }
}