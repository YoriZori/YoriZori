package com.e.yorizori.Adapter

import android.app.Activity
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
import com.e.yorizori.Class.Recipe
import com.e.yorizori.R
import com.e.yorizori.explainFrag
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class WroteAdapter(fragment : Fragment, activity : FragmentActivity) : BaseAdapter() {
    private var parent = fragment
    private var activity = activity
    private var Wrote_Recipe : ArrayList<Recipe> = arrayListOf()

    init{
        Wrote_Recipe = OpeningActivity.recipe_list.filter{
            it.writer_UID == FirebaseAuth.getInstance().currentUser!!.uid
        } as ArrayList<Recipe>
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

        titleView.text = Wrote_Recipe[position].cook_title
        tagView.text = mktag(Wrote_Recipe[position])
        Picasso.get().load(Wrote_Recipe[position].pics[0]).into(imageView)

        view.setOnClickListener {
            (activity as HomeActivity).changeFragment(explainFrag(this.parent,3))
        }


        return view
    }

    override fun getItem(position: Int): Any {
        return Wrote_Recipe[0]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return Wrote_Recipe.size
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