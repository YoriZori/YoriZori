package com.e.yorizori.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import android.widget.*
import com.e.yorizori.CalendarSet
import com.e.yorizori.Adapter.ChecklistListAdapter
import com.e.yorizori.Activity.HomeActivity.Companion.items
import com.e.yorizori.R
import kotlin.collections.ArrayList

class CheckList: Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.activity_checklist, container, false)

        val listView  = view.findViewById<ListView>(R.id.list_checklist)

        val listViewAdapter =
            ChecklistListAdapter(
                this.requireContext(),
                items
            )

        listView.setAdapter(listViewAdapter)


        /* search bar in the checklist: start! */

        // set the list
        var ing_list : ArrayList<String> = arrayListOf()
        setList(ing_list)

        // for real-time search
        val searchAutoComplete = view.findViewById<AutoCompleteTextView>(R.id.auto_search_checklist)
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
            Toast.makeText(requireContext(), "Clicked: $clicked", Toast.LENGTH_SHORT).show()

            val intent = Intent(activity, CalendarSet::class.java)
            intent.putExtra("ing_name", clicked)
            startActivity(intent)
            //activity?.finish()
        }

        /* search bar: done. */

        return view
    }

    private fun setList(list : ArrayList<String>) {
        list.add("ab")
        list.add("aab")
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
