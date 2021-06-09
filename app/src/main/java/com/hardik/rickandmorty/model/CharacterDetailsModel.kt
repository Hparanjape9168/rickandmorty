package com.hardik.rickandmorty.model

import com.google.gson.annotations.SerializedName

data class CharacterDetailsModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name : String?,

    @SerializedName("status")
    val status : String?,

    @SerializedName("gender")
    val gender : String?,

    @SerializedName("image")
    val image : String?,

    @SerializedName("location")
    val location : LocationModel,

    @SerializedName("episode")
    val episode : ArrayList<String>

)