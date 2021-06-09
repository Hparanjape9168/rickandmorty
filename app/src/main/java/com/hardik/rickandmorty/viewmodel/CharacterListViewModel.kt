package com.hardik.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hardik.rickandmorty.model.CharacterModel

class CharacterListViewModel : ViewModel() {

    private val _characterList = MutableLiveData<List<CharacterModel>>()
    val characterList : LiveData<List<CharacterModel>> = _characterList

    fun refreshData(){

        val item1 = CharacterModel(1,"Cool","Relation","Animal","no")
        val item2 = CharacterModel(2,"Test","12","Human","no")
        val item3 = CharacterModel(3,"Best","famus","Animal","no")
        val item4 = CharacterModel(4,"Canada","unknow","Alien","no")
        _characterList.value = arrayListOf(item1,item2,item3,item4)
    }

}