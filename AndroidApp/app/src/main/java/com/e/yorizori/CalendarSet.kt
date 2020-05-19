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

    }

    private fun showDatePicker() {
        // Calendar
        val c = Calendar.getInstance()
        var year = c.get(Calendar.YEAR)
        var month = c.get(Calendar.MONTH)
        var day = c.get(Calendar.DAY_OF_MONTH)

        val intent = getIntent()
        val name = intent.getStringExtra("ing_name")
        val date = c
        val ref_clicked = RefrigItem(name, date)


        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDayOfMonth ->
                val intent = getIntent()
                val name = intent.getStringExtra("ing_name")
                val date = mYear.toString() + "-" + (mMonth+1).toString() + "-" + mDayOfMonth.toString()

                val ref_clicked = RefrigItem(name, date)
                HomeActivity.add_item(name, date)
                finish()
            }, year, month, day)
        dpd.show()
    }
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
