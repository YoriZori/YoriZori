package com.e.yorizori

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment

class CheckList: Fragment(){

    internal var items = mutableListOf<RefrigItem>()

    fun add_item(name: String, date : String){
        items.add(RefrigItem(name, date))
    }

    fun add_item(name: String){
        items.add(RefrigItem(name))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.activity_checklist, container, false)

        items.add(RefrigItem("소세지", "2032-12-25"))
        items.add(RefrigItem("돼지고기"))
        items.add(RefrigItem("우유", "2026-11-10"))
        items.add(RefrigItem("양파", "2025-11-10"))
        items.add(RefrigItem("카레가루"))
        items.add(RefrigItem("마늘", "2025-11-10"))
        items.add(RefrigItem("고추장"))
        items.add(RefrigItem("떡", "2025-11-10"))
        items.add(RefrigItem("간장", "2025-11-10"))
        items.add(RefrigItem("나랏말싸미뒹귁에달아문자와로서로사맛디아니할세이런젼차로어린백성이니르고져홀빼이셔도마참내제뜻을", "2019-05-10"))

        val listView  = view.findViewById<ListView>(R.id.list_checklist)

        val listViewAdapter = ChecklistListAdapter(this.requireContext(), items)

        listView.setAdapter(listViewAdapter)

        return view
    }

}

