package com.e.yorizori

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment

class Community: Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{

        val view = inflater.inflate(R.layout.activity_community, container, false)
        val button = view.findViewById(R.id.add_recipe) as Button
        val listview = view.findViewById(R.id.listview1) as ListView
        button.setOnClickListener{
            // TODO: onclick Listener
            // (activity as HomeActivity).changeFragment( 레시피 추가 fragment로 바꿔주세요! )
        }
        val adapter = Community_ListViewAdapter(this.context!!,activity)
        listview.adapter = adapter

        return view
    }
}