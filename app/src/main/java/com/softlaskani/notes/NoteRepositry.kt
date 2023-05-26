package com.softlaskani.notes

import androidx.lifecycle.LiveData
import com.softlaskani.notes.room.Note
import com.softlaskani.notes.room.NoteDao

class NoteRepositry( private val dao: NoteDao) {

    fun getAllnote() : LiveData<List<Note>>{
        return dao.getAllNote()
    }

    suspend fun insert(note: Note){
        dao.insert(note)
    }

    suspend fun update(note: Note){
        dao.update(note)
    }

    suspend fun delete(note: Note){
        dao.delete(note)
    }

}