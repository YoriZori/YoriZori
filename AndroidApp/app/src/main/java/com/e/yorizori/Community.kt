package com.e.yorizori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class Community: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view = inflater.inflate(R.layout.activity_community, container, false)
        val title : TextView= view.findViewById(R.id.title0)
        var txt = "Event"
        title.text=  txt
        val image0 : ImageView = view.findViewById(R.id.image0)
        val text0 : TextView=view.findViewById(R.id.text0)
        val image1 : ImageView = view.findViewById(R.id.image1)
        val text1 : TextView=view.findViewById(R.id.text1)
        val image2 : ImageView = view.findViewById(R.id.image2)
        val text2 : TextView=view.findViewById(R.id.text2)
        val image3 : ImageView = view.findViewById(R.id.image3)
        val text3 : TextView=view.findViewById(R.id.text3)
        text0.text ="칠면조"
        image0.setImageResource(R.drawable.turkey_looking_right)
        text1.text ="칠면조반대"
        image1.setImageResource(R.drawable.turkey_looking_left)
        text2.text ="마주보는칠면조"
        image2.setImageResource(R.drawable.turkey_looking_right)
        text3.text ="마주보는칠면조"
        image3.setImageResource(R.drawable.turkey_looking_left)
        return view
    }
}