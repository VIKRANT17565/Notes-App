package com.vsapps.notesapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.vsapps.notesapp.R
import com.vsapps.notesapp.roomDatabase.NoteEntity
import com.vsapps.notesapp.roomDatabase.NoteViewModel

class NoteDescriptionActivity : AppCompatActivity() {

    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_description)

        var title = intent.getStringExtra("title").toString()
        var note = intent.getStringExtra("note").toString()
        var noteEntity:NoteEntity = intent.getSerializableExtra("noteEntity") as NoteEntity
        if (note.isEmpty()){
            note = "No description"
        }

        val deleteNote:ImageView = findViewById(R.id.deleteNote)

        val titleText:TextView = findViewById(R.id.title)
        titleText.text = title

        val noteText:TextView = findViewById(R.id.note)
        noteText.text = note


//        NoteEntity(title=iysiyx, note=7tsigdiyd)
//        NoteEntity(title=iysiyx, note=7tsigdiyd)

        deleteNote.setOnClickListener {
            viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]
            viewModel.deleteNote(noteEntity)
            println("##################################################################################################Deleted")
            println(noteEntity)
            finish()
        }


    }
}