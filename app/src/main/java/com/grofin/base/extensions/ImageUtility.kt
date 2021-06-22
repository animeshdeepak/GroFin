package com.grofin.base.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?, placeholderImage: Int, errorImage: Int) {
    try {
        Glide.with(this)
            .load(url)
            .placeholder(placeholderImage)
            .error(errorImage)
            .fitCenter()
            .into(this)
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
    }
}