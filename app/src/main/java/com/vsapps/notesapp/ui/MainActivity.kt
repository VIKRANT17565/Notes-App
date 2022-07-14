package com.vsapps.notesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.vsapps.notesapp.R
import com.vsapps.notesapp.adapter.NoteData
import com.vsapps.notesapp.adapter.NotesAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notesRecyclerView:RecyclerView = findViewById(R.id.notesRecyclerView)
        notesRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val data = ArrayList<NoteData>()
        for (i in 0 until 100) {
            val noteData = NoteData(
                "Title $i",
                "Note $i"
            )
            data.add(noteData)
        }

        val adapter = NotesAdapter(data)
        notesRecyclerView.adapter = adapter

    }
}