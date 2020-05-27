package com.e.yorizori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.e.yorizori.Activity.HomeActivity
import com.e.yorizori.Adapter.ChecklistListAdapter
import com.e.yorizori.Adapter.explainAdapter
import com.e.yorizori.Class.Recipe
import kotlinx.android.synthetic.main.activity_explain.*
import kotlinx.android.synthetic.main.activity_explain.view.*

class explainFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var mList: ArrayList<String?>
        var mListView: ListView
        var mAdapter: ArrayAdapter<*>

        var p : Int = 1

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

        val listView  = view.findViewById<ListView>(R.id.ing_listview)

        val listViewAdapter =
            ChecklistListAdapter(
                this.requireContext(),
                HomeActivity.items
            )

        listView.setAdapter(listViewAdapter)

        val listView2  = view.findViewById<ListView>(R.id.recipe_listview)
        val recipeAdapter = explainAdapter()
        listView2.setAdapter(recipeAdapter)


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
