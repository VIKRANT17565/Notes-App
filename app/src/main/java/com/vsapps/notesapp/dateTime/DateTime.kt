package com.vsapps.notesapp.dateTime

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.*
import com.vsapps.notesapp.R
import com.vsapps.notesapp.ui.NoteActivity
import java.text.DecimalFormat
import java.util.*

class DateTime(private val noteView: NoteActivity): DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    var format = DecimalFormat("00")


    var day = 0
    var month = 0
    var year =  0
    var hour12 = 0
    var hour24 = 0
    var minute = 0
    var AmPm = ""


    var saveDay = 0
    var saveMonth = 0
    var saveYear =  0
    var saveHour12 = 0
    var saveHour24 = 0
    var saveMinute = 0
    var saveAmPm = ""

    init {
        getDateTime()
    }


    private fun getDateTimeCalender() {
        val calendar: Calendar = Calendar.getInstance()
        day = calendar.get(Calendar.DAY_OF_MONTH)
        month = calendar.get(Calendar.MONTH)+1
        year = calendar.get(Calendar.YEAR)
        hour24 = calendar.get(Calendar.HOUR_OF_DAY)
        hour12 = calendar.get(Calendar.HOUR_OF_DAY)
        if (hour12 >= 12){
            AmPm = "PM"
        }else{
            AmPm = "AM"
        }
        hour12 %= 12
        if (hour12 == 0){
            hour12 = 12
        }

        minute = calendar.get(Calendar.MINUTE)

//        saveDay = day
//        saveMonth = month
//        saveYear = year
//        saveHour12 = hour12
//        saveAmPm = AmPm
//        saveMinute = minute


        val dateTime: TextView = noteView.findViewById(R.id.date_time)
        dateTime.text = "${format.format(day)}/${format.format(month)}/${format.format(year)} , ${format.format(hour12)}:${format.format(minute)} $AmPm"
        println("Curr Date : ${format.format(day)}/${format.format(month)}/${format.format(year)} , ${format.format(hour12)}:${format.format(minute)} $AmPm")

        month -= 1

    }

    private fun getDateTime() {
        getDateTimeCalender()
        val dateTime:TextView = noteView.findViewById(R.id.date_time)
        dateTime.setOnClickListener {

            DatePickerDialog(noteView, this, year, month, day, ).show()

        }

//        val date: ImageButton = noteView.findViewById(R.id.select_date_time)
//        date.setOnClickListener {
//
//            DatePickerDialog(noteView, this, year, month, day, ).show()
//
//        }

        val date: ImageView = noteView.findViewById(R.id.select_date_time)
        date.setOnClickListener {

            DatePickerDialog(noteView, this, year, month, day, ).show()

        }

    }



    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        saveDay = day
        saveMonth = month+1
        saveYear = year


        getDateTimeCalender()
        TimePickerDialog(view?.context, this, hour24, minute, false).show()

    }

    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        if (hour >= 12){
            saveAmPm = "PM"
        }else{
            saveAmPm = "AM"
        }
        saveHour24 = hour
        saveHour12 = hour%12
        if (saveHour12 == 0){
            saveHour12 = 12
        }
        saveMinute = minute


        val dateTime: TextView = noteView.findViewById(R.id.date_time)
        dateTime.text = "${format.format(saveDay)}/${format.format(saveMonth)}/${format.format(saveYear)} , ${format.format(saveHour12)}:${format.format(saveMinute)} $saveAmPm"
        println("Saved Date : ${format.format(saveDay)}/${format.format(saveMonth)}/${format.format(saveYear)} , ${format.format(saveHour12)}:${format.format(saveMinute)} $saveAmPm")

        updateDateTime()
    }

    fun updateDateTime(){
        day = saveDay
        month = saveMonth -1
        year = saveYear
        hour12 = saveHour12
        hour24 = saveHour24
        minute = saveMinute
        AmPm = saveAmPm
    }

    fun getdateTime(): String {
        return "${format.format(day)}/${format.format(month+1)}/${format.format(year)} , ${format.format(hour24)}:${format.format(minute)}"
    }
}