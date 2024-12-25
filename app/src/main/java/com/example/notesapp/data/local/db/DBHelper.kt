package com.example.notesapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.data.Model.DBNotemodel

@Database(entities = [DBNotemodel::class],
    version = DBHelper.DB_VERSION)
abstract class DBHelper:RoomDatabase(){
    companion object {
        private const val DB_NAME = "NOTES"
        const val DB_VERSION = 1
        const val NOTE_TABLE = "Note"
        private var INSTANCE:DBHelper?=null
        fun getDatabase(context: Context):DBHelper{
            if (INSTANCE == null)
                INSTANCE = Room.databaseBuilder(
                    context,DBHelper::class.java,DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
            return INSTANCE!!
        }
    }
}











/*class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private const val DB_NAME = "NOTES.db"
        private const val DB_VERSION = 1
        const val NOTE_TABLE = "Note"
        const val NOTE_ID = "id"
        const val NOTE_TITLE = "title"
        const val NOTE_DETAIL = "detail"
        const val NOTE_DELETE_STATE = "deletstat"
        const val NOTE_DATE = "date"
        const val TRUE_STATE = "1"
        const val FALSE_STATE = "0"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE IF NOT EXISTS $NOTE_TABLE(" +
                    "$NOTE_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$NOTE_TITLE VARCHAR(255)," +
                    "$NOTE_DETAIL TEXT," +
                    "$NOTE_DELETE_STATE VARCHAR(1)," +
                    "$NOTE_DATE VARCHAR(255))"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
}*/