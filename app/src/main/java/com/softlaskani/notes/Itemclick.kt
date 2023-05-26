package com.example.notes

import com.example.notes.room.Note

interface Itemclick {

    fun onitemclick(note : Note)
    fun onitemdelete(note : Note)
}