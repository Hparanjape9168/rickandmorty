package com.hardik.rickandmorty.utils

import com.hardik.rickandmorty.model.CharacterDetailsModel
import com.hardik.rickandmorty.model.CharacterListModel
import com.hardik.rickandmorty.utils.Constant.CHARACTER_LIST_BASE_URL
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIService(url: String) {

    private val api = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(API::class.java)

    fun getCharsListData() : Observable<CharacterListModel>{
        return api.getCharsList()
    }

    fun getCharsDetails(id: Int) : Observable<CharacterDetailsModel>{
        return  api.getCharsDetails(id)
    }
}