package com.example.notes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.setViewTreeOnBackPressedDispatcherOwner
import androidx.lifecycle.ViewModelProvider
import com.example.notes.R
import com.example.notes.databinding.ActivityCreateNoteBinding
import com.example.notes.room.MainViewModel
import com.example.notes.room.Note
import java.text.SimpleDateFormat
import java.util.Date

class CreateNote : AppCompatActivity() {

    lateinit var mainViewModel : MainViewModel
    lateinit var binding : ActivityCreateNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(MainViewModel::class.java)

        setSupportActionBar(binding.toolbar2)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val onbackpress = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                savedata()
            }

        }

        onBackPressedDispatcher.addCallback(onbackpress)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){

            R.id.saveitem -> savedata()

            else -> { super.onOptionsItemSelected(item)
            }
        }


    }

    private fun savedata(): Boolean  {

        val titleee = binding.txttitleCreate.text.toString()
        val dis = binding.txtcontantCreate.text.toString()
        var sdf = SimpleDateFormat("dd/MM/YYYY   HH:mm")
        var date = sdf.format(Date())
        val note1 = Note(id = 0,title = titleee, content = null ,date)
        val note2 = Note(id = 0,title = null, content = dis ,date)
        val note3 = Note(id = 0,title = titleee, content = dis ,date)

        if (titleee.isEmpty() && dis.isNotEmpty()){
            mainViewModel.insert(note2)
            Toast.makeText(this, "save", Toast.LENGTH_SHORT).show()
        }

        else if (dis.isEmpty() && titleee.isNotEmpty()){
            mainViewModel.insert(note1)
            Toast.makeText(this, "save", Toast.LENGTH_SHORT).show()
        }

        else if (titleee.isEmpty() && dis.isEmpty()) {
//
            Toast.makeText(this, "note not create", Toast.LENGTH_SHORT).show()

        }
        else {
            mainViewModel.insert(note3)
            Toast.makeText(this@CreateNote, "save", Toast.LENGTH_SHORT).show()


        }
        finish()


        return true

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_item,menu)
        return super.onCreateOptionsMenu(menu)
    }


}
