package com.e.yorizori

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*


class CheckList: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_checklist, container, false)
        val textView = view.findViewById<TextView>(R.id.text_checklist)
        // Current Calendar
        val c1 = Calendar.getInstance()
        var t1 : Long =  Calendar.getInstance().timeInMillis
        val simpleDate : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        c1.time = Date()
        val btn : Button =  view.findViewById(R.id.btn_input_date)
        val sdt : TextView = view.findViewById(R.id.due_date_txt)

        btn.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(context, CalendarSet::class.java)
                startActivity(intent)
                activity?.finish()
            }
        })

        val tmp_item = RefrigItem("Turkey", simpleDate.parse(simpleDate.format(c1.time))) // 날짜 Parsing으로 text 변경 -> 수정 후에는 search에서 선택한 아이템

        // D-day 기한에 따라서 색 변경
        /*
        if (tmp_item.dDay < 0) {
            sdt.setTextColor(0xFF0000)
        } else if (tmp_item.dDay < 7 ) {
            sdt.setTextColor(0xFFFF99)
        }
        */
        sdt.setText(tmp_item.print_due())
        return view
    }
}