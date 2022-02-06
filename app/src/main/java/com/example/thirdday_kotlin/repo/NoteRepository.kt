package com.example.thirdday_kotlin.repo

import androidx.lifecycle.LiveData
import androidx.room.Delete
import com.example.thirdday_kotlin.data.Note
import com.example.thirdday_kotlin.data.NoteDao


class NoteRepository(private  val noteDao: NoteDao) {

    val allNotes : LiveData<List<Note>> = noteDao.getAllnotes()

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }

    suspend fun delete(note: Note){
        noteDao.delete(note)
    }

}