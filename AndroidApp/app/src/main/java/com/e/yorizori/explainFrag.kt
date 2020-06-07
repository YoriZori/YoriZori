package com.e.yorizori

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.e.yorizori.Activity.HomeActivity
import com.e.yorizori.Adapter.ingAdapter
//import com.e.yorizori.Adapter.explainAdapter
import com.e.yorizori.Class.Recipe
import com.e.yorizori.Class.ScrapInfo
import com.e.yorizori.Class.likeCheck
import com.e.yorizori.Class.scrapCheck
import com.e.yorizori.Fragment.Community
import com.e.yorizori.Fragment.Community_SortedList
import com.e.yorizori.Interface.BackBtnPressListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_explain.*
import kotlinx.android.synthetic.main.activity_explain.view.*

class explainFrag(parent : Fragment, option : Int, item : Recipe?, tag : String?) : BackBtnPressListener,Fragment() {
    private val parent = parent
    private val option = option
    private val recipeAll = item
    private val tagStr = tag
    val firebaseAuth = FirebaseAuth.getInstance()
    val userUID = firebaseAuth.currentUser!!.uid
   // private lateinit var userUID : String
    private var rootRef = FirebaseDatabase.getInstance().reference
    var scraped = false
    var priced = false
    var simpled = false
    var delicioused = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val child = rootRef.child("재료")

        //scrap여부
        val finding = HomeActivity.scrap_info.filter{
            Log.d("recipes#####","key: ${it.key}, title : ${it.title}, writer : ${it.writer}")
            it.has(recipeAll!!.cook_title,recipeAll!!.writer_UID)
        }
        val finding1 = HomeActivity.price_info.filter{
            Log.d("recipes#####","key: ${it.key}, title : ${it.title}, writer : ${it.writer}")
            it.has(recipeAll!!.cook_title,recipeAll!!.writer_UID)
        }
        val finding2 = HomeActivity.simp_info.filter{
            Log.d("recipes#####","key: ${it.key}, title : ${it.title}, writer : ${it.writer}")
            it.has(recipeAll!!.cook_title,recipeAll!!.writer_UID)
        }
        val finding3 = HomeActivity.del_info.filter{
            Log.d("recipes#####","key: ${it.key}, title : ${it.title}, writer : ${it.writer}")
            it.has(recipeAll!!.cook_title,recipeAll!!.writer_UID)
        }
        scraped = finding.isNotEmpty()
        priced = finding1.isNotEmpty()
        simpled = finding2.isNotEmpty()
        delicioused = finding3.isNotEmpty()

        val view = inflater.inflate(R.layout.activity_explain, container, false)
        // Inflate the layout for this fragment
        // 뒤로가기버튼을 누를시 community로 돌아가기
        view.returnBtn.setOnClickListener {
            onBack()
        }

        view!!.scrapBtn.isSelected = scraped
        view!!.scrapBtn.isSelected = priced
        view!!.scrapBtn.isSelected = simpled
        view!!.scrapBtn.isSelected = delicioused

        // 레시피클래스 가져오기
        var recipe1 = recipeAll

        //초기값 설정
        view.foodName.text = recipe1?.cook_title
        view.price_num.text = recipe1?.like_num!![2].toString()
        view.simple_num.text = recipe1?.like_num[1].toString()
        view.del_num.text = recipe1?.like_num[0].toString()
        view.scrapNum.text = recipe1?.scrap_num.toString()
        view.scrapTag.text = tagStr
        val picc = view.findViewById<ImageView>(R.id.photoView)
        Picasso.get().load(recipe1?.pics[0]).into(picc)

        // 재료 리스트뷰
        val LIST_MENU1 = recipe1.ings
        val adapter1 = ingAdapter(this.context!!,recipe1.ings)
        val listview1 = view.findViewById<ListView>(R.id.ing_ListView)
        listview1.setAdapter(adapter1)

        // 조리법 리스트뷰
        val LIST_MENU2 = recipe1.recipe
        val adapter2 = ArrayAdapter(this.context!!, android.R.layout.simple_list_item_1,LIST_MENU2)
        val listview2 = view.findViewById<ListView>(R.id.recipe_listview)
        listview2.setAdapter(adapter2)


