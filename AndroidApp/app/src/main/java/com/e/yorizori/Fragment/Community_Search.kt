package com.e.yorizori.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.e.yorizori.Class.Recipe
import com.e.yorizori.R
import com.google.firebase.database.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_community_search.view.*

class Community_Search(context: Context) : Fragment(){
    private var recipes : MutableList<String> = ArrayList()
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootRef = FirebaseDatabase.getInstance().reference
        database = rootRef.child("recipe")

        val listener = object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Log.d("DatabaseError",p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                Log.d("onDataChange","in!")
                for (child in p0.children) {
                    val recipe_str= child.getValue(String::class.java)
                    val trimmed = recipe_str!!.trim()
                    val gson = Gson()
                    val recipe = gson.fromJson(trimmed,Recipe::class.java)
                    recipes.add(recipe.cook_title)
                }
            }
        }
        database.addValueEventListener(listener)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=  inflater.inflate(R.layout.activity_community_search,container,false)
        val searchAdapter = ArrayAdapter<String>(requireContext(),
            android.R.layout.simple_list_item_1,
            recipes
            )
        view.auto_search_community.threshold = 0
        view.auto_search_community.setAdapter(searchAdapter)

        if(recipes.isEmpty()){
            Toast.makeText(this.context,"Empty!",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this.context,"${recipes.size} item exists",Toast.LENGTH_SHORT).show()
        }

        view.auto_search_community.setOnItemClickListener { parent, view, position, id ->
            val clicked = parent.getItemAtPosition(position).toString()

        }


        return view
    }
}