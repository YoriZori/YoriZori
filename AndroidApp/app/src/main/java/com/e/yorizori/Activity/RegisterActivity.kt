package com.e.yorizori.Activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.e.yorizori.Class.User
import com.e.yorizori.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = FirebaseDatabase.getInstance().reference
        firebaseAuth = FirebaseAuth.getInstance()
        setContentView(R.layout.activity_register)

        register_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                val email = register_email.text.toString()
                val pw = register_pw.text.toString()
                val name = register_name.text.toString()

                if (isValidEmail(email)) {
                    createUser(email, name, pw)
                }
                finish()
            }
        })
    }

    /* 일단 지금은 비어있는지 아닌지만 테스트 함. */
    private fun isValidEmail(email : String): Boolean {
        if(TextUtils.isEmpty(email))
            return false
        return true
    }

    private fun createUser(email: String, name:String, pw: String) {
        database = FirebaseDatabase.getInstance().getReference("Auth")
        firebaseAuth!!.createUserWithEmailAndPassword(email, pw).addOnCompleteListener(this
        ) { task ->
            if (task.isSuccessful) { // 회원가입 성공
                val new = User(name,email, pw).toMap()
                database.child(name).setValue(new)
                Toast.makeText(this, R.string.register_succeed, Toast.LENGTH_SHORT).show()
            } else { // 회원가입 실패
                Toast.makeText(this, R.string.register_failed, Toast.LENGTH_SHORT).show()
            }
        }
    }
}