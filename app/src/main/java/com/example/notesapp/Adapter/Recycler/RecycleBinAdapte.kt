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
import com.example.notesapp.databinding.ListItemRecycelbinBinding

class RecycleBinAdapte(
    private val context: Context,
    private val dao: NoteDao
) : RecyclerView.Adapter<RecycleBinAdapte.RecycleViewHolder>() {
     private val allData = dao.getNoteforRecycler(DBHelper.TRUE_STATE)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecycleViewHolder(
            ListItemRecycelbinBinding.inflate(
                LayoutInflater.from(context),
                parent, false
            )
        )
    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        holder.setData(allData[position])
    }
    override fun getItemCount(): Int = allData.size
    inner class RecycleViewHolder(
        private val binding: ListItemRecycelbinBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun setData(data: RecyclerNoteModel) {
            binding.txtTitleNote.text = data.title

            binding.imgDeleteNote.setOnClickListener {
                AlertDialog.Builder(context)
                    .setTitle("حذف یادداشت")
                    .setMessage("آیا میخواهید یاداشت برای همیشه حذف شود؟")
                    .setNegativeButton("بله") { _, _ ->
                        val result = dao.deleteNote(data.id)
                        if (result) {
                            toast("یادداشت حذف شد!")
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
            binding.imgRestoreNote.setOnClickListener {
                AlertDialog.Builder(context)
                    .setTitle("بازیابی یادداشت")
                    .setMessage("آیا میخواهید یاداشت بازگردانی شود؟")
                    .setNegativeButton("بله") { _, _ ->
                        val result = dao.editeNote(data.id, DBHelper.FALSE_STATE)
                        if (result) {
                            toast("یادداشت بازگردانی شد!")
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
        }
    }
    private fun toast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

}