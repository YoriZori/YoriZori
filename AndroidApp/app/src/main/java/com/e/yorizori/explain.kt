package com.e.yorizori

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import com.e.yorizori.Class.Recipe
import kotlinx.android.synthetic.main.activity_explain.*

class explain : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {
// 이부분을 fragment로 바꾸시면 밑에 TODO를 눌러서 적어놓은 걸로 바꿔주세요!

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        var option = 0 // 체크를 하면 레시피를 추천해주기 위해 추천가능한 옵션을 설정한다.
        // if(ing1.isChecked) option +=  ( 체크할 시 추가 )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var actionBar : ActionBar?

        actionBar = supportActionBar;
        actionBar?.hide()

        val intent = getIntent()
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
            arrayOf(Pair("밥", 1), Pair("계란", 1), Pair("식용유", 10)),
            arrayOf(3, 19, 20),
            43
        )
        setContentView(R.layout.activity_explain)
        foodName.text = recipe1.cook_title
        var num : Int = recipe1.scrap_num.toInt()
        scrapNum.text = num.toString()

        val LIST_MENU1 = recipe1.recipe
        val adapter1 = ArrayAdapter(this, android.R.layout.simple_list_item_1,LIST_MENU1)
        val listview1 = findViewById(R.id.recipe_listview) as ListView
        listview1.setAdapter(adapter1)

        val LIST_MENU2 = recipe1.ings
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_list_item_1,LIST_MENU2)
        val listview2 = findViewById(R.id.ing_listview) as ListView
        listview2.setAdapter(adapter2)

        scrapBtn.setOnClickListener(object : View.OnClickListener
        {
            var isDefault = false
            override fun onClick(v: View?) {
                isDefault = !isDefault
                if(isDefault)
                {
                    //scrapBtn.background     // button의 background 모양 변경
                    num +=1   // scrap_num 증가 ( 두번누를때마다 증가하는거 수정 필요)
                    scrapNum.text = num.toString()

                }
            }
        }
        )

        returnBtn.setOnClickListener(object : View.OnClickListener { //return 버튼을 누르면 community뷰로 돌아가기
            override fun onClick(v: View?) {
                finish()
            }
        })
    }



}