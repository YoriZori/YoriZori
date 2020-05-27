package com.e.yorizori.Fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.e.yorizori.Activity.HomeActivity
import com.e.yorizori.Adapter.Community_ListViewAdapter
import com.e.yorizori.Interface.BackBtnPressListener
import com.e.yorizori.R

class Community: BackBtnPressListener,Fragment(){
    var savedFragment: Array<Fragment?> = arrayOf(null,null,null,null)
    var goto = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view = inflater.inflate(R.layout.activity_community, container, false)
        val Add_button = view.findViewById(R.id.add_recipe) as ImageButton
        val Back_btn = view.findViewById(R.id.commu_backBtn) as ImageButton
        val Search_btn = view.findViewById(R.id.commu_search) as ImageButton
        val listview = view.findViewById(R.id.listview1) as ListView
        val titleview = view.findViewById(R.id.page_title) as TextView

        titleview.text="YoriZori"
        Back_btn.visibility = View.GONE

        if(goto != -1){
            val prev_frag:Fragment
            when(goto){
                0 -> { // sorted_commu
                    prev_frag = savedFragment[0]!!
                }
                1 -> { // add_recipe
                    //prev_frag = Community_Explain()
                    prev_frag = savedFragment[1]!!
                }
                2 -> { // explain
                    prev_frag = savedFragment[2]!!
                }
                else ->{ // event_view
                    prev_frag = savedFragment[3]!!
                }
            }
            (this.activity as HomeActivity).changeFragment(prev_frag)

        }
        Add_button.setOnClickListener{
            (activity as HomeActivity).changeFragment(Add_Recipe(this))
        }
        Search_btn.visibility = View.VISIBLE
        Search_btn.setOnClickListener {
            //Todo: search 옮겨붙이기
        }
        val adapter =
            Community_ListViewAdapter(
                this.context!!,
                activity,
                this
            )
        listview.adapter = adapter
        (activity as HomeActivity).saveFragment(0,this)
        (activity as HomeActivity).setOnBackBtnListener(this)
        return view
    }

    fun saveInfo(idx: Int, fragment : Fragment?){
        savedFragment[idx] = fragment
        if(fragment == null)
            goto = -1
        else
            goto = idx
    }

    override fun onBack() {
        dialog()
    }
    fun dialog(){
        var builder = AlertDialog.Builder(this.context)
        builder.setTitle("YoriZori")
        builder.setMessage("종료하시겠습니까?")
        builder.setPositiveButton("예",DialogInterface.OnClickListener { dialog, which ->
            activity!!.finish()
        })
        builder.setNegativeButton("아니요",DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        builder.show()
        true
    }
}