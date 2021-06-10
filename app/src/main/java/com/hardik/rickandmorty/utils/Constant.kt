package com.hardik.rickandmorty.utils

import androidx.lifecycle.MutableLiveData

object Constant {

    const val BASE_URL = "https://rickandmortyapi.com/api/"
    const val CHARACTER_DETAILS_BASE_URL = "https://rickandmortyapi.com/api/character/"
    const val INTENT_LAUNCH_CHARACTER_DETAILS = "com.hardik.rickandmorty.view.CharacterDetailActivity"
    const val EXTRA_KEY = "key"
    const val NAVIGATE_TAG = "NAVIGATE_TAG"
    var layoutVisible = MutableLiveData(false)
}