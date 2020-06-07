package com.e.yorizori.Adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.yorizori.Class.FoodDataViewHolder
import com.e.yorizori.Class.FoodModel
import com.e.yorizori.Class.Recipe
import com.e.yorizori.R
import kotlinx.android.synthetic.main.item_food.view.*


class FoodDataAdapter(val list : List<FoodModel>):RecyclerView.Adapter<FoodDataViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food,parent,false)
        return FoodDataViewHolder(view)
    }
    //list : List<FoodModel>

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: FoodDataViewHolder, position: Int) {
        holder.containerView.nameText.text = list[position].name
        holder.containerView.numText.text = list[position].num
        holder.containerView.checkBox
    }
}