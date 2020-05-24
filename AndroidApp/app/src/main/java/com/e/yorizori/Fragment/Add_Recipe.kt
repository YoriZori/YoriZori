package com.e.yorizori.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.e.yorizori.R

class Add_Recipe : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_writing_recipe,container,false)
        val backBtn = view.findViewById(R.id.backBtn) as ImageButton
        val doneBtn = view.findViewById(R.id.doneBtn) as ImageButton


        return view
    }
    override fun onResume(){
        super.onResume()
    }
}
