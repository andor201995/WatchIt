package com.andor.watchit.screens.common.helper

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.andor.watchit.R
import com.andor.watchit.core.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.squareup.picasso.Picasso

class GlideImageLoader(private val context: Context) : ImageLoader {

    private val glide: RequestManager = Glide.with(context)

    override fun loadImageInto(view: ImageView, uri: String?) {
        val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
        glide
            .load("${Constants.BASE_IMAGE_URL}/${Constants.IMAGE_SIZE}/$uri")
            .centerCrop()
            .thumbnail(glide.load("${Constants.BASE_IMAGE_URL}/${Constants.THUMB_SIZE}/$uri"))
            .apply(requestOptions)
            .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_image)!!)
            .error(ContextCompat.getDrawable(context, R.drawable.ic_error)!!)
            .into(view)
    }

    override fun cleanUp(imageView: ImageView) {
        glide.clear(imageView)
        imageView.setImageDrawable(null)
    }
}

class PicassoImageLoader(private val context: Context) : ImageLoader {

    private val picasso: Picasso = Picasso.get()
    override fun loadImageInto(view: ImageView, uri: String?) {
        picasso
            .load("${Constants.BASE_IMAGE_URL}/${Constants.IMAGE_SIZE}/$uri")
            .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_image)!!)
            .error(ContextCompat.getDrawable(context, R.drawable.ic_error)!!)
            .into(view)
    }

    override fun cleanUp(imageView: ImageView) {
        picasso.cancelRequest(imageView)
        imageView.setImageDrawable(null)
    }
}

interface ImageLoader {
    fun loadImageInto(view: ImageView, uri: String?)
    fun cleanUp(imageView: ImageView)
}
