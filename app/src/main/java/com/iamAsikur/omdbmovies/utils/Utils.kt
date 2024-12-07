package com.iamAsikur.omdbmovies.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object Utils {

    fun showImage(context: Context, url: String, imageView: ImageView) {
        Glide
            .with(context)
            .load(url)
            .into(imageView)
    }
}