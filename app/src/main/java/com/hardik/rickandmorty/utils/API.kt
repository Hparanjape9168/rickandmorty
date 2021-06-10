package com.hardik.rickandmorty.utils

import com.hardik.rickandmorty.model.CharacterDetailsModel
import com.hardik.rickandmorty.model.CharacterListModel
import com.hardik.rickandmorty.model.EpisodeModel
import com.hardik.rickandmorty.model.LocationModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface API {

    @GET("character/")
    fun getCharsList(): Observable<CharacterListModel>

    @GET("character/{id}")
    fun getCharsDetails(@Path("id") int: Int) : Observable<CharacterDetailsModel>

    @GET("location/{id}")
    fun getLocation(@Path("id") int: Int) : Observable<LocationModel>

    @GET("episode/{id}")
    fun getEpisodes(@Path("id") url : String) : Single<List<EpisodeModel>>
}