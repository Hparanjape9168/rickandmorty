package com.hardik.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hardik.rickandmorty.model.CharacterModel
import com.hardik.rickandmorty.utils.APIService
import com.hardik.rickandmorty.utils.Constant
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CharacterListViewModel : ViewModel() {

    private val disposable = CompositeDisposable()
    private val apiService = APIService(Constant.CHARACTER_LIST_BASE_URL)
    private val _characterList = MutableLiveData<List<CharacterModel>>()
    val characterList : LiveData<List<CharacterModel>> = _characterList

    fun refreshData(){
        val data = apiService.getCharsListData()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
               _characterList.value = list.charsList
            }
        disposable.add(data)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}