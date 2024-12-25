package com.example.notesapp.Adapter.Recycler

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.Ui.Addnoteactivity
import com.example.notesapp.data.Model.RecyclerNoteModel
import com.example.notesapp.data.local.db.DBHelper
import com.example.notesapp.data.local.db.Dao.NoteDao
import com.example.notesapp.databinding.ListItemNoteBinding

class NoteAdapter(
    private val context: Context,
    private val dao: NotesDao
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var allData: ArrayList<RecyclerNoteModel>
    init {
        allData = dao.getNoteforRecycler(DBHelper.)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NoteViewHolder(
            ListItemNoteBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.setData(allData[position])
    }
    override fun getItemCount(): Int = allData.size
    inner class NoteViewHolder(
        private val binding: ListItemNoteBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun setData(data: RecyclerNoteModel) {
            binding.txtTitleNote.text = data.title
            binding.imgDeleteNote.setOnClickListener {
                AlertDialog.Builder(context)
                    .setTitle("حذف یادداشت")
                    .setMessage("آیا میخواهید یاداشت به سطل زباله انتقال یابد؟")
                    .setNegativeButton("بله") { _, _ ->
                        val result = dao.editeNote(data.id, DBHelper.TRUE_STATE)
                        if (result) {
                            toast("یادداشت به سطل زباله منتقل شد!")
                            allData.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                        } else {
                            toast("عملیات دچار اشکال شده است")
                        }
                    }
                    .setPositiveButton("خیر") { _, _ -> }
                    .create()
                    .show()
            }
            binding.root.setOnClickListener() {
                val intent = Intent(context, Addnoteactivity::class.java)
                intent.putExtra("noteId", data.id)
                context.startActivity(intent)
            }
        }
    }
    fun changdata(data: ArrayList<RecyclerNoteModel>) {
        if (data.size > allData.size) {
            allData = data
            notifyItemInserted(allData.size)
        }
    }
    private fun toast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}