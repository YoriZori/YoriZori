package com.e.yorizori.Adapter

import com.e.yorizori.R
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.view.LayoutInflater as LayoutInflater1


class WRRecipeListViewAdapter(context: Context?, data: Array<String>) : BaseAdapter() {
    var mContext = context
    var sample = data
    override fun getCount(): Int {
        return sample.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): String {
        return sample[position]
    }


    override fun getView(position: Int, converView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater1.from(mContext).inflate(R.layout.recipelist_item, parent, false)

        val recipeNumText = view.findViewById(R.id.recipeNumText) as TextView
        val recipeText = view.findViewById(R.id.recipeText) as TextView
        recipeNumText.setText((position + 1).toString())
        recipeText.setText(sample[position])
        return view
    }
}