package com.e.yorizori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.yorizori.Adapter.FoodDataAdapter
import com.e.yorizori.Class.FoodModel
//import com.e.yorizori.Adapter.explainAdapter
import com.e.yorizori.Class.Recipe
import kotlinx.android.synthetic.main.activity_explain.*
import kotlinx.android.synthetic.main.activity_explain.view.*

class explainFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_explain, container, false)
        // Inflate the layout for this fragment

        // 뒤로가기버튼을 누를시 community로 돌아가기
        view.returnBtn.setOnClickListener {
            val fragmentManager: FragmentManager = activity!!.supportFragmentManager
            fragmentManager.beginTransaction().remove(this@explainFrag).commit()
            fragmentManager.popBackStack()
        }



        //임시저장 레시피
        var recipe1 = Recipe(
            "계란볶음밥",
            arrayOf(
                "1. 기름을 두른다",
                "2. 계란을 깨서 넣고 마구 젓는다",
                "3. 밥을 넣어 잘 풀어준다",
                "4. 밥이 모두 풀어지면 소금간을 한다",
                "5. 완성!"
            ),
            arrayOf("5분 완성", "밥과 계란", "응용 가능"),
            arrayOf("대충 사진"),
            "내가 쓴거 아님",
            arrayOf(Pair("밥", "1그릇"), Pair("계란", "1개"), Pair("식용유", "10스푼")),
            arrayOf(3, 19, 20),
            43
        )

        view.foodName.text = recipe1.cook_title
        view.scrapNum.text = recipe1.scrap_num.toString()
        view.tag_array.scrapTag.text = recipe1.tag[0]

        val foodList = listOf(
            FoodModel("밥", "1그릇", false),
            FoodModel("계란","1개",false),
            FoodModel("식용유","10스푼",false),
            FoodModel("소시지","100개",false)
        )

        val adapter = FoodDataAdapter(foodList)
        val recyclerview2 = view.findViewById<RecyclerView>(R.id.foodListView)
        recyclerview2.adapter = adapter
        recyclerview2.layoutManager = LinearLayoutManager(this.context)

        /*
        val listView  = view.findViewById<ListView>(R.id.ing_listview)
        val listViewAdapter = ChecklistListAdapter(this.requireContext(),(activity as HomeActivity).items)
        listView.setAdapter(listViewAdapter)
*/

        val LIST_MENU2 = recipe1.recipe
        val adapter2 = ArrayAdapter(this.context!!, android.R.layout.simple_list_item_1,LIST_MENU2)
        val listview2 = view.findViewById<ListView>(R.id.recipe_listview)
        listview2.setAdapter(adapter2)

        view.scrapBtn.setOnClickListener(object : View.OnClickListener
        {
            var isDefault = false
            override fun onClick(v: View?) {
                isDefault = !isDefault
                if(isDefault)
                {
                    scrapBtn.setSelected(true)
                    recipe1.scrap_num +=1
                    scrapNum.text = recipe1.scrap_num.toString()

                }
                else
                {
                    scrapBtn.setSelected(false)
                    recipe1.scrap_num -=1
                    scrapNum.text = recipe1.scrap_num.toString()
                }
            }
        }
        )




        return view

    }




}
