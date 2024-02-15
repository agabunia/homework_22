package com.example.homework_22.presentation.extension

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.homework_22.R

fun AppCompatImageView.loadImage(url: String, circleCrop: CircleCrop? = null) {
    val glideRequest = Glide.with(context)
        .load(url)
        .error(R.drawable.user_icon)

    if (circleCrop != null) {
        glideRequest.transform(circleCrop)
    }

    glideRequest.into(this)
}