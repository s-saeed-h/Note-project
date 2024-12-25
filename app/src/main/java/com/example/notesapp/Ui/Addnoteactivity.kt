package com.example.notesapp.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.example.notesapp.Utils.PersianDate
import com.example.notesapp.databinding.ActivityAddnoteactivityBinding
import com.example.notesapp.data.local.db.DBHelper
import com.example.notesapp.data.local.db.Dao.NoteDao
import com.example.notesapp.data.Model.DBNotemodel

class Addnoteactivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddnoteactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddnoteactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val type =intent.getBooleanExtra("new Note",false)
       val id = intent.getIntExtra("noteId",0)
        val dao = NoteDao(DBHelper(this))
        if(type){
            binding.txtDate.text = getDate()
        }
        else{
            val note = dao.getnotebyId(id)
            val edit =Editable.Factory()
            binding.edtTitleNote.text = edit.newEditable(note.title)
            binding.edtDetailNote.text = edit.newEditable(note.detail)
            binding.txtDate.text = note.date
        }
        binding.btnSave.setOnClickListener() {
            val title = binding.edtTitleNote.text.toString()
            val detail = binding.edtDetailNote.text.toString()
            if (title.isNotEmpty()) {
                val note = DBNotemodel(0, title, detail, DBHelper.FALSE_STATE, getDate())
                    val result = if(type)
                            dao.saveNote(note)
                else
                    dao.editeNote(id,note)



                if (result){
                  toast("یادداشت با موفقیت ثبت شد")
                    finish()
                }else{
                   toast( "خطا در ثبت یادداشت")
                }
            } else {
                toast("عنوان را وارد کنید")
            }


        }
        binding.btnCancel.setOnClickListener(){
            finish()
        }
    }

    private fun getDate(): String {
       val date = PersianDate()
       val currentDate = "${date.year}/${date.month}/${date.day}"
       val currentTime = "${date.hour}:${date.min}"
       return "$currentDate | $currentTime"

    }
    private fun toast(text:String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}