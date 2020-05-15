package com.e.yorizori

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class RefrigItem(item : String, due : Date?){
    /*구조를 만들어 주세요~!*/
    var item : String = "" // 재료 이름
    val simpleDate : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    var due : Date? = due


    constructor(item_: String): this(item_,null) {
        item = item_
        due = simpleDate.parse("9999-12-31")
    }

    fun print_due() : String {
        var return_me : String
        if (due == simpleDate.parse("9999-12-31"))
            return_me = ""
        else
            return_me = simpleDate.format(due)
        return return_me
    }
}