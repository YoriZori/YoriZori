package com.e.yorizori.Fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import android.view.KeyEvent
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.*
import com.e.yorizori.Activity.HomeActivity.Companion.items
import com.e.yorizori.Activity.HomeActivity
import com.e.yorizori.Adapter.ChecklistListAdapter
import com.e.yorizori.CalendarSet
import com.e.yorizori.Class.RefrigItem
import com.e.yorizori.Interface.BackBtnPressListener
import com.e.yorizori.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_checklist.*
import kotlinx.android.synthetic.main.activity_checklist.view.*
import java.util.*
import kotlin.collections.ArrayList


class CheckList: BackBtnPressListener,Fragment(){

    private lateinit var database: DatabaseReference
    lateinit var firebaseAuth: FirebaseAuth
    val ele_num : Int = 1 // Number of elements in list

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as HomeActivity).saveFragment(1, this)

        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser
        val userUID = user!!.uid

        val view = inflater.inflate(R.layout.activity_checklist, container, false)
        // activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        val listView  = view.findViewById<ListView>(R.id.list_checklist)

        val listViewAdapter = ChecklistListAdapter(this.requireContext(), items)
        val button = view.findViewById<Button>(R.id.delete_button)

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

            showDatePicker(clicked,this)
            //activity?.finish()
        }

        //input new ingredients
        view.auto_search_checklist.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP){
                val refrigItem = RefrigItem(auto_search_checklist.text.toString())
                database = FirebaseDatabase.getInstance().reference
                database.child("refrigItem").child(userUID).push().setValue(Gson().toJson(refrigItem))
                view.auto_search_checklist.setText("")

                //hide keyboard
                val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

            }
            true
        }
        (activity as HomeActivity).setOnBackBtnListener(this)

        /* search bar: done. */

        if (ele_num == 0) {
            emptyChk?.visibility = View.VISIBLE
        }
        else if (ele_num != 0){
            emptyChk?.visibility = View.INVISIBLE
        }

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
        if (ChecklistListAdapter.selected.size == 0){
            button.visibility = View.INVISIBLE
        }
        else if (ChecklistListAdapter.selected.size > 0){
            button.visibility = View.VISIBLE
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
    override fun onResume(){
        super.onResume()
        if (ele_num == 0) {
            emptyChk?.visibility = View.VISIBLE
        }
        else if (ele_num != 0){
            emptyChk?.visibility = View.INVISIBLE
        }
    }
    private fun showDatePicker(name : String, fragment : Fragment) {
        // Calendar
        val c = Calendar.getInstance()
        var year = c.get(Calendar.YEAR)
        var month = c.get(Calendar.MONTH)
        var day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            fragment.context!!,
            DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDayOfMonth ->
                val date = mYear.toString() + "-" + (mMonth+1).toString() + "-" + mDayOfMonth.toString()
                val ref_clicked = RefrigItem(name, date)
                HomeActivity.add_item(name, date)
                (activity as HomeActivity).changeFragment(CheckList())
            }, year, month, day)
        dpd.show()
    }

    override fun onBack() {
        dialog()
    }
    fun dialog(){
        var builder = AlertDialog.Builder(this.context)
        builder.setTitle("YoriZori")
        builder.setMessage("종료하시겠습니까?")
        builder.setPositiveButton("예", DialogInterface.OnClickListener { dialog, which ->
            activity!!.finish()
        })
        builder.setNegativeButton("아니요", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        builder.show()
        true
    }
}
