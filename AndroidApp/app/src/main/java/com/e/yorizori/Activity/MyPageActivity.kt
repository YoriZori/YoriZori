package com.e.yorizori.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.e.yorizori.Fragment.MyPage
import com.e.yorizori.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MyPageActivity : AppCompatActivity(){
    lateinit var ft : FragmentTransaction

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
    }
    override fun onResume(){
        super.onResume()
        val bottomNavigation =findViewById<BottomNavigationView>(R.id.tab)
        val upperView = findViewById<FrameLayout>(R.id.tab_frame)
        changeFragment(MyPage())
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