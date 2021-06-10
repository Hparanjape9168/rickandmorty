package com.hardik.rickandmorty.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hardik.rickandmorty.model.EpisodeModel
import com.hardik.rickandmorty.utils.APIService
import com.hardik.rickandmorty.utils.Constant
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class EpisodeViewModel : ViewModel() {

    private val disposable = CompositeDisposable()
    private val apiService = APIService(Constant.BASE_URL)

    private val _episodeList = MutableLiveData<List<EpisodeModel>>()
    val episodeList : LiveData<List<EpisodeModel>> = _episodeList

    fun refreshData(url : String) {
        Log.d(Constant.TAG,"url")
        val data = apiService.getEpisodes(url)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<EpisodeModel>>(){
                override fun onSuccess(list: List<EpisodeModel>) {
                   _episodeList.value = list
                }
                override fun onError(e: Throwable) {
                  Log.d(Constant.TAG ,"onError:"+e.toString())
                }
            })
        disposable.add(data)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}