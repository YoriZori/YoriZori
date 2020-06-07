package com.e.yorizori.Adapter

import com.e.yorizori.R
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.view.LayoutInflater as LayoutInflater1


class WRIngListViewAdapter(context: Context?, data: Array<Pair<String,String>>) : BaseAdapter() {
    var mContext = context
    var sample = data
    override fun getCount(): Int {
        return sample.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Pair<String,String> {
        return sample[position]
    }


    override fun getView(position: Int, converView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater1.from(mContext).inflate(R.layout.ingredientlist_item, parent, false)

        val ingText = view.findViewById(R.id.ingText) as TextView
        val ingAmountText = view.findViewById(R.id.ingAmountText) as TextView
        ingText.setText(sample[position].first)
        ingAmountText.setText(sample[position].second)
        return view
    }
}