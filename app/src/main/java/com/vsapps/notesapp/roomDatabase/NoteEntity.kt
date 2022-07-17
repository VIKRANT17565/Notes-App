package com.vsapps.notesapp.roomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "NoteDatabase")
data class NoteEntity(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "note") val note: String):Serializable{
    @PrimaryKey(autoGenerate = true) var uid = 0
}