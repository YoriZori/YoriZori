package com.e.yorizori.Fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.e.yorizori.Activity.HomeActivity
import com.e.yorizori.Adapter.ChecklistListAdapter
import com.e.yorizori.Adapter.WRIngListViewAdapter
import com.e.yorizori.Adapter.WRRecipeListViewAdapter
import com.e.yorizori.CalendarSet
import com.e.yorizori.Class.Recipe
import com.e.yorizori.Class.RefrigItem
import com.e.yorizori.Interface.BackBtnPressListener
import com.e.yorizori.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_writing_recipe.*
import kotlinx.android.synthetic.main.activity_writing_recipe.view.*
<<<<<<< HEAD
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import com.e.yorizori.Activity.OpeningActivity.Companion.ing_list
=======
import java.time.ZoneId
import com.google.firebase.auth.FirebaseAuth
>>>>>>> saintreal4

import kotlinx.android.synthetic.main.activity_writing_recipe.view.recipeImage


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
<<<<<<< HEAD
            recipe.tag = arrayOf(view.tagInput.text.toString())
            recipe.ings = ings
            recipe.recipe = recipes
=======
            recipe.tag = arrayListOf(view.tagInput.text.toString())
            recipe.ings = arrayListOf(Pair(view.ingInput.text.toString(), view.ingNumInput.text.toString()))
            recipe.recipe = arrayListOf(view.recipeInput.text.toString())
>>>>>>> saintreal4
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

        /* search bar start */

        // for real-time search
        val searchAutoComplete = view.findViewById<AutoCompleteTextView>(R.id.ingInput)
        val searchAdapter = ArrayAdapter<String>(requireContext(),
            android.R.layout.simple_list_item_1,
            /*android.R.layout.simple_list_item_1,*/
            ing_list)
        searchAutoComplete.threshold = 0
        searchAutoComplete.setAdapter(searchAdapter)

        /* search bar end */


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

}
        var ft = activity!!.supportFragmentManager
        ft.beginTransaction().remove(this).commit()
        ft.popBackStack()
    }

    override fun onResume() {
        super.onResume()
        if (HomeActivity.picsuc == 1) {
            var uri = Uri.parse(HomeActivity.str)
            recipeImage.setImageURI(uri)
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

