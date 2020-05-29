package com.e.yorizori.MyPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.e.yorizori.Activity.HomeActivity
import com.e.yorizori.Fragment.MyPage
import com.e.yorizori.Interface.BackBtnPressListener
import com.e.yorizori.R
import kotlinx.android.synthetic.main.activity_wrote.*
import kotlinx.android.synthetic.main.activity_wrote.view.*
import kotlinx.android.synthetic.main.activity_wrote.view.emptyWrote

class Wrote(parent : Fragment) : BackBtnPressListener,Fragment() {
    var ele_num : Int = 0
    val parent = parent
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_wrote, container, false)
        val listView  = view.findViewById<ListView>(R.id.wrt_checklist)
        view.wrt_backBtn.setOnClickListener {
           onBack()
        }
        (parent as MyPage).saveInfo(1,this)
        (activity as HomeActivity).setOnBackBtnListener(this)

        if (ele_num != 0) {
            emptyWrote.setVisibility(View.VISIBLE)
        }
        else {
            emptyWrote.setVisibility(View.INVISIBLE)
        }

        return view
    }

    override fun onBack() {
        (parent as MyPage).saveInfo(1,null)
        var ft = (activity as HomeActivity).supportFragmentManager
        ft.beginTransaction().remove(this).commit()
        ft.popBackStack()
    }

    override fun onResume() {
        super.onResume()
        if (ele_num == 0) {
            emptyWrote.setVisibility(View.VISIBLE)
        }
        else {
            emptyWrote.setVisibility(View.INVISIBLE)
        }
    }

}
