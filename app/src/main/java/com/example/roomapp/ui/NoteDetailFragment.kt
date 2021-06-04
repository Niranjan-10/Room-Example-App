package com.example.roomapp.ui

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.roomapp.R
import com.example.roomapp.data.Note
import com.example.roomapp.database.NoteDatabaseBuilder
import com.example.roomapp.repository.DatabaseHelperImpl
import com.example.roomapp.util.ViewModelFactory
import kotlinx.android.synthetic.main.note_detail_fragment.*

class NoteDetailFragment : Fragment(R.layout.note_detail_fragment) {
    private val args by navArgs<NoteDetailFragmentArgs>()
    private lateinit var viewModel: DatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.note_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                DatabaseHelperImpl(
                    NoteDatabaseBuilder.getInstance(
                        activity?.applicationContext!!
                    )
                )
            )
        ).get(DatabaseViewModel::class.java)

        var title: String = args.note.title
        var description: String = args.note.description

        edit_title.text = Editable.Factory.getInstance().newEditable(title)
        edit_description.text = Editable.Factory.getInstance().newEditable(description)

        edit_button.setOnClickListener {


            val title = edit_title.text.toString()
            val description = edit_description.text.toString()

            val note = Note(title = title, description = description, priority = 0)
            viewModel.update(note)

            edit_title.clearFocus()
            edit_description.clearFocus()
            hideKeyboard(it)
            Toast.makeText(activity?.applicationContext,"Success",Toast.LENGTH_SHORT).show()

        }


    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE)
                as InputMethodManager

        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}