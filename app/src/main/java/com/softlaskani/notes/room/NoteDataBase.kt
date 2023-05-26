package com.softlaskani.notes.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDataBase : RoomDatabase() {

    abstract fun getDao() : NoteDao

    companion object{

        @Volatile

        private var INSTACE : NoteDataBase?= null

        fun getdatabase(context: Context) : NoteDataBase {
           if (INSTACE ==null) {
               synchronized(this){
                   INSTACE = Room.databaseBuilder(context , NoteDataBase::class.java,"note_data").build()
               }

           }
            return INSTACE!!
        }

    }
}