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
import kotlinx.android.synthetic.main.activity_allergy.*
import kotlinx.android.synthetic.main.activity_allergy.view.*

class Allergy(parent:  Fragment) : BackBtnPressListener, Fragment() {
    val ele_num : Int = 0
    val parent = parent
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_allergy, container, false)
        val listView  = view.findViewById<ListView>(R.id.alg_checklist)
        view.alg_backBtn.setOnClickListener {
            onBack()
        }

        (parent as MyPage).saveInfo(2,this)
        (activity as HomeActivity).setOnBackBtnListener(this)

        if (ele_num != 0) {
            emptyAlg.setVisibility(View.VISIBLE)
        }
        else {
            emptyAlg.setVisibility(View.INVISIBLE)
        }

        return view
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
            emptyAlg.setVisibility(View.VISIBLE)
        }
        else {
            emptyAlg.setVisibility(View.INVISIBLE)
        }
    }
}
