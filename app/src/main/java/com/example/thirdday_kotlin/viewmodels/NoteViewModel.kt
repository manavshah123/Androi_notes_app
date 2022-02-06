package com.example.thirdday_kotlin.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thirdday_kotlin.data.Note
import com.example.thirdday_kotlin.repo.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {
    val allNotes: LiveData<List<Note>>
        get() = repository.allNotes

    fun insertNote( note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(note)
        }
    }

    fun deleteNotes(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(note)
        }
    }
}