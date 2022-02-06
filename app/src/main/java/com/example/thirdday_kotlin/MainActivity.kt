package com.example.thirdday_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thirdday_kotlin.data.Note
import com.example.thirdday_kotlin.data.NoteDatabase
import com.example.thirdday_kotlin.repo.NoteRepository
import com.example.thirdday_kotlin.view.INoteAdapter
import com.example.thirdday_kotlin.view.NotesAdapter
import com.example.thirdday_kotlin.viewmodels.NoteVMFactory
import com.example.thirdday_kotlin.viewmodels.NoteViewModel
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity(), INoteAdapter {

    private lateinit var noteText: TextInputEditText
    private lateinit var noteTextdesc: TextInputEditText
    private lateinit var notesViewModel: NoteViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteText = findViewById(R.id.fulltext)
        noteTextdesc = findViewById(R.id.full_desc)
        recyclerView = findViewById(R.id.recycle)

        recyclerView.layoutManager= LinearLayoutManager(this)
        val adapter = NotesAdapter(this,this)
        recyclerView.adapter = adapter


        val dao = NoteDatabase.getDatabase(applicationContext).getNoteDao()
        val repository = NoteRepository(dao)
        notesViewModel = ViewModelProvider(this, NoteVMFactory(repository)).get(NoteViewModel::class.java)

        notesViewModel.allNotes.observe(this, Observer {
            adapter.updateNotes(it)
        })
    }

    fun submitdata(view: android.view.View) {
        val noteText = noteText.text.toString()
        val noteTextdesc = noteTextdesc.text.toString()
        if ((noteText. isNotEmpty()) && (noteTextdesc.isNotEmpty())){
            notesViewModel.insertNote(Note(noteText,noteTextdesc))
            Toast.makeText(this,"Data Inserted" , Toast.LENGTH_SHORT).show()
            
        }

    }
    override fun onItemClicked(note: Note) {
        notesViewModel.deleteNotes(note)
        Toast.makeText(this,"Data Deleted" , Toast.LENGTH_SHORT).show()

    }
}