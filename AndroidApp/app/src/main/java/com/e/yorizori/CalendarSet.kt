package com.e.yorizori

import android.app.DatePickerDialog
import android.graphics.Color.RED
import android.graphics.Color.CYAN
import android.graphics.Color.BLACK
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_checklist.*
import java.util.*

class CalendarSet : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_set)

        showDatePicker()

        val btn2 : Button =  findViewById(R.id.btn_reInput_date)
        btn2.setOnClickListener {
            showDatePicker()
        }

        val btn : Button =  findViewById(R.id.btn_confirm)
        btn.setOnClickListener {
            var intent : Intent = Intent( this, HomeActivity::class.java)
            startActivity(intent)
        }

    }

    private fun showDatePicker() {
        // Calendar
        val c = Calendar.getInstance()
        var year = c.get(Calendar.YEAR)
        var month = c.get(Calendar.MONTH)
        var day = c.get(Calendar.DAY_OF_MONTH)
        var lTime = c.timeInMillis / (24*60*60*1000)
        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDayOfMonth ->
                due_date_txt.setText(" " + mYear + "." + (mMonth+1) + "." + mDayOfMonth)
                due_date_txt.setTextColor(BLACK)
                setColor(lTime)
            }, year, month, day)
        dpd.show()
    }

    // 아직 작동 안함
    fun setColor(lt : Long) {
        var n : Long = (Calendar.getInstance().timeInMillis) / (24*60*60*1000)
        var nD : Int = n.toInt()
        var dDay : Int = lt.toInt()
        dDay = nD - dDay
        if (dDay < 0) {
            due_date_txt.setTextColor(RED)
        }
        else if (0< dDay) {
            due_date_txt.setTextColor(CYAN)
        }
    }
}
