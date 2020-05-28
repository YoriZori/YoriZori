package com.e.yorizori.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.e.yorizori.Activity.LoginActivity
import com.e.yorizori.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MyPage: Fragment(){
    lateinit var database : DatabaseReference
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.activity_my, container, false)

        // get the user's id
        database = FirebaseDatabase.getInstance().getReference("Users")
        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser

        // set the text view
        val textView = view.findViewById<TextView>(R.id.text_my)
        textView.text = user!!.displayName

        // for logout
        textView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                firebaseAuth.signOut()
                Toast.makeText(requireContext(), R.string.logout, Toast.LENGTH_SHORT).show()
                val i = Intent(requireContext(), LoginActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(i)
            }
        })

        return view
    }

}