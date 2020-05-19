package com.e.yorizori

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import java.text.SimpleDateFormat

class ChecklistListAdapter (val context: Context, val _items: MutableList<RefrigItem>): BaseAdapter() {

    private val mContext: Context = context
    val simpleDate : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    val items : MutableList<RefrigItem> = _items
    val selected : MutableList<RefrigItem> = mutableListOf()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = LayoutInflater.from(context).inflate(R.layout.checklist_item, parent, false)

        val item_name = view.findViewById<TextView>(R.id.item_name)
        val due_date = view.findViewById<TextView>(R.id.due_date)

        RefrigItem.sort(items)

        val item = items[position]
        item_name.text = item.item
        due_date.text = item.print_due()

        var tmp = simpleDate.format(item.due).split("-")[0].toInt()
        if (tmp < 2040)
            due_date.setTextColor(Color.RED)

        val checkBox = view.findViewById<CheckBox>(R.id.item_check)

        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            var check = true
            if (isChecked){
                for (i in 0 until selected.size){
                    if (selected[i] == items[position]){
                        check = false
                        break
                    }
                }
                if (check)
                    selected.add(items[position])
            }
            else{
                for (i in 0 until selected.size){
                    if (selected[i] == items[position]){
                        selected.removeAt(i)
                        break
                    }
                }
            }
        }

        var check = true
        for (i in 0 until selected.size){
            if (selected[i] == items[position]){
                checkBox.isChecked = true
                check = false
                break
            }
        }
        if (check)
            checkBox.isChecked = false

        return view
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

}
