package com.vsapps.notesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.vsapps.notesapp.R
import com.vsapps.notesapp.roomDatabase.NoteEntity
import com.vsapps.notesapp.roomDatabase.NoteViewModel

class NoteDescriptionActivity : AppCompatActivity() {

    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_description)

        val title = intent.getStringExtra("title").toString()
        var note = intent.getStringExtra("note").toString()
        val noteEntity:NoteEntity = intent.getSerializableExtra("noteEntity") as NoteEntity
        if (note.isEmpty()){
            note = "No description"
        }

        val deleteNote:ImageView = findViewById(R.id.deleteNote)

        val titleText:TextView = findViewById(R.id.title)
        titleText.text = title

        val noteText:TextView = findViewById(R.id.note)
        noteText.text = note


        deleteNote.setOnClickListener {
            viewModel  = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]
            viewModel.deleteNote(noteEntity)
            finish()
        }


    }

}