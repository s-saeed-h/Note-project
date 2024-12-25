package com.example.notesapp.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.Adapter.Recycler.NoteAdapter
import com.example.notesapp.Adapter.Recycler.RecycleBinAdapte
import com.example.notesapp.R
import com.example.notesapp.data.local.db.DBHelper
import com.example.notesapp.data.local.db.Dao.NoteDao
import com.example.notesapp.databinding.ActivityRecycelBinBinding

class RecycelBinActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRecycelBinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecycelBinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
    }
    private fun initRecycler(){
        val dao = NotesDao(DBHelper(this))

        val adapter = RecycleBinAdapte(this,dao)
        binding.RecyclerNote.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        binding.RecyclerNote.adapter = adapter

    }
}