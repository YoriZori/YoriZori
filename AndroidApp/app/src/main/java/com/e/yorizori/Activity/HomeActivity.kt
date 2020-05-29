package com.e.yorizori.Activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.e.yorizori.Class.RefrigItem
import com.e.yorizori.Fragment.Add_Recipe
import com.e.yorizori.Fragment.CheckList
import com.e.yorizori.Fragment.Community
import com.e.yorizori.Fragment.MyPage
import com.e.yorizori.Interface.BackBtnPressListener
import com.e.yorizori.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_writing_recipe.*

class HomeActivity : AppCompatActivity(){
    lateinit var ft : FragmentTransaction
    var backBtnListener : BackBtnPressListener? = null
    var fragments : Array<Fragment?> = arrayOf(Community(), CheckList(), MyPage())
    var position = 0

    companion object {
        var items = mutableListOf<RefrigItem>()
        var picsuc = 0
        var str : String = ""

        fun add_item(name: String, date: String) {
            items.add(RefrigItem(name, date))
        }

        fun add_item(name: String) {
            items.add(RefrigItem(name))
        }

        // Image Pick Code
        val IMAGE_PICK_CODE = 1000
        // Permission Code
        val PERMISSION_CODE = 1001

    }

    fun setOnBackBtnListener(listener:BackBtnPressListener?){
        backBtnListener = listener
    }

    fun changeFragment(f: Fragment,name: String?=null, cleanStack:Boolean=false){
        ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.tab_frame,f)
        ft.addToBackStack(name)
        ft.commit()
        true
    }


    fun perCheck() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                //Permission Denied
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permissions, PERMISSION_CODE)
            }
            else {
                pickImageFromGallery()
            }
        }
        else {
            pickImageFromGallery()
        }

    }

    fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            PERMISSION_CODE -> {
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery()
                }
                else {
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            val uri = data!!.data
            str = uri.toString()
            picsuc = 1
        }
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
        setContentView(R.layout.activity_home)
        val bottomNavigation =findViewById<BottomNavigationView>(R.id.tab)
        val upperView = findViewById<FrameLayout>(R.id.tab_frame)
        if(fragments[position] != null) {
                changeFragment(fragments[position]!!)
        }

        bottomNavigation.setOnNavigationItemSelectedListener {
            upperView.removeAllViews()

            var selected:Fragment
            when(it.itemId){
                R.id.tab_community -> {
                    selected = fragments[0]!!
                }
                R.id.tab_check ->{
                    selected = fragments[1]!!
                }
                else ->{
                    selected = fragments[2]!!
                }

            }
            changeFragment(selected)
            true
        }

    }

    override fun onBackPressed() {
        if(backBtnListener != null){
            backBtnListener!!.onBack()
        }
        else {
            Toast.makeText(this,"no back Button Listener!",Toast.LENGTH_SHORT).show()
            super.onBackPressed()
        }
    }

    fun saveFragment(p : Int,f: Fragment){
        fragments[p] = f
        position = p
    }
}