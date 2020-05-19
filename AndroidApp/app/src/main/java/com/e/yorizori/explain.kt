package com.e.yorizori

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.CompoundButton
import kotlinx.android.synthetic.main.activity_explain.*

class explain : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        var option = 0 // 체크를 하면 레시피를 추천해주기 위해 추천가능한 옵션을 설정한다.
        // if(ing1.isChecked) option +=  ( 체크할 시 추가 )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explain)

        scrapBtn.setOnClickListener(object : CompoundButton.OnCheckedChangeListener,
            View.OnClickListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                when(isChecked)
                {
                    // 1. 버튼 모양 변경
                    // 2. 스크랩수 증가
                }
            }

            override fun onClick(v: View?) {
                TODO("Not yet implemented")
            }

        })

        returnBtn.setOnClickListener(object : View.OnClickListener { //return 버튼을 누르면 community뷰로 돌아가기
            override fun onClick(v: View?) {
                finish()
            }
        })
    }



}