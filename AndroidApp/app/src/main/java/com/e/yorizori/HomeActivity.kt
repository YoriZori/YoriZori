package com.e.yorizori

import java.util.*
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeActivity : AppCompatActivity() {
    lateinit var ft : FragmentTransaction

    companion object {
        var items = mutableListOf<RefrigItem>()
        var hate_items = mutableListOf<RefrigItem>()
        var checklist_prefs : SharedPreferences? = null
        var hate_prefs : SharedPreferences? = null
        var checklist_edit : SharedPreferences.Editor? = null
        var hate_edit : SharedPreferences.Editor? = null

        fun set_prefs(_context : Context) {
            checklist_prefs = _context.getSharedPreferences("checklist_data", Context.MODE_PRIVATE)
            hate_prefs = _context.getSharedPreferences("hate_data", Context.MODE_PRIVATE)
            checklist_edit = _context.getSharedPreferences("checklist_data", Context.MODE_PRIVATE).edit()
            hate_edit = _context.getSharedPreferences("hate_data", Context.MODE_PRIVATE).edit()
        }

        fun add_item(name: String, date: String) {
            val tmp : RefrigItem = RefrigItem(name, date)
            items.add(tmp)
            checklist_edit?.putString(name, date)
            checklist_edit?.commit()
        }

        fun add_item(name: String) {
            val tmp : RefrigItem = RefrigItem(name)
            items.add(tmp)
            checklist_edit?.putString(name, tmp.print_due())
            checklist_edit?.commit()
        }

        fun add_hate(name: String) {
            val tmp : RefrigItem = RefrigItem(name)
            hate_items.add(tmp)
            hate_edit?.putString(name, tmp.print_due())
            hate_edit?.commit()
        }

        fun load_checklist() {
            var tmp = checklist_prefs?.all
            for(name in tmp!!.keys){
                items.add(RefrigItem(name, tmp.get(name) as String))
            }
        }

        fun load_hate() {
            var tmp = hate_prefs?.all
            for(name in tmp!!.keys){
                hate_items.add(RefrigItem(name))
            }
        }

        fun delete_checklist(refrigItem: RefrigItem) {
            checklist_edit?.remove(refrigItem.item)
            checklist_edit?.commit()
        }

        fun delete_hate(refrigItem: RefrigItem) {
            hate_edit?.remove(refrigItem.item)
            hate_edit?.commit()
        }
    }

    fun changeFragment(f: Fragment, cleanStack:Boolean=false){
        ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.tab_frame,f)
        ft.addToBackStack(null)
        ft.commit()

        true
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        set_prefs(applicationContext)
        load_checklist()
        load_hate()

//        items.add(RefrigItem("소세지", "2018-12-25"))
//        items.add(RefrigItem("돼지고기"))
//        items.add(RefrigItem("우유", "2020-05-20"))
//        items.add(RefrigItem("양파", "2020-05-28"))
//        items.add(RefrigItem("카레가루"))
//        items.add(RefrigItem("마늘", "2020-05-29"))
//        items.add(RefrigItem("고추장"))
//        items.add(RefrigItem("떡", "2020-05-30"))
//        items.add(RefrigItem("간장", "2020-05-27"))
//        items.add(RefrigItem("으악", "2020-05-26"))
//        items.add(RefrigItem("으악", "2020-05-25"))
//        items.add(RefrigItem("으악", "2020-05-24"))
//        items.add(RefrigItem("으악", "2020-05-23"))
//        items.add(RefrigItem("으악", "2020-05-22"))
//        items.add(RefrigItem("으악", "2020-05-21"))
//        items.add(RefrigItem("으악", "2020-05-20"))
//        items.add(RefrigItem("으악", "2020-05-19"))
//        items.add(RefrigItem("으악", "2020-05-18"))
//        items.add(RefrigItem("으악", "2020-05-17"))
//        items.add(RefrigItem("나랏말싸미뒹귁에달아문자와로서로사맛디아니할세이런젼차로어린백성이니르고져홀빼이셔도마참내제뜻을", "2019-05-10"))

        val bottomNavigation =findViewById<BottomNavigationView>(R.id.tab)
        val upperView = findViewById<FrameLayout>(R.id.tab_frame)
        changeFragment(Community())
        bottomNavigation.setOnNavigationItemSelectedListener {
            upperView.removeAllViews()

            var selected:Fragment

            when(it.itemId){
                R.id.tab_community ->{
                    selected = Community()
                    true
                }
                R.id.tab_check ->{
                    selected = CheckList()
                    true
                }
                else ->{
                    selected = HateList()
                    true
                }
            }
            if(selected != null){
                changeFragment(selected)
            }

            true
        }
    }
}