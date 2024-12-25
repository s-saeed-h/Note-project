package com.example.notesapp.data.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notesapp.data.local.db.DBHelper

@Entity(tableName = DBHelper.NOTE_TABLE)
data class DBNotemodel(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo val title: String,
    @ColumnInfo val detail:String,
    @ColumnInfo val deletestate:String,
    @ColumnInfo val date:String,
    @ColumnInfo val truestate:Int = 1,
    @ColumnInfo val falsestate:Int =0
)






/*data class DBNotemodel(
    var id:Int,
    var title:String,
    var detail:String,
    var deletestate:String,
    var date:String
)*/