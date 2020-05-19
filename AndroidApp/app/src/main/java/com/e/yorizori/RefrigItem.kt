package com.e.yorizori

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class RefrigItem(){
    /*구조를 만들어 주세요~!*/
    var item : String = "" // 재료 이름
    val simpleDate : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
<<<<<<< HEAD
    var due = simpleDate.parse("9999-12-31")

    constructor(item_: String): this() {
=======
    val ONE_DAY = 24*60*60*1000
    var due : Date? = due
    //var dDay : Long = 10000

    constructor(item_: String): this(item_,null) {
>>>>>>> c365a9ab9fe7cbaa4d13ba533972fd5174c92150
        item = item_
        due = simpleDate.parse("9999-12-31")
        //dDay = caldDay(due)
    }

    constructor(item_: String, due_ : String): this(item_) {
        item = item_
        this.due = simpleDate.parse(due_)
    }

    fun print_due() : String {
        var return_me : String
        if (due == simpleDate.parse("9999-12-31"))
            return_me = ""
        else
            return_me = simpleDate.format(due)
        return return_me
    }

<<<<<<< HEAD
    fun get_val() : Int {
        if (due == simpleDate.parse("9999-12-31"))
            return 100000000
        else{
            var arr = simpleDate.format(due).split("-")
            return arr[0].toInt()*10000 + arr[1].toInt()*100 + arr[2].toInt()
        }
    }

    companion object {
        fun sort(items : MutableList<RefrigItem>) {
            items.sortWith(compareBy({it.get_val()}, {it.item}))
        }
    }
=======
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
>>>>>>> c365a9ab9fe7cbaa4d13ba533972fd5174c92150
}