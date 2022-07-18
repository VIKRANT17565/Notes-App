package com.vsapps.notesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vsapps.notesapp.R
import com.vsapps.notesapp.roomDatabase.NoteEntity
import com.vsapps.notesapp.roomDatabase.NoteViewModel
import java.util.ArrayList

class NoteDescriptionActivity : AppCompatActivity() {

    lateinit var viewModel: NoteViewModel
    var notesArrayList  = ArrayList<NoteEntity>()
    var noteEntityIndex :Int = 0

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


        deleteNote.setOnClickListener {
            viewModel  = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]

            println("%%%%%%%%%%%%%%%%%%%%%%%%%% $noteEntity")

            viewModel.deleteNote(noteEntity)
            println("################################################################################################## deleted")
            finish()
        }


    }


    private fun updateList(newList: List<NoteEntity>, index: Int){
        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@##################################################### $newList")
        notesArrayList.clear()
        notesArrayList.addAll(newList)
        noteEntityIndex = index

        println("updateList #")
        println(notesArrayList)
        println(noteEntityIndex)

        viewModel.deleteNote(notesArrayList.get(noteEntityIndex))
    }
}