package com.e.yorizori.Fragment

import android.R.attr.fragment
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.e.yorizori.Activity.HomeActivity
import com.e.yorizori.Interface.BackBtnPressListener
import com.e.yorizori.MyPage.*
import com.e.yorizori.R
import kotlinx.android.synthetic.main.activity_my.view.*


class MyPage: BackBtnPressListener, Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view = inflater.inflate(R.layout.activity_my, container, false)

        view.text_scrap.setOnClickListener {
            (activity as HomeActivity).changeFragment(Scrap())
        }

        view.text_wrote.setOnClickListener {
            (activity as HomeActivity).changeFragment(Wrote())
        }

        view.text_allergy.setOnClickListener {
            (activity as HomeActivity).changeFragment(Allergy())
        }

        view.text_account_set.setOnClickListener {
            (activity as HomeActivity).changeFragment(AccountSetting())
        }

        view.text_suggest.setOnClickListener {
            val intent=Intent(activity, Suggest::class.java)
            startActivity(intent)
        }

        view.text_donate.setOnClickListener {
            val intent=Intent(activity, Donate::class.java)
            startActivity(intent)
        }

        return view
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