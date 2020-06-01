package com.e.yorizori.Fragment

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.ListView
import androidx.fragment.app.Fragment
import android.database.DataSetObserver
import android.view.*
import android.widget.*
import com.e.yorizori.Activity.HomeActivity
import com.e.yorizori.Activity.OpeningActivity.Companion.my_ing
import com.e.yorizori.Activity.OpeningActivity.Companion.server_ing
import com.e.yorizori.Adapter.ChecklistListAdapter
import com.e.yorizori.CheckListPicker
import com.e.yorizori.Class.RefrigItem
import com.e.yorizori.Interface.BackBtnPressListener
import com.e.yorizori.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_checklist.*
import kotlinx.android.synthetic.main.activity_checklist.view.*
import kotlinx.android.synthetic.main.checklist_date_picker.view.*


class CheckList: BackBtnPressListener,Fragment(){

    lateinit var database: DatabaseReference
    lateinit var firebaseAuth: FirebaseAuth

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

        // when empty, we will show a cute ref...냉장고
        if (my_ing.size == 0)
            view.img_empty_checklist.visibility = View.VISIBLE
        else
            view.img_empty_checklist.visibility = View.INVISIBLE

        // check list list view
        val listView  = view.findViewById<ListView>(R.id.list_checklist)

        // for added ingredients
        val listViewAdapter = ChecklistListAdapter(this.requireContext(), my_ing)
        listViewAdapter.registerDataSetObserver(object : DataSetObserver() {
            override fun onChanged() {
                Log.d("###", "NOTIFY")
                super.onChanged()
            }
        })
        val button = view.findViewById<Button>(R.id.delete_button)

        listView.setAdapter(listViewAdapter)

        button.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                showSettingPopup(listView, listViewAdapter, button)
            }
        })

        /* search bar in the checklist: start! */

        // for real-time search
        val searchAutoComplete = view.findViewById<AutoCompleteTextView>(R.id.auto_search_checklist)
        val searchAdapter = ArrayAdapter<String>(requireContext(),
                                                android.R.layout.simple_list_item_1,
                                                /*android.R.layout.simple_list_item_1,*/
                                                server_ing)
        searchAutoComplete.threshold = 0
        searchAutoComplete.setAdapter(searchAdapter)

        // for enter and click
        searchAutoComplete.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                val selected = v!!.text.toString()
//                엔터키 누른 애가 서버에 있는 재료라면????
                CheckListPicker(selected).show(fragmentManager!!, "HI")
                return true
            }
        })

        searchAutoComplete.onItemClickListener = AdapterView.OnItemClickListener{
            parent, view, position, id ->
            val clicked = parent.getItemAtPosition(position).toString()

            CheckListPicker(clicked).show(fragmentManager!!, "HI")
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

        return view
    }

    // remove the item from my_ings
    private fun showSettingPopup(listView: ListView,  listViewAdapter: ChecklistListAdapter, button:Button) {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.alert_delete, null)
        val textView: TextView = view.findViewById<TextView>(R.id.alert_textview)
        textView.text = ChecklistListAdapter.selected.size.toString() + "개 항목을 삭제하시겠습니까?"

        val alertDialog = AlertDialog.Builder(this.requireContext())
            .setTitle("  ")
            .setPositiveButton("삭제") { dialog, which ->
                for (i in 0 until ChecklistListAdapter.selected.size){
                    my_ing.remove(ChecklistListAdapter.selected[i])
                }
                ChecklistListAdapter.selected.clear()
                listView.clearChoices()
                listViewAdapter.notifyDataSetChanged()
                Toast.makeText(this.requireContext(), "삭제", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("취소", null)
            .create()

        alertDialog.setView(view)
        alertDialog.show()
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
