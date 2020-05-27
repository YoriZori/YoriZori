package com.e.yorizori.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.e.yorizori.R
import com.e.yorizori.Activity.HomeActivity
import kotlinx.android.synthetic.main.activity_writing_recipe.view.*


class Add_Recipe : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_writing_recipe,container,false)

        view.backBtn.setOnClickListener {
            (activity as HomeActivity).changeFragment(Community())
        }

        // Image Click
        view.recipeImage.setOnClickListener {
            (activity as HomeActivity).perCheck()
        }
        return view
    }


}
