package com.vsapps.notesapp.notification

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.vsapps.notesapp.R
import com.vsapps.notesapp.roomDatabase.NoteEntity
import com.vsapps.notesapp.ui.MainActivity
import com.vsapps.notesapp.ui.NoteDescriptionActivity


class NoteNotification: BroadcastReceiver(){

    private val CHANNEL_ID = "remindMe"
    private val NOTIFICATION_ID = 256
//    private val GROUP_KEY = "reminder group"
    private var counter = 0

    override fun onReceive(context: Context, intent: Intent) {

        val bundle = intent.getBundleExtra("bundle")


        var title = intent.getStringExtra("title").toString()
        var note = intent.getStringExtra("note").toString()
        println("title $title")
        println("note $note")
        if (note.isEmpty()){
            note = "No description"
        }

        var noteEntity:NoteEntity = bundle?.getSerializable("noteEntity") as NoteEntity
        println("noteEntity $noteEntity")


        val newIntent = Intent(context, MainActivity::class.java)
        newIntent.putExtra("title", title)
        newIntent.putExtra("note", note)
        newIntent.putExtra("noteEntity", bundle.getSerializable("noteEntity") as NoteEntity)// noteEntity)
        val pendingIntent = PendingIntent.getActivity(context, 0, newIntent, PendingIntent.FLAG_CANCEL_CURRENT)

        var builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(title)
            .setContentText(note)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager:NotificationManagerCompat = NotificationManagerCompat.from(context)

        notificationManager.notify(NOTIFICATION_ID, builder.build())




    }

}