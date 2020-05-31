package com.e.yorizori.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.e.yorizori.Activity.HomeActivity
import com.e.yorizori.Activity.OpeningActivity
import com.e.yorizori.Class.Community_ListViewItem
import com.e.yorizori.Class.Recipe
import com.e.yorizori.Enum.Like
import com.e.yorizori.R
import com.e.yorizori.explainFrag
import com.squareup.picasso.Picasso

class Community_SortedListViewAdapter(context : Context, activity : FragmentActivity, fragment : Fragment, p : Int): BaseAdapter(){
    private var listViewItemList = ArrayList<Community_ListViewItem>()
    private var context = context
    private var activity = activity
    private var fragment = fragment
    private var parent_position = p
    init{
        when(parent_position){
            0 -> {
                // Error! It's event tab
            }
            1-> {
                // Nothing to do ( Don't need sort )
            }
            2->{
                OpeningActivity.recipe_list.sortWith(compareByDescending { it.like_num[Like.DELICIOUS.idx] })
            }
            3->{
                OpeningActivity.recipe_list.sortWith(compareByDescending{ it.like_num[Like.QUICK.idx] })
            }
            4->{
                OpeningActivity.recipe_list.sortWith(compareByDescending{ it.like_num[Like.CHEAP.idx] })
            }
            5->{
                OpeningActivity.recipe_list.sortWith(compareByDescending { it.scrap_num })
            }
            else->{
                // Nothing to do ( Don't need sort )
            }
        }
        for(i in 0 until OpeningActivity.recipe_list.size) {
            addItem(OpeningActivity.recipe_list[i])
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val context = parent.context

        if (view == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.community_list_item_2,parent,false)
        }
        val titleView = view!!.findViewById(R.id.list_title) as TextView
        val tagView  = view.findViewById(R.id.list_tag1) as TextView
        val imageView = view.findViewById(R.id.list_imageView1) as ImageView

        titleView.text = listViewItemList[position].titleStr
        tagView.text = listViewItemList[position].tagStr
        Picasso.get().load(listViewItemList[position].iconurl).into(imageView)

        view.setOnClickListener {
            // TODO : explain fragment 넣는 부분
            // 여기도 HorizontalAdapter에서와 마찬가지로 recipe를 넣어주는 걸로 하면 될거 같습니다
            // 여기서는 listViewItemList[position].argRecipe를 사용해주시면 될거 같습니다!
            (activity as HomeActivity).changeFragment(explainFrag(fragment,1))
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return listViewItemList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listViewItemList.size
    }

    fun addItem(recipe: Recipe){
        val item = Community_ListViewItem()
        item.iconurl = recipe.pics[0]
        item.titleStr = recipe.cook_title
        item.tagStr = mktag(recipe)
        item.argRecipe = recipe

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