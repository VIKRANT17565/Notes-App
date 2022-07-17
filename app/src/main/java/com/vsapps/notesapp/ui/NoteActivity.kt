package com.vsapps.notesapp.ui

import android.app.*
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.vsapps.notesapp.R
import com.vsapps.notesapp.dateTime.DateTime
import com.vsapps.notesapp.notification.NoteNotification
import com.vsapps.notesapp.notification.SetAlarm
import com.vsapps.notesapp.roomDatabase.NoteEntity
import com.vsapps.notesapp.roomDatabase.NoteViewModel
import java.text.SimpleDateFormat
import java.util.*


class NoteActivity : AppCompatActivity() {

    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        val inputDescription:TextInputEditText = findViewById(R.id.input_description)
        inputDescription.gravity = Gravity.TOP
        inputDescription.setHorizontallyScrolling(false)
        inputDescription.maxLines = 17

        val dt = DateTime(this)

        val setDateTime: TextView = findViewById(R.id.date_time)

        val alarmManager:AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        val saveNote: Button = findViewById(R.id.saveNote)
        saveNote.setOnClickListener {
            if (validateTitle()) {

                val title: TextInputLayout = findViewById(R.id.text_input_title)
                val titleInput = title.editText?.text.toString()

                val note: TextInputLayout = findViewById(R.id.text_input_description)
                val noteInput = note.editText?.text.toString()

                val dateTime = dt.getdateTime()// + " GMT+05:30"
                println(dateTime)

//                16/06/2022 , 09:40 PM GMT+05:30
                val formatter:SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy , HH:mm", Locale.ENGLISH)

                val dateTimeToMs :Long= formatter.parse(dateTime).time.toLong()
                println(dateTimeToMs)

                val timeOfNoteSaved: Long = System.currentTimeMillis()
                println(timeOfNoteSaved)

                val delay: Long = 0
                println(delay)
                val intent = Intent(this, NoteNotification::class.java)

                intent.putExtra("title", titleInput)
                intent.putExtra("note", noteInput)


                val pendingIntent = PendingIntent.getBroadcast(this, dateTimeToMs.toInt(), intent,PendingIntent.FLAG_CANCEL_CURRENT)

                SetAlarm(dateTimeToMs, pendingIntent, alarmManager)


//                alarmManager.set(AlarmManager.RTC_WAKEUP, dateTimeToMs + delay, pendingIntent)

                viewModel  = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
                viewModel.insertNote(NoteEntity(titleInput, noteInput))

                Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show()

                finish()
            }
        }





    }


    private fun validateTitle(): Boolean {
        val title:TextInputLayout = findViewById(R.id.text_input_title)
        val titleInput = title.editText?.text.toString()
        if (titleInput.isEmpty()) {
            title.error = "Field can't be empty"
            return false
        } else {
            title.error = null
            return true
        }
    }

    private fun validateDescription(): String {
        val note:TextInputLayout = findViewById(R.id.text_input_title)
        val noteInput = note.editText?.text.toString()
        if (noteInput.isEmpty()) {
            return "No description"
        }
        return noteInput
    }

}