package com.e.yorizori.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.e.yorizori.Fragment.Community
import com.e.yorizori.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class CommunityActivity : AppCompatActivity() {
    lateinit var fm : FragmentManager
    lateinit var ft : FragmentTransaction
    var fragment = Community()

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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //Toast.makeText(this,"onSave~~~",Toast.LENGTH_SHORT).show()
    }
    override fun onStop(){
        super.onStop()
        //Toast.makeText(this,"onStop",Toast.LENGTH_SHORT).show()
    }
    override fun onRestart(){
        super.onRestart()
        //Toast.makeText(this,"onRestart",Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        //Toast.makeText(this,"onPause",Toast.LENGTH_SHORT).show()
    }
    override fun onResume(){
        super.onResume()
        val bottomNavigation =findViewById<BottomNavigationView>(R.id.tab)
        val upperView = findViewById<FrameLayout>(R.id.tab_frame)
        changeFragment(fragment)

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
        }// Toast.makeText(this,"onResume",Toast.LENGTH_SHORT).show()
    }
    override fun onResumeFragments() {
        super.onResumeFragments()
     //   Toast.makeText(this,"onResumeFragment",Toast.LENGTH_SHORT).show()
    }
    fun saveFragment(f: Community){
        fragment = f
    }

}