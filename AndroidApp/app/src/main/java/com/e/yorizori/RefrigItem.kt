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
    val ONE_DAY = 24*60*60*1000
    var due : Date? = due
    //var dDay : Long = 10000

    constructor(item_: String): this(item_,null) {
        item = item_
        due = simpleDate.parse("9999-12-31")
        //dDay = caldDay(due)
    }

    fun print_due() : String {
        var return_me : String
        if (due == simpleDate.parse("9999-12-31"))
            return_me = ""
        else
            return_me = simpleDate.format(due)
        return return_me
    }

    /*
    fun caldDay(due: Date?) : Long {
        var ddayCal : Calendar = Calendar.getInstance()
        ddayCal.set(a_year, a_monthOfYear, a_dayOfMonth)
        var dday : Long = ddayCal.getTimeInMillis() / ONE_DAY
        var today : Long = Calendar.getInstance().getTimeInMillis() / ONE_DAY
        var result : Long = dday - today
        return result
    }
    */
}