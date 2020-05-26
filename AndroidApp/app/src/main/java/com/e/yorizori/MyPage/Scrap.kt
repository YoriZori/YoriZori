package com.e.yorizori.MyPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.e.yorizori.Activity.HomeActivity
import com.e.yorizori.Fragment.MyPage
import com.e.yorizori.R
import kotlinx.android.synthetic.main.activity_scrap.view.*

class Scrap : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_scrap, container, false)
        val listView  = view.findViewById<ListView>(R.id.scr_checklist)
        view.scr_backBtn.setOnClickListener {
            (activity as HomeActivity).changeFragment(MyPage())
        }

        return view
    }
}
