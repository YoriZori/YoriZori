package com.e.yorizori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.yorizori.Activity.HomeActivity
import com.e.yorizori.Adapter.FoodDataAdapter
import com.e.yorizori.Class.FoodModel
//import com.e.yorizori.Adapter.explainAdapter
import com.e.yorizori.Class.Recipe
import com.e.yorizori.Fragment.Community
import com.e.yorizori.Fragment.Community_SortedList
import com.e.yorizori.Interface.BackBtnPressListener
import kotlinx.android.synthetic.main.activity_explain.*
import kotlinx.android.synthetic.main.activity_explain.view.*

class explainFrag(parent : Fragment, option : Int) : BackBtnPressListener,Fragment() {
    private val parent = parent
    private val option = option
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.activity_explain, container, false)
        // Inflate the layout for this fragment
        // 뒤로가기버튼을 누를시 community로 돌아가기
        view.returnBtn.setOnClickListener {
            onBack()
        }


        /*
        val database = FirebaseDatabase.getInstance()
        val recipe10 = database.getReference("recipe")
        val recipe20 = recipe10.child("간장계란밥").child("cook_title")
*/

        var recipe1 = Recipe(
            "계란볶음밥",
            arrayListOf(
                "1. 기름을 두른다",
                "2. 계란을 깨서 넣고 마구 젓는다",
                "3. 밥을 넣어 잘 풀어준다",
                "4. 밥이 모두 풀어지면 소금간을 한다",
                "5. 완성!"
            ),
            arrayListOf("5분 완성", "밥과 계란", "응용 가능"),
            arrayListOf("대충 사진"),
            "내가 쓴거 아님",
            arrayListOf(Pair("밥", "1그릇"), Pair("계란", "1개"), Pair("식용유", "10스푼")),
            arrayListOf(3, 19, 20),
            43
        )

        view.foodName.text = recipe1.cook_title
        view.price_num.text = recipe1.like_num[2].toString()
        view.simple_num.text = recipe1.like_num[1].toString()
        view.del_num.text = recipe1.like_num[0].toString()

        /*
        view.foodName.text = recipe20.toString()
        view.scrapNum.text = recipe1.scrap_num.toString()
        view.tag_array.scrapTag.text = recipe1.tag[0]
*/

        val foodList = listOf(
            FoodModel("밥", "1그릇", false),
            FoodModel("계란","1개",false),
            FoodModel("식용유","10스푼",false),
            FoodModel("소시지","100개",false)
        )

        // 재료 리사이클러뷰
        val adapter = FoodDataAdapter(foodList)
        val recyclerview2 = view.findViewById<RecyclerView>(R.id.foodListView)
        recyclerview2.adapter = adapter
        recyclerview2.layoutManager = LinearLayoutManager(this.context)

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
        setListViewHeightBasedOnItems(listview2)

        // 스크랩버튼 누를 때
        view.scrapBtn.setOnClickListener(object : View.OnClickListener {
            var isDefault = false
            override fun onClick(v: View?) {
                isDefault = !isDefault
                if (isDefault) {
                    scrapBtn.setSelected(true)
                    recipe1.scrap_num += 1
                    scrapNum.text = recipe1.scrap_num.toString()

                } else {
                    scrapBtn.setSelected(false)
                    recipe1.scrap_num -= 1
                    scrapNum.text = recipe1.scrap_num.toString()
                }
            }
        })

        // 가성비버튼 누를 때
        view.price_btn.setOnClickListener(object : View.OnClickListener {
            var isDefault = false
            override fun onClick(v: View?) {
                isDefault = !isDefault
                if(isDefault) {
                    price_btn.setSelected(true)
                    recipe1.like_num[2] += 1
                    price_num.text = recipe1.like_num[2].toString()
                }
                else {
                    price_btn.setSelected(false)
                    recipe1.like_num[2] -= 1
                    price_num.text = recipe1.like_num[2].toString()
                }
            }

        })

        // 간단해요버튼 누를 때
        view.simple_btn.setOnClickListener(object : View.OnClickListener {
            var isDefault = false
            override fun onClick(v: View?) {
                isDefault = !isDefault
                if(isDefault) {
                    simple_btn.setSelected(true)
                    recipe1.like_num[1] += 1
                    simple_num.text = recipe1.like_num[1].toString()
                }
                else {
                    simple_btn.setSelected(false)
                    recipe1.like_num[1] -= 1
                    simple_num.text = recipe1.like_num[1].toString()
                }
            }

        })

        // 맛있어요버튼 누를 때
        view.del_btn.setOnClickListener(object : View.OnClickListener {
            var isDefault = false
            override fun onClick(v: View?) {
                isDefault = !isDefault
                if(isDefault) {
                    del_btn.setSelected(true)
                    recipe1.like_num[0] += 1
                    del_num.text = recipe1.like_num[0].toString()
                }
                else {
                    price_btn.setSelected(false)
                    recipe1.like_num[0] -= 1
                    del_num.text = recipe1.like_num[0].toString()
                }
            }

        })

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


}
