package com.hardik.rickandmorty.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hardik.rickandmorty.R
import com.hardik.rickandmorty.viewmodel.CharacterListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var characterListViewModel : CharacterListViewModel
    private val charsListAdapter =  CharacterListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        characterListViewModel  = ViewModelProvider(this).get(CharacterListViewModel::class.java)
        characterListViewModel.refreshData()
        charsListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = charsListAdapter
        }

        updateData()
    }

    private fun updateData(){
        characterListViewModel.characterList.observe(this, Observer { list ->
            list?.let {
                charsListAdapter.updateList(list)
            }
        })
    }
}