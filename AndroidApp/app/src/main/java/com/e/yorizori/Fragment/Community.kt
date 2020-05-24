package com.e.yorizori.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.e.yorizori.Activity.CommunityActivity
import com.e.yorizori.Adapter.Community_ListViewAdapter
import com.e.yorizori.R

class Community: Fragment(){
    var savedFragment: Array<Fragment?> = arrayOf(null,null,null)
    var goto = -1
    var position = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view = inflater.inflate(R.layout.activity_community, container, false)
        val button = view.findViewById(R.id.add_recipe) as Button
        val listview = view.findViewById(R.id.listview1) as ListView

        if(goto != -1){
            val prev_frag:Fragment
            when(goto){
                0 -> {
                    prev_frag = savedFragment[0]!!
                    savedFragment[0] = null
                }
                1 -> {
                    //prev_frag = Community_Explain()
                    prev_frag = savedFragment[1]!!
                }
                else -> {
                    prev_frag = savedFragment[2]!!
                }
            }
            (this.activity as CommunityActivity).changeFragment(prev_frag)

        }
        button.setOnClickListener{
            (activity as CommunityActivity).changeFragment(Add_Recipe())
        }
        val adapter =
            Community_ListViewAdapter(
                this.context!!,
                activity,
                this
            )
        listview.adapter = adapter
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onPause(){
        super.onPause()

    }

    override fun onDestroy(){
        super.onDestroy()

    }

    override fun onResume(){
        super.onResume()
        (activity as CommunityActivity).saveFragment(this)
        Toast.makeText(context,"OnResume of Fragment",Toast.LENGTH_SHORT).show()
    }
    fun saveInfo(idx: Int, fragment : Fragment){
        savedFragment[idx] = fragment
        goto = idx
    }
}