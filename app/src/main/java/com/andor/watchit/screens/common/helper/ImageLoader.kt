package com.andor.watchit.screens.common.helper

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.andor.watchit.R
import com.andor.watchit.core.Constants
import com.squareup.picasso.Picasso


class ImageLoader(private val context: Context) {

    val picasso = Picasso.get()

    fun loadImageInto(view: ImageView, uri: String?) {

        picasso
            .load("${Constants.BASE_IMAGE_URL}/${Constants.IMAGE_SIZE}/${uri}")
            .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_image_24px)!!)
            .error(ContextCompat.getDrawable(context, R.drawable.ic_error_24px)!!)
            .into(view)
    }

    fun cleanUp(imageView: ImageView) {
        picasso.cancelRequest(imageView)
        imageView.setImageDrawable(null)
    }
}