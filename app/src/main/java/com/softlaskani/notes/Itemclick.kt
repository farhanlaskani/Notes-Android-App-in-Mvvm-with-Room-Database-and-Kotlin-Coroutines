package com.softlaskani.notes

import com.softlaskani.notes.room.Note

interface Itemclick {

    fun onitemclick(note : Note)
    fun onitemdelete(note : Note)
}