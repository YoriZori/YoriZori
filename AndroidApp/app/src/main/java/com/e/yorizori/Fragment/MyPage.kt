package com.e.yorizori.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.e.yorizori.Class.User
import com.e.yorizori.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_my.*

class MyPage: Fragment(){
    lateinit var database : DatabaseReference
    lateinit var nameView : TextView
    lateinit var emailView : TextView
    lateinit var idView : TextView
    lateinit var pwView : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.activity_my, container, false)

        // get the user's id
        val email = login_email.text

        database = FirebaseDatabase.getInstance().getReference("Users")

        // find views.


        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(shot: DataSnapshot) {
                for (snapshot in shot.children) {
                    val user = snapshot.getValue(User::class.java)
                    if (user!!.email == email.toString()) {
                        text_my.text = "I found you"
                    }
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.d("@@@error", "onCancelled")
            }
        })

        return view
    }
}