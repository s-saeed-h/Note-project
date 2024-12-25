package com.example.notesapp.data.local.db.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notesapp.data.Model.DBNotemodel
import com.example.notesapp.data.local.db.DBHelper
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Insert
    fun insertNotes(vararg notes: DBNotemodel)

    @get:Query("SELECT * FROM ${DBHelper.NOTE_TABLE}")
    val getNotes: Flow<List<DBNotemodel>>

    @Update
    fun updateNotes(vararg notes:DBNotemodel):Int

    @Delete
    fun deleteNotes(notes:DBNotemodel)

    @Query("DELETE FROM ${DBHelper.NOTE_TABLE}")
    fun DeleteAlldata()
}