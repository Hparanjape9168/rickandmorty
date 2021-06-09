package com.hardik.rickandmorty.model

import com.google.gson.annotations.SerializedName

data class CharacterListModel(
    @SerializedName("results")
    val charsList : List<CharacterModel>
)