package com.hardik.rickandmorty.model

import com.google.gson.annotations.SerializedName

data class LocationModel(
    @SerializedName("id")
    val id : Int,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("type")
    val type: String = "",

    @SerializedName("dimension")
    var dimension: String ="",

    @SerializedName("residents")
    var residents : ArrayList<String>,

    @SerializedName("url")
    val url: String = ""
)