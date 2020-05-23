package com.e.yorizori

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Community_ListViewAdapter(context: Context, activity: FragmentActivity?): BaseAdapter(){
    private var itemList = ArrayList<Recipe>()
    private var listViewItemList=ArrayList<Community_HorizontalAdapter>()
    private var titles = arrayOf(null,"#지금 당장만들어 보자","#이 세상 맛이 아니다","#배고프니 빨리빨리","#이 가격 실화냐!?","#다들 이거 만들던데....","#이런건 어때요?")
    private val activity = activity as HomeActivity
    init{
        itemList.add(Recipe("계란볶음밥",arrayOf("1. 기름을 두른다","2. 계란을 깨서 넣고 마구 젓는다","3. 밥을 넣어 잘 풀어준다","4. 밥이 모두 풀어지면 소금간을 한다","5. 완성!"),arrayOf("5분 완성","밥과 계란","응용 가능"),arrayOf("대충 사진"),"내가 쓴거 아님",arrayOf(Pair("밥",1),Pair("계란",1),Pair("식용유",10)),arrayOf(3,19,20),43))
        itemList.add(Recipe("제육볶음",arrayOf("1. 고기에 핏기가 안보일때 까지 볶는다","2. 고추장, 설탕, 간장, 고춧가루를 넣고 잘 섞어준다","3. 적당히 잘 볶아준다","4. 완성!"),arrayOf("고기","고기고기","고기고기고기"),arrayOf("대충 고기"),"고기반찬",arrayOf(Pair("돼지고기",200),Pair("고추장",1),Pair("고춧가루",1),Pair("간장",1),Pair("설탕",1)),arrayOf(25,3,10),87))
        itemList.add(Recipe("소시지볶음",arrayOf("1. 소시지를 기름에 볶는다","2. 케찹을 넣고 마구 젓는다","3. 먹고싶은 야채를 대충 때려 박는다","4. 적당히 익으면 완성!"),arrayOf("소시지 강추","야채 아무거나","국민반찬"),arrayOf("대충 소시지 윤기 좔좔"),"소시지가짱이야",arrayOf(Pair("소시지",10),Pair("케찹",3)),arrayOf(13,15,23),54))
        itemList.add(Recipe("티라미수",arrayOf("1. 계란의 흰자와 노른자를 분리한다","2. 계란 흰자를 열심히 저어서 거품을 낸다", "3. 노른자에도 설탕을 넣으며 밝은 노란색이 될때까지 열심히 젓는다","4. 노른자에 크림치즈를 넣는다","5. 노른자 + 크림치즈에 흰자를 넣는다","6. 빵에 커피를 바른다","7. 커피를 바른 빵 과 흰자 + 노른자 + 크림치즈를 순서대로 쌓는다","8. 코코아파우더를 뿌려 완성한다"),arrayOf("달콤쌉쌀","케이크","홈카페"),arrayOf("맛잇어보이는케이크"),"홈카페 장인",arrayOf(Pair("계란",3),Pair("크림치즈",1),Pair("식빵",2),Pair("카카오파우더",1),Pair("설탕",6)),arrayOf(65,0,23),105))
        itemList.add(Recipe("계란후라이",arrayOf("1. 기름을 둘러 데운다","2. 계란을 깨서 넣는다","3. 원하는 만큼 익힌다","4. 완성!"),arrayOf("다들 하는법 알잖아","이걸 왜봐"),arrayOf("대충 후라이"),"포인트꺼억",arrayOf(Pair("계란",1)),arrayOf(12,74,36),1))

        for (i in 0 until 7) {
            listViewItemList.add(Community_HorizontalAdapter(context, activity as HomeActivity))
            fillList(i)
        }

    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val context = parent.context

        if (view == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.community_list_item_1,parent,false)
        }

        val listTitleView=view!!.findViewById(R.id.item_title) as TextView
        val listView = view.findViewById(R.id.list_horizontal) as RecyclerView
        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        listView.setLayoutManager(layoutManager)

        val ladapter = listViewItemList[position]
        listView.adapter = ladapter
        listTitleView.text = titles[position]

        listTitleView.setOnClickListener(View.OnClickListener {
            activity.changeFragment(Community_SortedList(position,activity))
        })
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

    override fun isEnabled(position: Int): Boolean {
        return false
    }

    fun fillList(position:Int){
        when(position){
            0 -> {//Event Tab...!? 어떻게 채우지 ㄷㄷㄷㄷㄷ

            }
            1->{
                for (i in itemList){
                    listViewItemList[position].addItem(i)
                }
            }
            2->{

                itemList.sortWith(compareByDescending { it.like_num[Like.DELICIOUS.idx] })
                for (i in itemList){
                    listViewItemList[position].addItem(i)
                }
            }
            3->{
                itemList.sortWith(compareByDescending { it.like_num[Like.QUICK.idx] })
                for (i in itemList){
                    listViewItemList[position].addItem(i)
                }
            }
            4-> {
                itemList.sortWith(compareByDescending { it.like_num[Like.CHEAP.idx] })
                for (i in itemList) {
                    listViewItemList[position].addItem(i)
                }
            }
            5->{
                itemList.sortWith(compareByDescending { it.scrap_num })
                for (i in itemList){
                    listViewItemList[position].addItem(i)
                }
            }
            else->{
                itemList.sortWith(compareByDescending { it.like_num[Like.CHEAP.idx] })
                for (i in itemList){
                    listViewItemList[position].addItem(i)
                }
            }

        }
    }
}