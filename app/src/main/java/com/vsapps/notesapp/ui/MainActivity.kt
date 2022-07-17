package com.vsapps.notesapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vsapps.notesapp.R
import com.vsapps.notesapp.adapter.INotesAdapter
import com.vsapps.notesapp.adapter.NotesAdapter
import com.vsapps.notesapp.roomDatabase.NoteEntity
import com.vsapps.notesapp.roomDatabase.NoteViewModel


class MainActivity : AppCompatActivity(), INotesAdapter {

    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notesRecyclerView:RecyclerView = findViewById(R.id.notesRecyclerView)
        notesRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        val adapter = NotesAdapter(this, this)
        notesRecyclerView.adapter = adapter

        viewModel  = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]

        viewModel.notes.observe(this, Observer { list-> list?.let{
            adapter.updateList(it)
        }
        })
        println("updated#############################################################")


        val addNote:FloatingActionButton = findViewById(R.id.addNote)

        addNote.setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onItemClick(note: NoteEntity) {
        println("######################################## $note")
        val intent = Intent(this, NoteDescriptionActivity::class.java)
        intent.putExtra("title", note.title)
        intent.putExtra("note", note.note)
        intent.putExtra("noteEntity", note)
        startActivity(intent)
//        viewModel.deleteNote(note)
//        Toast.makeText(this,"${note.text} Deleted", Toast.LENGTH_SHORT).show()
    }

}