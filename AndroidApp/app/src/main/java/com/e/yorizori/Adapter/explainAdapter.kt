package com.e.yorizori.Adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Insets.add
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.view.OneShotPreDrawListener.add
import com.e.yorizori.Activity.HomeActivity.Companion.items
import com.e.yorizori.Class.Recipe
import com.e.yorizori.Class.RefrigItem
import com.e.yorizori.Class.recipe_ListViewItem
import com.e.yorizori.R
import java.nio.file.Files.size
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class explainAdapter : BaseAdapter() {

    private var recipeList = ArrayList<Recipe>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = convertView
        val context = parent?.context

        if (view == null) {
            val inflater =
                context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.custom_explain, parent, false)
        }

        val howto = view?.findViewById<TextView>(R.id.howto)
        val recipe_ListviewItem = recipeList[position]


        return view!!
    }

    override fun getItem(position: Int): Any {
        return recipeList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return recipeList.size
    }
}