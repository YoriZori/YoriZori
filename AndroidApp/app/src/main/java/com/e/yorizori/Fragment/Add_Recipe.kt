package com.e.yorizori.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.e.yorizori.Activity.HomeActivity
import com.e.yorizori.Class.Recipe
import com.e.yorizori.Interface.BackBtnPressListener
import com.e.yorizori.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_writing_recipe.*
import kotlinx.android.synthetic.main.activity_writing_recipe.view.*
import java.time.ZoneId
import com.google.firebase.auth.FirebaseAuth



class Add_Recipe(fragment: Fragment, option :Int = 0) :BackBtnPressListener, Fragment() {


    private lateinit var database: DatabaseReference
    lateinit var firebaseAuth: FirebaseAuth
    private var fragment = fragment
    private var fromwhere : Int = option

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_writing_recipe,container,false)

        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser
        val userUID = user!!.uid

        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        val backBtn = view.findViewById(R.id.backBtn) as ImageButton
        val doneBtn = view.findViewById(R.id.doneBtn) as ImageButton
        if(fromwhere == 0){
            (fragment as Community).saveInfo(1,this)
        }
        else if(fromwhere == 1){
            (fragment as Community_SortedList).saveInfo(0,this)
        }
        (activity as HomeActivity).setOnBackBtnListener(this)

        //back 버튼을 누르면 community뷰로 돌아감
        view.backBtn.setOnClickListener {
            if(fromwhere == 0)
                (fragment as Community).saveInfo(1,null)
            else
                (fragment as Community_SortedList).saveInfo(0,null)
            val fragmentManager: FragmentManager = activity!!.supportFragmentManager
            fragmentManager.beginTransaction().remove(this).commit()
            fragmentManager.popBackStack()
        }

        //done 버튼을 누르면 데이터를 저장하고 community뷰로 돌아감
        view.doneBtn.setOnClickListener {
            val recipe = Recipe()
            recipe.cook_title = view.titleInput.text.toString()
            recipe.tag = arrayListOf(view.tagInput.text.toString())
            recipe.ings = arrayListOf(Pair(view.ingInput.text.toString(), view.ingNumInput.text.toString()))
            recipe.recipe = arrayListOf(view.recipeInput.text.toString())
            recipe.writer_UID = userUID

            //Gson().fromJson<Recipe>("", Recipe::class.java)

            database = FirebaseDatabase.getInstance().reference
            database.child("recipe").push().setValue(Gson().toJson(recipe))
                .addOnSuccessListener {
                    val toast = Toast.makeText(activity, "저장되었습니다 :)", Toast.LENGTH_SHORT)
                    toast.show()

                    onBack()
                }
                .addOnFailureListener {
                    val toast = Toast.makeText(activity, "저장에 실패 :(", Toast.LENGTH_SHORT)
                    toast.show()
                }
        }


        view.backBtn.setOnClickListener {
            onBack()
        }

        // Image Click
        view.recipeImage.setOnClickListener {
            (activity as HomeActivity).perCheck()
        }

        return view
    }
    override fun onBack() {
        if(fromwhere == 0)
            (fragment as Community).saveInfo(1,null)
        else
            (fragment as Community_SortedList).saveInfo(0,null)

        var ft = activity!!.supportFragmentManager
        ft.beginTransaction().remove(this).commit()
        ft.popBackStack()
    }
}
