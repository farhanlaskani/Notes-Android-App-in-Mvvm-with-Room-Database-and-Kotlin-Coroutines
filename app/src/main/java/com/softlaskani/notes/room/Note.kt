package com.softlaskani.notes.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String? = "",
    val content: String? = "",
    val date: String
)
