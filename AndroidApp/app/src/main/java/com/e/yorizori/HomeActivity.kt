package com.e.yorizori

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeActivity : AppCompatActivity() {
    lateinit var ft : FragmentTransaction

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
                    selected = MyPage()
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