package com.e.yorizori.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.e.yorizori.Class.Community_ListViewItem
import com.e.yorizori.Class.Recipe
import com.e.yorizori.Activity.HomeActivity
import com.e.yorizori.R
import com.e.yorizori.explain
import com.e.yorizori.explainFrag


class Community_HorizontalAdapter(
    context: Context,
    activity: HomeActivity,
    fragment : Fragment
) :
    RecyclerView.Adapter<Community_HorizontalAdapter.ViewHolder>() {
    private var listViewItemList=ArrayList<Community_ListViewItem>()
    private val context: Context
    private val activity = activity
    private val fragment = fragment
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder { // context 와 parent.getContext() 는 같다.
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.community_list_item_2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = listViewItemList[position]
        if(item.titleStr == null){
            val drawable_tmp = ContextCompat.getDrawable(context,R.drawable.event_image)
            holder.rnameview.visibility = View.GONE
            holder.rtagview.visibility = View.GONE
            holder.rpicview.setImageDrawable(drawable_tmp)
        }
        else {
            holder.rnameview.text = item.titleStr
            holder.rtagview.text = item.tagStr
            holder.rpicview.setImageDrawable(item.iconDrawable)
        }
        holder.itemView.setOnClickListener{

            activity.changeFragment(explainFrag(fragment,0))

        }
    }

    override fun getItemCount(): Int {
        return listViewItemList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rnameview: TextView
        var rtagview:TextView
        var rpicview: ImageView
        init {
            rnameview = itemView.findViewById(R.id.list_title)
            rtagview = itemView.findViewById(R.id.list_tag1)
            rpicview=itemView.findViewById(R.id.list_imageView1)
            itemView.setOnClickListener() {

               activity.changeFragment(explainFrag(fragment, 0))
            }

        }
    }

    init {
        this.context = context
        this.listViewItemList = listViewItemList
    }
    fun addItem(recipe: Recipe?) {
        val item = Community_ListViewItem()
        item.iconDrawable = ContextCompat.getDrawable(
            context,
            R.drawable.turkey_looking_left
        )
        if (recipe == null){
            item.titleStr = null
            item.tagStr=null
        }
        else {
            item.titleStr = recipe!!.cook_title
            item.tagStr = mktag(recipe!!)
        }
        listViewItemList.add(item)
    }
    fun mktag(recipe: Recipe): String{
        var ret=""
        val like = arrayOf("맛있어요","간단해요","저렴해요")
        var tags = recipe.tag
        for (i in 0 until 3){
            val tmp = like[i]
            if(recipe.like_num[i] > 50)
                ret +=("#$tmp ")
        }
        for (i in tags){
            ret += ("#$i ")
        }
        return ret
    }
}