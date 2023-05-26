package com.softlaskani.notes.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.softlaskani.notes.NoteRepositry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel( application: Application) : AndroidViewModel(application) {

    var repositry : NoteRepositry
    var allnote : LiveData<List<Note>>
    init {
        val dao = NoteDataBase.getdatabase(application).getDao()
        repositry = NoteRepositry(dao)
        allnote = repositry.getAllnote()
    }


    fun insert(note: Note) = GlobalScope.launch(Dispatchers.IO) {
        repositry.insert(note)

    }

    fun update(note: Note) = GlobalScope.launch(Dispatchers.IO) {
        repositry.update(note)

    }

    fun delete(note: Note) = GlobalScope.launch(Dispatchers.IO) {
        repositry.delete(note)

    }
}