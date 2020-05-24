package com.e.yorizori.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.e.yorizori.Activity.HomeActivity
import com.e.yorizori.Class.Recipe
import com.e.yorizori.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_writing_recipe.*
import kotlinx.android.synthetic.main.activity_writing_recipe.view.*
import java.time.ZoneId

class Add_Recipe : Fragment() {

    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_writing_recipe,container,false)
        val backBtn = view.findViewById(R.id.backBtn) as ImageButton
        val doneBtn = view.findViewById(R.id.doneBtn) as ImageButton



        //back 버튼을 누르면 community뷰로 돌아감
        view.backBtn.setOnClickListener {
            val fragmentManager: FragmentManager = activity!!.supportFragmentManager
            fragmentManager.beginTransaction().remove(this@Add_Recipe).commit()
            fragmentManager.popBackStack()
        }

        //done 버튼을 누르면 데이터를 저장하고 community뷰로 돌아감
        view.doneBtn.setOnClickListener {
            val recipe = Recipe()
            recipe.cook_title = view.titleInput.text.toString()
            recipe.tag = arrayOf(view.tagInput.text.toString())
            recipe.ings = arrayOf(Pair(view.ingInput.text.toString(), view.ingNumInput.text.toString()))
            recipe.recipe = arrayOf(view.recipeInput.text.toString())

            //Gson().fromJson<Recipe>("", Recipe::class.java)

            database = FirebaseDatabase.getInstance().reference
            database.child("recipe").push().setValue(Gson().toJson(recipe))
                .addOnSuccessListener {
                    val toast = Toast.makeText(activity, "저장되었습니다 :)", Toast.LENGTH_SHORT)
                    toast.show()

                    val fragmentManager: FragmentManager = activity!!.supportFragmentManager
                    fragmentManager.beginTransaction().remove(this@Add_Recipe).commit()
                    fragmentManager.popBackStack()
                }
                .addOnFailureListener {
                    val toast = Toast.makeText(activity, "저장에 실패 :(", Toast.LENGTH_SHORT)
                    toast.show()
                }
        }

        return view
    }
}
