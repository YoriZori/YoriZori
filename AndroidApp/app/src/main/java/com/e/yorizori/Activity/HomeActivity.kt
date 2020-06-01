package com.e.yorizori.Activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.e.yorizori.Class.RefrigItem
import com.e.yorizori.Fragment.CheckList
import com.e.yorizori.Fragment.Community
import com.e.yorizori.Fragment.MyPage
import com.e.yorizori.Interface.BackBtnPressListener
import com.e.yorizori.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_writing_recipe.*


class HomeActivity : AppCompatActivity(){
    lateinit var ft : FragmentTransaction
    var backBtnListener : BackBtnPressListener? = null
    var fragments : Array<Fragment?> = arrayOf(Community(), CheckList(), MyPage())
    var position = 0

    // Image Pick Code
    val IMAGE_PICK_CODE = 1000
    // Permission Code
    val PERMISSION_CODE = 1001

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
            recipeImage.setImageURI(data?.data)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
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