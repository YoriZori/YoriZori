package com.e.yorizori

enum class like(val idx:Int){
    EASY(0),
    QUICK(1),
    CHEAP(2)
}

class Recipe(cook_title: String, recipe : Array<String>, tag : Array<String>, pics : Array<String>, writer : String, ings: Array<Pair<String,Int>>, like_num: Array<Int>, scrap_num : Int){
    /*구조를 만들어 주세요~!*/
    var cook_title : String = "" // 제목
    var recipe : Array<String> = arrayOf<String>()// 조리법
    var tag : Array<String> = arrayOf<String>()//태그
    var pics : Array<String> = arrayOf<String>()//사진
    var writer : String = ""//작성자 아이디
    var ings : Array<Pair<String,Int>> = arrayOf<Pair<String,Int>>()//재료
    var scrap_num :Int = 0//스크랩 수
    var like_num : Array<Int> = arrayOf<Int>()//추천수

    constructor() : this("",arrayOf<String>(),arrayOf<String>(),arrayOf<String>(),"",arrayOf<Pair<String,Int>>(),arrayOf<Int>(0,0,0),0)

    constructor(cook_title:String,recipe:Array<String>,tag:Array<String>,pics:Array<String>,writer:String,ings: Array<Pair<String,Int>>): this(cook_title,recipe,tag, pics, writer, ings, arrayOf<Int>(0,0,0), 0)

    
}