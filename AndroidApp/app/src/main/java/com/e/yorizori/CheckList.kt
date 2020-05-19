package com.e.yorizori

<<<<<<< HEAD
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
=======
import android.content.Intent
>>>>>>> c365a9ab9fe7cbaa4d13ba533972fd5174c92150
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
import android.widget.ListView
import androidx.fragment.app.Fragment
=======
import android.widget.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

>>>>>>> c365a9ab9fe7cbaa4d13ba533972fd5174c92150






class CheckList: Fragment(){

    internal var items = mutableListOf<RefrigItem>()

    companion object {

        fun add_item(name: String, date: String) {
            items.add(RefrigItem(name, date))
        }

        fun add_item(name: String) {
            items.add(RefrigItem(name))
        }
    }
    //호출은 요렇게
    Checklist.add_item(name, due)










    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.activity_checklist, container, false)
<<<<<<< HEAD

       dd(RefrigItem("소세지", "2032-12-25"))
        items.add(RefrigItem("돼지고기"))
        items.add(RefrigItem("우유", "2026-11-10"))
        items.add(RefrigItem("양파", "2025-11-10"))
        items.add(RefrigItem("카레가루"))
        items.add(RefrigItem("마늘", "2025-11-10"))
        items.add(RefrigItem("고추장"))
        items.add(RefrigItem("떡", "2025-11-10"))
        items.add(RefrigItem("간장", "2025-11-10")) items.a
        items.add(RefrigItem("나랏말싸미뒹귁에달아문자와로서로사맛디아니할세이런젼차로어린백성이니르고져홀빼이셔도마참내제뜻을", "2019-05-10"))

        val listView  = view.findViewById<ListView>(R.id.list_checklist)

        val listViewAdapter = ChecklistListAdapter(this.requireContext(), items)

        listView.setAdapter(listViewAdapter)

        return view
    }

}

=======
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

        /* calendar */
        //val textView = view.findViewById<TextView>(R.id.text_checklist)
        // Current Calendar
        /*val c1 = Calendar.getInstance()
        var t1 : Long =  Calendar.getInstance().timeInMillis
        c1.time = Date()*/
        /*val simpleDate : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val btn : Button =  view.findViewById(R.id.btn_input_date)
        val sdt : TextView = view.findViewById(R.id.due_date_txt)

        btn.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(context, CalendarSet::class.java)
                startActivity(intent)

            }
        })
*/
        //val tmp_item = RefrigItem("Turkey", simpleDate.parse(simpleDate.format(c1.time))) // 날짜 Parsing으로 text 변경 -> 수정 후에는 search에서 선택한 아이템

        // D-day 기한에 따라서 색 변경
        /*
        if (tmp_item.dDay < 0) {
            sdt.setTextColor(0xFF0000)
        } else if (tmp_item.dDay < 7 ) {
            sdt.setTextColor(0xFFFF99)
        }
        */
        //sdt.setText(tmp_item.print_due())
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
>>>>>>> c365a9ab9fe7cbaa4d13ba533972fd5174c92150
