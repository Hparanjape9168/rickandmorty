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

@BindingAdapter("bind:textVisible")
fun showVisibilityText(view: TextView, isVisible: Boolean){
    if(isVisible)
        view.visibility = View.VISIBLE
    else
        view.visibility = View.GONE
}

@BindingAdapter("bind:layoutVisible")
fun showVisibilityLayout(view: LinearLayout, isVisible: Boolean){
    if(isVisible)
        view.visibility = View.VISIBLE
    else
        view.visibility = View.GONE
}

