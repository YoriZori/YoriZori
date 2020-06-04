package com.e.yorizori

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import android.widget.*
import com.e.yorizori.HomeActivity.Companion.add_hate
import com.e.yorizori.HomeActivity.Companion.hate_items
import kotlin.collections.ArrayList

class HateList: Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.activity_hatelist, container, false)

        val listView  = view.findViewById<ListView>(R.id.list_hatelist)

        val button = view.findViewById<Button>(R.id.delete_button)
        val listViewAdapter = HateListAdapter(this.requireContext(), hate_items)

        listView.setAdapter(listViewAdapter)

        button.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                showSettingPopup(listView, listViewAdapter, button)
            }
        })

        /* search bar in the checklist: start! */

        // set the list
        var ing_list : ArrayList<String> = arrayListOf()
        setList(ing_list)

        // for real-time search
        val searchAutoComplete = view.findViewById<AutoCompleteTextView>(R.id.auto_search_hatelist)
        val searchAdapter = ArrayAdapter<String>(requireContext(),
                                                android.R.layout.simple_list_item_1,
                                                /*android.R.layout.simple_list_item_1,*/
                                                ing_list)
        searchAutoComplete.threshold = 0
        searchAutoComplete.setAdapter(searchAdapter)

        // for click event
        searchAutoComplete.onItemClickListener = AdapterView.OnItemClickListener{
            parent, view, position, id ->
            val clicked = parent.getItemAtPosition(position).toString()
            add_hate(clicked)
//            Toast.makeText(requireContext(), "Clicked: $clicked", Toast.LENGTH_SHORT).show()
//
//            val intent = Intent(activity, CalendarSet::class.java)
//            intent.putExtra("ing_name", clicked)
//            startActivity(intent)
//            activity?.finish()
        }

        /* search bar: done. */

        return view
    }

    private fun showSettingPopup(listView: ListView, listViewAdapter: HateListAdapter, button:Button) {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.alert_delete, null)
        val textView: TextView = view.findViewById<TextView>(R.id.alert_textview)
        textView.text = HateListAdapter.selected.size.toString() + "개 항목을 삭제하시겠습니까?"

        val alertDialog = AlertDialog.Builder(this.requireContext())
            .setTitle("  ")
            .setPositiveButton("삭제") { dialog, which ->
                for (i in 0 until HateListAdapter.selected.size){
                    HomeActivity.delete_hate(HateListAdapter.selected[i])
                    hate_items.remove(HateListAdapter.selected[i])
                }
                HateListAdapter.selected.clear()
                listView.clearChoices()
                listViewAdapter.notifyDataSetChanged()
                Toast.makeText(this.requireContext(), "삭제", Toast.LENGTH_SHORT).show()
            }
            .setNeutralButton("취소", null)
            .create()

        alertDialog.setView(view)
        alertDialog.show()
//        changeVisibility(button)
   }

    fun changeVisibility(button: Button){
        Log.d("init : ", button.visibility.toString())
        if (ChecklistListAdapter.selected.size == 0){
            button.visibility = View.INVISIBLE
        }
        else if (ChecklistListAdapter.selected.size > 0){
            Log.d("Before : ", button.visibility.toString())
            button.visibility = View.VISIBLE
            Log.d("After : ", button.visibility.toString())
        }
    }


    private fun setList(list : ArrayList<String>) {
        list.add("a")
        list.add("b")
        list.add("c")
        list.add("d")
        list.add("e")
        list.add("f")
        list.add("g")
        list.add("h")
        list.add("i")
        list.add("j")
        list.add("k")
        list.add("l")
        list.add("m")
        list.add("n")
        list.add("o")
        list.add("p")
        list.add("q")
        list.add("r")
        list.add("s")
        list.add("t")
        list.add("u")
        list.add("v")
        list.add("w")
        list.add("x")
        list.add("y")
        list.add("z")
        list.add("양파")
        list.add("양서류(?)")
        list.add("양고기")
        list.add("양상추")
        list.add("양배추")
        list.add("소세지")
        list.add("소고기")
        list.add("소라게(?)")
        list.add("소수림왕")
        list.add("소고기무국")
        list.add("탕수육")
        list.add("팔보채")
        list.add("양장피")
        list.add("맛있다")
    }
}
