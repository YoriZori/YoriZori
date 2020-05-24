package com.e.yorizori.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.e.yorizori.Class.RefrigItem
import com.e.yorizori.Fragment.CheckList
import com.e.yorizori.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class ChecklistActivity : AppCompatActivity(){
    lateinit var ft : FragmentTransaction

    companion object {
        var items = mutableListOf<RefrigItem>()

        fun add_item(name: String, date: String) {
            items.add(RefrigItem(name, date))
        }

        fun add_item(name: String) {
            items.add(RefrigItem(name))
        }
    }

    fun changeFragment(f: Fragment,name: String?=null, cleanStack:Boolean=false){
        ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.tab_frame,f)
        ft.addToBackStack(name)
        ft.commit()

        true
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        items.add(RefrigItem("소세지", "2018-12-25"))
        items.add(RefrigItem("돼지고기"))
        items.add(RefrigItem("우유", "2020-05-20"))
        items.add(RefrigItem("양파", "2020-05-28"))
        items.add(RefrigItem("카레가루"))
        items.add(RefrigItem("마늘", "2020-05-29"))
        items.add(RefrigItem("고추장"))
        items.add(RefrigItem("떡", "2020-05-30"))
        items.add(RefrigItem("간장", "2020-05-27"))
        items.add(RefrigItem("으악", "2020-05-26"))
        items.add(RefrigItem("으악", "2020-05-25"))
        items.add(RefrigItem("으악", "2020-05-24"))
        items.add(RefrigItem("으악", "2020-05-23"))
        items.add(RefrigItem("으악", "2020-05-22"))
        items.add(RefrigItem("으악", "2020-05-21"))
        items.add(RefrigItem("으악", "2020-05-20"))
        items.add(RefrigItem("으악", "2020-05-19"))
        items.add(RefrigItem("으악", "2020-05-18"))
        items.add(RefrigItem("으악", "2020-05-17"))
        items.add(
            RefrigItem(
                "나랏말싸미뒹귁에달아문자와로서로사맛디아니할세이런젼차로어린백성이니르고져홀빼이셔도마참내제뜻을",
                "2019-05-10"
            )
        )

    }
    override fun onResume(){
        super.onResume()
        val bottomNavigation =findViewById<BottomNavigationView>(R.id.tab)
        val upperView = findViewById<FrameLayout>(R.id.tab_frame)
        changeFragment(CheckList())
        bottomNavigation.setOnNavigationItemSelectedListener {
            upperView.removeAllViews()

            //  var selected:Fragment
            var selected : Intent
            when(it.itemId){
                R.id.tab_community ->{
                    selected = Intent(this, CommunityActivity::class.java)
                    //selected = Community()
                    true
                }
                R.id.tab_check ->{
                    selected = Intent(this, ChecklistActivity::class.java)
                    // selected = CheckList()
                    true
                }
                else ->{
                    selected = Intent(this, MyPageActivity::class.java)
                    //selected = MyPage()
                    true
                }
            }
            if(selected != null){
                selected.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(selected)
                //changeFragment(selected)
            }

            true
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
    }

}