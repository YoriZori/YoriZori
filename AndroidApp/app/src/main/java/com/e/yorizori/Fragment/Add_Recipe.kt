package com.e.yorizori.Fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.e.yorizori.Activity.HomeActivity
import com.e.yorizori.Adapter.ChecklistListAdapter
import com.e.yorizori.Adapter.WRIngListViewAdapter
import com.e.yorizori.Adapter.WRRecipeListViewAdapter
import com.e.yorizori.Class.Recipe
import com.e.yorizori.Class.RefrigItem
import com.e.yorizori.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_writing_recipe.*
import kotlinx.android.synthetic.main.activity_writing_recipe.view.*
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class Add_Recipe : Fragment() {

    private lateinit var database: DatabaseReference
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_writing_recipe,container,false)

        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser
        val userUID = user!!.uid

        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        var ings : Array<Pair<String, String>> = emptyArray()
        var recipes : Array<String> = emptyArray()

        val ingListView  = view.findViewById<ListView>(R.id.ingredientList)

        var ingListViewAdapter =
            WRIngListViewAdapter(
                this.requireContext(),
                ings
            )

        ingListView.setAdapter(ingListViewAdapter)

        val recipeListView  = view.findViewById<ListView>(R.id.recipeList)

        var recipeListViewAdapter =
            WRRecipeListViewAdapter(
                this.requireContext(),
                recipes
            )

        recipeListView.setAdapter(recipeListViewAdapter)


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
//            recipe.ings = arrayOf(Pair(view.ingInput.text.toString(), view.ingNumInput.text.toString()))
//            recipe.recipe = arrayOf(view.recipeInput.text.toString())
            recipe.ings = ings
            recipe.recipe = recipes
            recipe.writer_UID = userUID

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

        //input new ingredients
        view.ingNumInput.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP){
                ings = append(ings, Pair(view.ingInput.text.toString() ,view.ingNumInput.text.toString()))
                view.ingInput.setText("")
                view.ingNumInput.setText("")

                ingListViewAdapter =
                    WRIngListViewAdapter(
                        this.requireContext(),
                        ings
                    )

                ingListView.setAdapter(ingListViewAdapter)

                //hide keyboard
                val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

            }
            true
        }

        //input new recipes
        view.recipeInput.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP){

                recipes = append(recipes, view.recipeInput.text.toString())
                view.recipeInput.setText("")

                recipeListViewAdapter =
                    WRRecipeListViewAdapter(
                        this.requireContext(),
                        recipes
                    )

                recipeListView.setAdapter(recipeListViewAdapter)

                //hide keyboard
                val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

            }
            true
        }

        return view
    }


}


fun append(arr: Array<Pair<String, String>>, element: Pair<String, String>): Array<Pair<String, String>> {
    val list : MutableList<Pair<String,String>> = arr.toMutableList()
    list.add(element)
    return list.toTypedArray()
}

fun append(arr: Array<String>, element: String): Array<String> {
    val list : MutableList<String> = arr.toMutableList()
    list.add(element)
    return list.toTypedArray()
}

