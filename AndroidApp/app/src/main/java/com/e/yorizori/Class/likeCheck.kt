package com.e.yorizori.Class

class likeCheck(like_num : ArrayList<Boolean> = arrayListOf(false,false,false)){
    var like_num : ArrayList<Boolean> = like_num//추천수
    get() = field
        set(value) {
            field = value
        }
    constructor() : this(arrayListOf<Boolean>())
}