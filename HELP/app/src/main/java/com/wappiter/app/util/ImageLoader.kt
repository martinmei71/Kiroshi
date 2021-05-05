package com.wappiter.app.util

import android.widget.ImageView
import com.bumptech.glide.Glide


class ImageLoader {

    //

    companion object {
        fun showImage(imageView: ImageView, url: String?, placeholder: Int = android.R.drawable.gallery_thumb) {

            if (imageView == null || url == null) {
                throw NullPointerException("Algo es nulo!!!")
            }

            Glide
                    .with(imageView.context)
                    .load(url)
                    .fitCenter()
                    .error(placeholder)
                    .into(imageView)
        }
    }
}