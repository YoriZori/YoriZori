package com.e.yorizori.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.e.yorizori.R

class ingAdapter (val context : Context, val arrlist : ArrayList<Pair<String,String>>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_food,null)

        val howto = view.findViewById<TextView>(R.id.nameText)
        val num=view.findViewById<TextView>(R.id.numText)

        val rec = arrlist[position]
        howto.text = rec.first
        num.text = rec.second

        return view
    }

    override fun getItem(position: Int): Any {
        return arrlist[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return arrlist.size
    }

}