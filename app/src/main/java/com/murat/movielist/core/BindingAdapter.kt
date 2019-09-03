package com.murat.movielist.core

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso

object BindingAdapter {
    @JvmStatic
    @androidx.databinding.BindingAdapter("app:visibility")
    fun setVisibility(view: View, isVisible: Boolean) {
        view.visibility = View.GONE
        if (isVisible) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("app:setDrawableLink")
    fun setDrawableLink(view: ImageView, link: String?) {
        if (link.isNullOrEmpty())
            return
        Picasso.get().cancelRequest(view)
        Picasso.get().load(Constants.NetworkService.POSTER_BASE_URL + link).into(view)
    }
        @JvmStatic
        @androidx.databinding.BindingAdapter("app:setDrawableYoutubeLink")
        fun setDrawableYoutubeLink(view: ImageView, link: String?) {
            if (link.isNullOrEmpty())
                return
            Picasso.get().cancelRequest(view)
            Picasso.get().load(Constants.NetworkService.YOUTUBE__IMAGE_URL + link + "/0.jpg").into(view)
        }
}