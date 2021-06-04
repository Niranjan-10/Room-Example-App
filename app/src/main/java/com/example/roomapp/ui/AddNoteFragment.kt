package com.example.roomapp.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.roomapp.R
import com.example.roomapp.data.Note
import com.example.roomapp.database.NoteDatabaseBuilder
import com.example.roomapp.repository.DatabaseHelperImpl
import com.example.roomapp.util.ViewModelFactory
import kotlinx.android.synthetic.main.add_note_fragment.*

class AddNoteFragment : Fragment(R.layout.add_note_fragment) {
    private lateinit var viewModel: DatabaseViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_note_fragment, container, false)
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

        submit_button.setOnClickListener {
            var title = title_edittext.text
            var description = description_edittext.text
            var note: Note =
                Note(title = title.toString(), description = description.toString(), priority = 2)
//
            viewModel.add(note)
            title_edittext.text.clear()
            description_edittext.text.clear()
            title_edittext.clearFocus()
            description_edittext.clearFocus()
            hideKeyboard(it)
            Toast.makeText(activity?.applicationContext,"Noted added",Toast.LENGTH_SHORT).show()
        }


    }


    private fun hideKeyboard(view: View) {
        val inputMethodManager = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE)
                as InputMethodManager

        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}