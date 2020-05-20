package com.e.yorizori

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment

class Community_SortedList(position : Int, activity: HomeActivity): Fragment(){
    val position = position
    val activity = activity
    private var titles = arrayOf(null,"#지금 당장만들어 보자","#이 세상 맛이 아니다","#배고프니 빨리빨리","#이 가격 실화냐!?","#다들 이거 만들던데....","#이런건 어때요?")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_community,container,false)
        val add_recipe_button = view.findViewById(R.id.add_recipe) as Button
        add_recipe_button.setOnClickListener{
            // TODO: click event
            val intent = Intent(context,activity_writingRecipe::class.java)
            startActivity(intent)
            // activity.changeFragment(레시피 추가하는 fragment를 넣어주세요)
        }
        val titleview = view.findViewById(R.id.page_title) as TextView
        val adapter = Community_SortedListViewAdapter(this.context!!, position)
        titleview.text = titles[position]
        val listview = view.findViewById(R.id.listview1) as ListView

        listview.adapter = adapter

        listview.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            val intent = Intent(context,explain::class.java)
            startActivity(intent)
        })

        return view
    }
}