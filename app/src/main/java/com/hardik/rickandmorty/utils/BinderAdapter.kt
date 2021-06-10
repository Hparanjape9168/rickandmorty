package com.hardik.rickandmorty.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hardik.rickandmorty.R

fun getProgressDrawable(context: Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(url:String?, progressDrawable: CircularProgressDrawable){
    val option = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher)

    Glide.with(context)
        .setDefaultRequestOptions(option)
        .load(url)
        .into(this)
}

@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, url: String?){
    Log.d("Hardik",""+url)
    view.loadImage(url, getProgressDrawable(view.context))
}

@BindingAdapter("bind:textEpisode")
fun textViewEpisode(view: TextView, number : Int){
  view.text = "Episode Number: "+number
}

@BindingAdapter("bind:textEpisodeName")
fun textViewEpisodeName(view: TextView, name:String){
    view.text = "Name: "+name
}

@BindingAdapter("bind:textEpisodeRelease")
fun textViewEpisodeRelease(view: TextView, release:String){
    view.text = "Release Date: "+release
}

@BindingAdapter("bind:textEpisodeNumber")
fun textViewEpisodeNumber(view: TextView, number:String){
    view.text = "Series Number: "+number
}



