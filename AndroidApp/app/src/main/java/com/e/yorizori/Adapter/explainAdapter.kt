package com.e.yorizori.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.e.yorizori.Activity.HomeActivity.Companion.items
import com.e.yorizori.Class.Recipe
import com.e.yorizori.Class.RefrigItem
import com.e.yorizori.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
/*
class explainAdapter (val context: Context, val howto: ArrayList<Recipe>): BaseAdapter() {

    private val mContext: Context = context
    val simpleDate : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    val selected : MutableList<RefrigItem> = mutableListOf()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = LayoutInflater.from(context).inflate(R.layout.community_recipe, parent, false)

        val howto = view.findViewById<TextView>(R.id.howtorecipe)
        //val string = ArrayList[position]

        return view
    }

    override fun getItem(position: Int): Any {
        //return ArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

}
*/