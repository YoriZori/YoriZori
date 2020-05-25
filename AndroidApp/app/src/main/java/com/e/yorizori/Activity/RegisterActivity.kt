package com.e.yorizori.Activity

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
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

                var result = createAccount(email, name, pw)
                if (result) {
                    Toast.makeText(applicationContext, R.string.register_succeed, Toast.LENGTH_SHORT).show()
                    finish()
                }
                else {
                    Toast.makeText(applicationContext, R.string.register_failed, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    /* 이메일: 일단 지금은 비어있는지 아닌지만 테스트 함. */
    private fun validateForm() : Boolean {
        var valid = true

        val email = register_email.text.toString()
        if(TextUtils.isEmpty(email)) {
            register_email.error = "이메일을 정확히 입력해주세요"
            valid = false
        } else {
            register_email.error = null
        }

        val pw = register_pw.text.toString()
        if (pw.length < 6) {
            register_pw.error = "비밀번호는 6자 이상이어야 합니다."
            valid = false
        } else {
            register_pw.error = null
        }

        val name = register_name.text.toString()
        if (TextUtils.isEmpty(name)) {
            register_name.error = "닉네임을 정해주세요."
            valid = false
        } else {
            register_name.error = null
        }

        return valid
    }


    private fun createAccount(email: String, name: String, password: String) : Boolean {
        if (!validateForm())
            return false

        var return_me = true
        // [START create_user_with_email]
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                } else {
                    return_me = false
                }
            }

        return return_me
    }
}