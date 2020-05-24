package com.e.yorizori.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.e.yorizori.R

class MyPage: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view = inflater.inflate(R.layout.activity_my, container, false)

        return view
    }
    override fun onResume(){
        super.onResume()
    }
}