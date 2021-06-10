package com.hardik.rickandmorty.utils

import androidx.lifecycle.MutableLiveData

object Constant {

    const val BASE_URL = "https://rickandmortyapi.com/api/"
    const val INTENT_LAUNCH_CHARACTER_DETAILS = "com.hardik.rickandmorty.view.CharacterDetailActivity"
    const val EXTRA_KEY = "key"
    const val NAVIGATE_TAG = "NAVIGATE_TAG"
    const val TAG = "rickandmorty"
    var layoutVisible = MutableLiveData(false)
}