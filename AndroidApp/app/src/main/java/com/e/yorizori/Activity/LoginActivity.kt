package com.e.yorizori.Activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.e.yorizori.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_my.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun signIn(view : View) {
        val email = login_email.text
        val pw = login_pw.text
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            text_my.text = user.email
            /*
            status.text = getString(R.string.emailpassword_status_fmt,
                user.email, user.isEmailVerified)
            detail.text = getString(R.string.firebase_status_fmt, user.uid)

            emailPasswordButtons.visibility = View.GONE
            emailPasswordFields.visibility = View.GONE
            signedInButtons.visibility = View.VISIBLE

            verifyEmailButton.isEnabled = !user.isEmailVerified
             */
        } else {
            text_my.text = "not logged in."
            /*
            status.setText(R.string.signed_out)
            detail.text = null

            emailPasswordButtons.visibility = View.VISIBLE
            emailPasswordFields.visibility = View.VISIBLE
            signedInButtons.visibility = View.GONE
             */
        }
    }

    private fun signIn(email: String, password: String) {
        if (!validateForm()) {
            return
        }

        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this, R.string.login_succeed, Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, R.string.login_failed, Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
/*
                // [START_EXCLUDE]
                if (!task.isSuccessful) {
                    status.setText(R.string.auth_failed)
                }
                hideProgressDialog()
                // [END_EXCLUDE]

 */
            }
        // [END sign_in_with_email]
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = login_email.text.toString()
        if (TextUtils.isEmpty(email)) {
            login_email.error = "Required."
            valid = false
        } else {
            login_email.error = null
        }

        val password = login_pw.text.toString()
        if (TextUtils.isEmpty(password)) {
            login_pw.error = "Required."
            valid = false
        } else {
            login_pw.error = null
        }
        return valid
    }
/*
    private fun userLogin(id : String, pw : String) : Boolean {

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        database = FirebaseDatabase.getInstance().reference

        login_button.setOnClickListener(object : View.OnClickListener
        {
            override fun onClick(v: View?) {
                val id = register_id.text.toString()
                val pw = register_pw.text.toString()

                val result = userLogin(id, pw)

                if (result) {
                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                    finish()
                }
            }
        })
        
        login_register.setOnClickListener(object: View.OnClickListener
        {
            override fun onClick(v: View?) {
                startActivity(Intent(applicationContext, RegisterActivity::class.java))
            }
        })
    }

 */
}