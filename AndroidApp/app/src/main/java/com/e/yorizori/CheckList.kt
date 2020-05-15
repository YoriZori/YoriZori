package com.e.yorizori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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

        val simpleDate : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val tmp_item = RefrigItem("hotdog", simpleDate.parse("2020-05-16"))

        textView.setText(tmp_item.print_due())
        return view
    }
}