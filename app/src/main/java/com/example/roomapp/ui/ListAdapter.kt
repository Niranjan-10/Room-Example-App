package com.example.roomapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.data.Note
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter(private var notes: ArrayList<Note>) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var view = layoutInflater.inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        holder.view.setOnClickListener {
            val action =
                ListFragmentDirections.actionListFragmentToNoteDetailFragment(note = notes[position])
            Navigation.findNavController(it).navigate(action)
        }
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.title
        private val description = view.description
        fun bind(note: Note) {
            title.text = note.title
            description.text = note.description
        }
    }

    fun setNotes(data: List<Note>) {
        notes.clear()
        notes.addAll(data)
        notifyDataSetChanged()
    }


}