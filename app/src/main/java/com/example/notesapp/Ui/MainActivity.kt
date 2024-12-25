package com.example.notesapp.Ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.Adapter.Recycler.NoteAdapter
import com.example.notesapp.data.local.db.DBHelper
import com.example.notesapp.data.local.db.Dao.NoteDao
import com.example.notesapp.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = DBHelper.getDatabase(this)
        binding.imgAddNote.setOnClickListener() {
            val intent = Intent(this, Addnoteactivity::class.java)
            intent.putExtra("new Note",true)
            startActivity(intent)
        }
        binding.txtRecycleBin.setOnClickListener(){
            val intent =Intent(this,RecycelBinActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onStart() {
        super.onStart()
        val data = dao.getNoteforRecycler(DBHelper.FALSE_STATE)
        adapter.changdata(data)

    }
    private fun initRecycler(){
        dao = NoteDao(DBHelper(this))

        adapter = NoteAdapter(this,dao )
        binding.RecyclerNote.layoutManager =
            LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        binding.RecyclerNote.adapter = adapter

    }


}
/*class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dao:NotesDao
    private lateinit var adapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
        binding.imgAddNote.setOnClickListener() {
            val intent = Intent(this, Addnoteactivity::class.java)
            intent.putExtra("new Note",true)
            startActivity(intent)
        }
        binding.txtRecycleBin.setOnClickListener(){
            val intent =Intent(this,RecycelBinActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onStart() {
        super.onStart()
        val data = dao.getNoteforRecycler(DBHelper.FALSE_STATE)
        adapter.changdata(data)

    }
     private fun initRecycler(){
        dao = NoteDao(DBHelper(this))

        adapter = NoteAdapter(this,dao )
        binding.RecyclerNote.layoutManager =
            LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        binding.RecyclerNote.adapter = adapter

    }


}*/