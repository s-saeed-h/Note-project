/*package com.example.notesapp.data.local.db.Dao

import android.content.ContentValues
import android.database.Cursor
import android.util.Log
import com.example.notesapp.data.Model.RecyclerNoteModel
import com.example.notesapp.data.local.db.DBHelper
import com.example.notesapp.data.Model.DBNotemodel

class NoteDao(
     //private var db: DBHelper
) {
    private lateinit var cursor: Cursor
    private val contentValues=ContentValues()
    fun saveNote(note:DBNotemodel):Boolean{
        val database = db.writableDatabase
        setcontentvalues(note)
        val result = database.insert(DBHelper.NOTE_TABLE,null,contentValues)
        database.close()
        return result > 0
    }
    private fun setcontentvalues(note:DBNotemodel) {
        contentValues.clear()
        contentValues.put(DBHelper.NOTE_TITLE,note.title)
        contentValues.put(DBHelper.NOTE_DETAIL,note.detail)
        contentValues.put(DBHelper.NOTE_DELETE_STATE,note.deletestate)
        contentValues.put(DBHelper.NOTE_DATE,note.date)

    }
    fun editeNote(id:Int,state:String):Boolean{
        val database=db.writableDatabase
        contentValues.clear()
        contentValues.put(DBHelper.NOTE_DELETE_STATE,state)
        val result = database.update(DBHelper.NOTE_TABLE,contentValues,
            "${DBHelper.NOTE_ID} = ? ", arrayOf(id.toString()))
        database.close()
        return result > 0
    }
    fun editeNote(id:Int,note:DBNotemodel):Boolean{
        val database = db.writableDatabase
        setcontentvalues(note)
        val result = database.update(
            DBHelper.NOTE_TABLE,
            contentValues, "${DBHelper.NOTE_ID} = ?",
            arrayOf(id.toString()))
        database.close()
        return result > 0
    }
    fun getnotebyId(id:Int):DBNotemodel{
        val database = db.readableDatabase
        val query = "SELECT * FROM ${DBHelper.NOTE_TABLE} WHERE ${DBHelper.NOTE_ID} = ?"
        cursor = database.rawQuery(query, arrayOf(id.toString()))
        val data = getdata()
        cursor.close()
        database.close()
        return data
    }
    private fun getdata(): DBNotemodel {
        val data = DBNotemodel(0,"","","","")
        try {
            if(cursor.moveToFirst()){
                data.id = cursor.getInt(getindex(DBHelper.NOTE_ID))
                data.title = cursor.getString(getindex(DBHelper.NOTE_TITLE))
                data.detail = cursor.getString(getindex(DBHelper.NOTE_DETAIL))
                data.deletestate = cursor.getString(getindex(DBHelper.NOTE_DELETE_STATE))
                data.date = cursor.getString(getindex(DBHelper.NOTE_DATE))
            }
        }catch (e:Exception){
            Log.e("error",e.message.toString())
        }
        return data

    }
    fun getNoteforRecycler(value:String):ArrayList<RecyclerNoteModel>{
        val database = db.readableDatabase
        val query = "SELECT ${DBHelper.NOTE_ID},${DBHelper.NOTE_TITLE}" +
                " FROM ${DBHelper.NOTE_TABLE}" +
                " WHERE ${DBHelper.NOTE_DELETE_STATE} = ?"
         cursor =database.rawQuery(query, arrayOf(value))
        val data = getDataforRecycler()
        cursor.close()
        database.close()
        return data
    }
    private fun getDataforRecycler(): ArrayList<RecyclerNoteModel> {
        val data = ArrayList<RecyclerNoteModel>()
        try{
            if(cursor.moveToFirst()){
                do{
                    val id = cursor.getInt(getindex(DBHelper.NOTE_ID))
                    val title = cursor.getString(getindex(DBHelper.NOTE_TITLE))
                    data.add(RecyclerNoteModel(id, title))
                }while(cursor.moveToNext())
            }
        }catch (e:Exception){
            Log.e("error",e.message.toString())
        }
        return data
    }
    private fun getindex(name:String) = cursor.getColumnIndex(name)
    fun deleteNote(id:Int):Boolean{
        val database=db.writableDatabase
        val result = database.delete(DBHelper.NOTE_TABLE,
            "${DBHelper.NOTE_ID} = ? ", arrayOf(id.toString()))
        database.close()
        return result > 0
    }
}*/