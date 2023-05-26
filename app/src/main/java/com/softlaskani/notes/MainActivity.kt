package com.softlaskani.notes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.softlaskani.notes.room.MainViewModel
import com.softlaskani.notes.room.Note
import com.softlaskani.notes.ui.CreateNote
import com.softlaskani.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Itemclick {

    lateinit var adapter: RvAdapter

   lateinit var mainViewModel : MainViewModel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            MainViewModel::class.java)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        val adapter = RvAdapter(this)

        binding.recyclerview.adapter = adapter

        mainViewModel.allnote.observe(this, Observer {list ->
           list?.let {
                adapter.updatelist(it)
            }
        })


        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, CreateNote::class.java))
        }


    }

    override fun onitemclick(note: Note) {
        val intent = Intent(this@MainActivity, UpdateFragment::class.java)
        intent.putExtra("title",note.title)
        intent.putExtra("dis",note.content)
        intent.putExtra("id",note.id)
        startActivity(intent)
        finish()

    }

    override fun onitemdelete(note: Note) {
        mainViewModel.delete(note)

    }
}