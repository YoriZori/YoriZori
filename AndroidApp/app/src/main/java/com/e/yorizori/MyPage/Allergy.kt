package com.e.yorizori.MyPage

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.e.yorizori.Activity.HomeActivity
//import com.e.yorizori.Activity.HomeActivity.Companion.hate_items
import com.e.yorizori.Adapter.AllergyListAdapter
import com.e.yorizori.Fragment.MyPage
import com.e.yorizori.Interface.BackBtnPressListener
import com.e.yorizori.R
import kotlinx.android.synthetic.main.activity_allergy.*
import kotlinx.android.synthetic.main.activity_allergy.view.*

class Allergy(parent:  Fragment) : BackBtnPressListener, Fragment() {
    private val ele_num : Int = 0
    val parent = parent
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_hatelist, container, false)
        val listView  = view.findViewById<ListView>(R.id.list_hatelist)
        view.alg_backBtn.setOnClickListener {
            onBack()
        }

        val button = view.findViewById<ImageButton>(R.id.delete_button)
        val listViewAdapter = AllergyListAdapter(
            this.requireContext(),
            HomeActivity.hate_items
        )

        listView.setAdapter(listViewAdapter)

        button.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                showSettingPopup(listView, listViewAdapter, button)
            }
        })

        (parent as MyPage).saveInfo(2,this)
        (activity as HomeActivity).setOnBackBtnListener(this)

        if (ele_num == 0) {
            emptyAlg?.visibility = View.VISIBLE
        }
        else if (ele_num != 0){
            emptyAlg?.visibility = View.INVISIBLE
        }

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
            HomeActivity.add_hate(clicked)
//            Toast.makeText(requireContext(), "Clicked: $clicked", Toast.LENGTH_SHORT).show()
//
//            val intent = Intent(activity, CalendarSet::class.java)
//            intent.putExtra("ing_name", clicked)
//            startActivity(intent)
//            activity?.finish()
        }


        return view
    }

    private fun showSettingPopup(listView: ListView, listAdapterViewAdapter: AllergyListAdapter, button: ImageButton) {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.alert_delete, null)
        val textView: TextView = view.findViewById<TextView>(R.id.alert_textview)
        textView.text = AllergyListAdapter.selected.size.toString() + "개 항목을 삭제하시겠습니까?"

        val alertDialog = AlertDialog.Builder(this.requireContext())
            .setTitle("  ")
            .setPositiveButton("삭제") { dialog, which ->
                for (i in 0 until AllergyListAdapter.selected.size){
                    HomeActivity.delete_hate(AllergyListAdapter.selected[i])
                    HomeActivity.hate_items.remove(AllergyListAdapter.selected[i])
                }
                AllergyListAdapter.selected.clear()
                listView.clearChoices()
                listAdapterViewAdapter.notifyDataSetChanged()
                Toast.makeText(this.requireContext(), "삭제", Toast.LENGTH_SHORT).show()
            }
            .setNeutralButton("취소", null)
            .create()

        alertDialog.setView(view)
        alertDialog.show()
    }

    override fun onBack() {
        (parent as MyPage).saveInfo(2,null)
        var ft = (activity as HomeActivity).supportFragmentManager
        ft.beginTransaction().remove(this).commit()
        ft.popBackStack()
    }

    override fun onResume() {
        super.onResume()
        if (ele_num == 0) {
            emptyAlg?.visibility = View.VISIBLE
        }
        else if (ele_num != 0){
            emptyAlg?.visibility = View.INVISIBLE
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
