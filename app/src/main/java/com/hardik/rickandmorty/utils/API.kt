package com.hardik.rickandmorty.utils

import com.hardik.rickandmorty.model.CharacterDetailsModel
import com.hardik.rickandmorty.model.CharacterListModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface API {

    @GET("character/")
    fun getCharsList(): Observable<CharacterListModel>

    @GET("character/{id}")
    fun getCharsDetails(@Path("id") int: Int) : Observable<CharacterDetailsModel>
}