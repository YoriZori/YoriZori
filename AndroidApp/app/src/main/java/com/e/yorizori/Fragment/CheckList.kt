package com.e.yorizori.Fragment

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.e.yorizori.Activity.HomeActivity.Companion.items
import androidx.fragment.app.Fragment
import com.e.yorizori.Activity.HomeActivity
import com.e.yorizori.Adapter.ChecklistListAdapter
import com.e.yorizori.CalendarSet
import com.e.yorizori.Class.RefrigItem
import com.e.yorizori.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_checklist.*
import kotlinx.android.synthetic.main.activity_checklist.view.*


class CheckList: Fragment(){

    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as HomeActivity).saveFragment(1, this)

        val view = inflater.inflate(R.layout.activity_checklist, container, false)

        val listView  = view.findViewById<ListView>(R.id.list_checklist)
        val button = view.findViewById<Button>(R.id.delete_button)
        val listViewAdapter = ChecklistListAdapter(this.requireContext(), button, items)

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

        //input new ingredients
        view.auto_search_checklist.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP){
                val refrigItem = RefrigItem(auto_search_checklist.text.toString())
                database = FirebaseDatabase.getInstance().reference
                database.child("refrigItem").push().setValue(Gson().toJson(refrigItem))
                view.auto_search_checklist.setText("")

                //hide keyboard
                val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

            }
            true
        }

        /* search bar: done. */

        return view
    }

    private fun showSettingPopup(listView: ListView,  listViewAdapter: ChecklistListAdapter, button:Button) {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.alert_delete, null)
        val textView: TextView = view.findViewById<TextView>(R.id.alert_textview)
        textView.text = ChecklistListAdapter.selected.size.toString() + "개 항목을 삭제하시겠습니까?"

        val alertDialog = AlertDialog.Builder(this.requireContext())
            .setTitle("  ")
            .setPositiveButton("삭제") { dialog, which ->
                for (i in 0 until ChecklistListAdapter.selected.size){
                    items.remove(ChecklistListAdapter.selected[i])
                }
                ChecklistListAdapter.selected.clear()
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
    override fun onResume(){
        super.onResume()
    }
}
