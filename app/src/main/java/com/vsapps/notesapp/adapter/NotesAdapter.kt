package com.vsapps.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vsapps.notesapp.adapter.NoteData

class NotesAdapter(private val notes:ArrayList<NoteData>):RecyclerView.Adapter<NotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_notes, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNote = notes[position]
        holder.title.text = currentNote.title
        holder.notes.text = currentNote.note
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}

class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title:TextView = itemView.findViewById(R.id.title)
    val notes:TextView = itemView.findViewById(R.id.notes)
}
