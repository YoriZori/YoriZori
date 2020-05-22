package com.e.yorizori.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.e.yorizori.Adapter.Community_ListViewAdapter
import com.e.yorizori.Activity.HomeActivity
import com.e.yorizori.R

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
            (activity as HomeActivity).changeFragment(Add_Recipe())
        }
        val adapter =
            Community_ListViewAdapter(
                this.context!!,
                activity
            )
        listview.adapter = adapter
        return view
    }
}