package com.e.yorizori

enum class like(val idx:Int){
    EASY(0),
    QUICK(1),
    CHEAP(2)
}

class Recipe(){
    /*구조를 만들어 주세요~!*/
    var cook_title : String = "" // 제목
    var recipe : Array<String> = []// 조리법
    var tag : Array<String> = []//태그
    var pics : Array<String> = []//사진
    var writer : String = ""//작성자 아이디
    var scrap_num :Int = 0//스크랩 수
    var ings : Array<Pair<String,Int>>? = null//재료
    var like_num : Array<Int> = []//추천수

    constructor(){
        cook_title=""
        recipe=[]
        tag=[]
        pics=[]
        writer=""
        scrap_num=0
        ings =[]
        like_num=[0,0,0]
    }

}