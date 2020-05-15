package com.e.yorizori


class Recipe(cook_title: String, recipe : Array<String>, tag : Array<String>, pics : Array<String>, writer : String, ings: Array<Pair<String,Int>>, like_num: Array<Int>, scrap_num : Int){
    /*구조를 만들어 주세요~!*/
    var cook_title : String = cook_title // 제목
    var recipe : Array<String> = recipe// 조리법
    var tag : Array<String> = tag//태그
    var pics : Array<String> = pics//사진
    var writer : String = writer//작성자 아이디
    var ings : Array<Pair<String,Int>> = ings//재료
    var scrap_num :Int = scrap_num//스크랩 수
    var like_num : Array<Int> = like_num//추천수

    constructor() : this("",arrayOf<String>(),arrayOf<String>(),arrayOf<String>(),"",arrayOf<Pair<String,Int>>(),arrayOf<Int>(0,0,0),0)

    constructor(cook_title:String,recipe:Array<String>,tag:Array<String>,pics:Array<String>,writer:String,ings: Array<Pair<String,Int>>): this(cook_title,recipe,tag, pics, writer, ings, arrayOf<Int>(0,0,0), 0)


}