        //Listview의 높이를 Item의 갯수에 따라 조정하는 함수
        fun setListViewHeightBasedOnItems(listView:ListView):Boolean {
            val listAdapter = listView.getAdapter()
            if (listAdapter != null)
            {
                val numberOfItems = listAdapter.getCount()
                // Get total height of all items.
                var totalItemsHeight = 0
                for (itemPos in 0 until numberOfItems)
                {
                    val item = listAdapter.getView(itemPos, null, listView)
                    val px = 500 * (listView.getResources().getDisplayMetrics().density)
                    item.measure(View.MeasureSpec.makeMeasureSpec(px.toInt(), View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
                    totalItemsHeight += item.getMeasuredHeight()
                }
                // Get total height of all item dividers.
                val totalDividersHeight = (listView.getDividerHeight() * (numberOfItems - 1))
                // Get padding
                val totalPadding = listView.getPaddingTop() + listView.getPaddingBottom()
                // Set list height.
                val params = listView.getLayoutParams()
                params.height = totalItemsHeight + totalDividersHeight + totalPadding
                listView.setLayoutParams(params)
                listView.requestLayout()
                return true
            }
            else
            {
                return false
            }
        }
        setListViewHeightBasedOnItems(listview1)
        setListViewHeightBasedOnItems(listview2)

        // 스크랩버튼 누를 때
        view.scrapBtn.setOnClickListener {
            scrapBtn.isSelected = !scraped
            scraped = !scraped
            if(scraped) {
                recipe1.scrap_num += 1
            }
            else {
                recipe1.scrap_num -= 1
            }
            scrapNum.text = recipe1.scrap_num.toString()
            rootRef.child("recipe/${recipe1.cook_title}").setValue(Gson().toJson(recipe1))
        }

        // 가성비버튼 누를 때
        view.price_btn.setOnClickListener {
            price_btn.isSelected = !priced
            priced = !priced
            if(priced) {
                recipe1.like_num[2] += 1
            }
            else {
                recipe1.like_num[2] -= 1
            }
            price_num.text = recipe1.like_num[2].toString()
            rootRef.child("recipe/${recipe1.cook_title}").setValue(Gson().toJson(recipe1))
        }

        // 간단해요버튼 누를 때
        view.simple_btn.setOnClickListener {
            simple_btn.isSelected = !simpled
            simpled = !simpled
            if(simpled) {
                recipe1.like_num[1] += 1
            }
            else {
                recipe1.like_num[1] -= 1
            }
            simple_num.text = recipe1.like_num[1].toString()
            rootRef.child("recipe/${recipe1.cook_title}").setValue(Gson().toJson(recipe1))
        }

        // 맛있어요버튼 누를 때
        view.del_btn.setOnClickListener {
            del_btn.isSelected = !delicioused
            delicioused = !delicioused
            if(delicioused) {
                recipe1.like_num[0] += 1
            }
            else {
                recipe1.like_num[0] -= 1
            }
            del_num.text = recipe1.like_num[0].toString()
            rootRef.child("recipe/${recipe1.cook_title}").setValue(Gson().toJson(recipe1))
        }


        if(option == 0)
            (parent as Community).saveInfo(2,this)
        else
            (parent as Community_SortedList).saveInfo(1,this)

        (activity as HomeActivity).setOnBackBtnListener(this)
        return view

    }

    override fun onBack() {
        if(option == 0)
            (parent as Community).saveInfo(2,null)
        else
            (parent as Community_SortedList).saveInfo(1,null)
        var ft = (activity as HomeActivity).supportFragmentManager
        ft.beginTransaction().remove(this).commit()
        ft.popBackStack()
    }

    override fun onPause() {
        super.onPause()
        Log.d("#######","onPause")
        var tmp = HomeActivity.scrap_info.filter{ it.has(recipeAll!!.cook_title,recipeAll.writer_UID)}
        var tmp1 = HomeActivity.price_info.filter{ it.has(recipeAll!!.cook_title,recipeAll.writer_UID)}
        var tmp2 = HomeActivity.simp_info.filter{ it.has(recipeAll!!.cook_title,recipeAll.writer_UID)}
        var tmp3 = HomeActivity.del_info.filter{ it.has(recipeAll!!.cook_title,recipeAll.writer_UID)}

        if(priced){
            if(tmp1.isEmpty()){
                rootRef.child("$userUID/price_checked").push().setValue("${recipeAll!!.cook_title},${recipeAll.writer_UID}")
            }
        }
        if(simpled){
            if(tmp2.isEmpty()){
                rootRef.child("$userUID/simple_checked").push().setValue("${recipeAll!!.cook_title},${recipeAll.writer_UID}")
            }
        }

        if(delicioused){
            if(tmp3.isEmpty()){
                rootRef.child("$userUID/delicious_checked").push().setValue("${recipeAll!!.cook_title},${recipeAll.writer_UID}")
            }
        }
        if(scraped){
            if(tmp.isEmpty()){
                rootRef.child("$userUID/scrap").push().setValue("${recipeAll!!.cook_title},${recipeAll.writer_UID}")
            }
        }
        else{
            if(tmp.isNotEmpty()){
                rootRef.child("$userUID/scrap/${tmp.last().key}").removeValue()
            }
            if(tmp1.isNotEmpty()){
                rootRef.child("$userUID/price_checked/${tmp1.last().key}").removeValue()
            }
            if(tmp2.isNotEmpty()){
                rootRef.child("$userUID/simple_checked/${tmp1.last().key}").removeValue()
            }
            if(tmp3.isNotEmpty()){
                rootRef.child("$userUID/delicious_checked/${tmp1.last().key}").removeValue()
            }
        }
    }


}
