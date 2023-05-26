package com.example.notes

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.notes.databinding.FragmentUpdateBinding
import com.example.notes.room.MainViewModel
import com.example.notes.room.Note
import java.text.SimpleDateFormat
import java.util.Date

class UpdateFragment : AppCompatActivity() {
   lateinit var mainViewModel : MainViewModel

    lateinit var binding: FragmentUpdateBinding
    var noteid = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(MainViewModel::class.java)
        val toolbar = binding.toolbar
       setSupportActionBar(toolbar)


       val title = intent.getStringExtra("title")
       val des = intent.getStringExtra("dis")
        noteid = intent.getIntExtra("id", -1)
        binding.txttitleUpdate.setText(title)
        binding.txtcontantUpdate.setText(des)


        val backpress = object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
          updatebackpress()
            }

        }

        onBackPressedDispatcher.addCallback(backpress)


    }

    private fun updatebackpress() {
        val newtitle = binding.txttitleUpdate.text.toString()
        val newdes = binding.txtcontantUpdate.text.toString()
        var sdf = SimpleDateFormat("dd/MM/YYYY   HH:mm")
        var date = sdf.format(Date())


        val updatenote = Note(noteid,newtitle,newdes,date)
        val updatenote2 = Note(noteid,null,newdes,date)
        updatenote.id = noteid


        if (newtitle.isEmpty() && newdes.isNotEmpty() ){
            mainViewModel.update(updatenote2)
            Toast.makeText(this@UpdateFragment, "save", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@UpdateFragment,MainActivity::class.java))
            this.finish()


        } else if (newtitle.isNotEmpty() && newdes.isEmpty()){
            mainViewModel.update(updatenote)
            Toast.makeText(this@UpdateFragment, "save", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@UpdateFragment,MainActivity::class.java))
            this.finish()
        }
        else if (newtitle.isNotEmpty() && newdes.isNotEmpty()) {

            mainViewModel.update(updatenote)
            Toast.makeText(this@UpdateFragment, "save", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@UpdateFragment, MainActivity::class.java))
            this.finish()
        } else {
            Toast.makeText(this@UpdateFragment, "not save", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@UpdateFragment, MainActivity::class.java))
            this.finish()

        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){

            R.id.saveitem -> { updatebackpress()
                return true
            }

            else -> { super.onOptionsItemSelected(item)
            }
        }


    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_item,menu)
        return super.onCreateOptionsMenu(menu)
    }
}