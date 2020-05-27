package com.e.yorizori.Fragment

import android.R.attr.fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.e.yorizori.Activity.HomeActivity
import com.e.yorizori.MyPage.*
import com.e.yorizori.R
import kotlinx.android.synthetic.main.activity_my.view.*


class MyPage: Fragment(){
    private var savedFragment : Array<Fragment?> = arrayOf(null,null,null,null,null,null)
    private var goto = -1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view = inflater.inflate(R.layout.activity_my, container, false)
        if(goto != -1){
            val fragment = savedFragment[goto]!!
            (activity as HomeActivity).changeFragment(fragment)
        }
        view.text_scrap.setOnClickListener {
            (activity as HomeActivity).changeFragment(Scrap(this))
        }

        view.text_wrote.setOnClickListener {
            (activity as HomeActivity).changeFragment(Wrote(this))
        }

        view.text_allergy.setOnClickListener {
            (activity as HomeActivity).changeFragment(Allergy(this))
        }

        view.text_account_set.setOnClickListener {
            (activity as HomeActivity).changeFragment(AccountSetting(this))
        }

        view.text_suggest.setOnClickListener {
            (activity as HomeActivity).changeFragment(Suggest(this))
        }

        view.text_donate.setOnClickListener {
            (activity as HomeActivity).changeFragment(Donate(this))
        }

        return view
    }
    override fun onResume(){
        super.onResume()
    }
    fun saveInfo(idx: Int, fragment : Fragment?){
        savedFragment[idx] = fragment
        if(fragment == null)
            goto = -1
        else
            goto = idx
    }
}