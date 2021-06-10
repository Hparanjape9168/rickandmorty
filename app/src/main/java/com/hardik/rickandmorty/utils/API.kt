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

    /**
     * Get All Characters
     */
    @GET("character/")
    fun getCharsList(): Observable<CharacterListModel>

    /**
     * Get Specific Id Character Request
     */
    @GET("character/{id}")
    fun getCharsDetails(@Path("id") int: Int) : Observable<CharacterDetailsModel>

    /**
     * Get Specific location id
     */
    @GET("location/{id}")
    fun getLocation(@Path("id") int: Int) : Observable<LocationModel>

    /**
     * Get all episodes list for specific characters
     */
    @GET("episode/{id}")
    fun getEpisodes(@Path("id") url : String) : Single<List<EpisodeModel>>
}