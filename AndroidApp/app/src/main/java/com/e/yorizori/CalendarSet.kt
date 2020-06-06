package com.e.yorizori

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.e.yorizori.Activity.OpeningActivity
import com.e.yorizori.Class.RefrigItem
import java.util.*

class CalendarSet : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showDatePicker()

    }

    private fun showDatePicker() {
        // Calendar
        val c = Calendar.getInstance()
        var year = c.get(Calendar.YEAR)
        var month = c.get(Calendar.MONTH)
        var day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDayOfMonth ->
                val intent = getIntent()
                val name = intent.getStringExtra("ing_name")
                val date = mYear.toString() + "-" + (mMonth+1).toString() + "-" + mDayOfMonth.toString()

                val ref_clicked = RefrigItem(name, date)
                OpeningActivity.add_item(name, date)
                finish()
            }, year, month, day)
        dpd.show()
    }

}
