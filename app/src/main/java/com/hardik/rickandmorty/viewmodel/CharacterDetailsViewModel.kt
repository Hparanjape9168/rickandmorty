package com.hardik.rickandmorty.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hardik.rickandmorty.model.CharacterDetailsModel
import com.hardik.rickandmorty.model.LocationModel
import com.hardik.rickandmorty.utils.APIService
import com.hardik.rickandmorty.utils.Constant.BASE_URL
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CharacterDetailsViewModel : ViewModel() {

    private val disposable = CompositeDisposable()
    private val apiService = APIService(BASE_URL)

    private val _characterDetails = MutableLiveData<CharacterDetailsModel>()
    val characterDetails : LiveData<CharacterDetailsModel> = _characterDetails

    private val _locationModel = MutableLiveData<LocationModel>()
    val locationModel : LiveData<LocationModel> = _locationModel

    fun callCharacterDetailsAPI(id: Int) {
    val data = apiService.getCharsDetails(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
        data.subscribe(object : Observer<CharacterDetailsModel>{
         override fun onComplete() {
           Log.d("Hardik onComplete","onComplete")
           if(!disposable.isDisposed)
               disposable.clear()
         }
         override fun onSubscribe(d: Disposable) {
             disposable.add(d)
         }

         override fun onNext(characterDetailsModel: CharacterDetailsModel) {
            _characterDetails.value = characterDetailsModel
             Log.d("Hardik","onNext${characterDetailsModel.id}")
         }

         override fun onError(e: Throwable) {
             Log.d("Hardik onError",e.toString())
         }
     })
    }

    fun callLocationAPI(id: Int){
        val data = apiService.getLocation(id)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { locationModel ->
                _locationModel.value = locationModel
            }
        disposable.add(data)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}