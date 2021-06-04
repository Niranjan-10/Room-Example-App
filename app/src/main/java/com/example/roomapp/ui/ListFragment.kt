package com.example.roomapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomapp.R
import com.example.roomapp.database.NoteDatabaseBuilder
import com.example.roomapp.repository.DatabaseHelperImpl
import kotlinx.android.synthetic.main.list_fragment.*
import com.example.roomapp.util.ViewModelFactory

class ListFragment : Fragment(R.layout.list_fragment) {

    private lateinit var viewModel: DatabaseViewModel
    private val listAdapter = ListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.list_fragment, container, false)
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
        viewModel.fetch()

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        observeData()

        button_add_note.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToAddNoteFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }

    private fun observeData() {

        viewModel.getNotes().observe(viewLifecycleOwner, Observer{ notes ->

            notes.let {
                recycler_view.visibility = View.VISIBLE

                listAdapter.setNotes(notes)
            }

        })


    }


}

