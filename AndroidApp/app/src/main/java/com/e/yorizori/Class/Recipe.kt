package com.e.yorizori.Class

import java.util.*

<<<<<<< HEAD

class Recipe(
    cook_title: String,
    recipe: Array<String>,
    tag: Array<String>,
    pics: Array<String>,
    writer_UID: String,
    ings: Array<Pair<String,String>>,
    like_num: Array<Int>,
    scrap_num: Int
){
=======
class Recipe(cook_title: String, recipe : ArrayList<String>, tag : ArrayList<String>, pics : ArrayList<String>, writer_UID : String, ings: ArrayList<Pair<String,String>>, like_num: ArrayList<Int> = arrayListOf(0,0,0), scrap_num : Int = 0){
>>>>>>> saintreal4
    /*구조를 만들어 주세요~!*/

    var cook_title : String = cook_title // 제목
        get() = field
        set(value) {
            field = value
        }
    var recipe : ArrayList<String> = recipe// 조리법
        get() = field
        set(value) {
            field = value
        }
    var tag : ArrayList<String> = tag//태그
        get() = field
        set(value) {
            field = value
        }
    var pics : ArrayList<String> = pics//사진
        get() = field
        set(value) {
            field = value
        }
    var writer_UID : String = writer_UID//작성자 식별자
        get() = field
        set(value) {
            field = value
        }
    var ings : ArrayList<Pair<String,String>> = ings//재료
        get() = field
        set(value) {
            field = value
        }
    var scrap_num :Int = scrap_num//스크랩 수
<<<<<<< HEAD
    var like_num : Array<Int> = like_num//추천수
    var date : Date? = Date()//작성시간

    constructor() : this("",arrayOf<String>(),arrayOf<String>(),arrayOf<String>(),"",arrayOf<Pair<String,String>>(),arrayOf<Int>(0,0,0),0)
    constructor(cook_title:String,
                recipe:Array<String>,
                tag:Array<String>,
                pics:Array<String>,
                writer_UID:String,
                ings: Array<Pair<String,String>>) :
            this(cook_title,recipe,tag, pics, writer_UID, ings, arrayOf<Int>(0,0,0), 0)
=======
        get() = field
        set(value) {
            field = value
        }
    var like_num : ArrayList<Int> = like_num//추천수
        get() = field
        set(value) {
            field = value
        }

    constructor() : this("",arrayListOf<String>(),arrayListOf<String>(),arrayListOf<String>(),"",arrayListOf<Pair<String,String>>(),arrayListOf<Int>(0,0,0),0)


>>>>>>> saintreal4
}
