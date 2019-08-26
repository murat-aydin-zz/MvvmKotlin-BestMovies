package com.murat.movielist.core

import android.view.View
import android.widget.ImageView
import com.murat.movielist.service.TmdbAPI

import com.squareup.picasso.Picasso
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object BindingAdapter {
    @JvmStatic
    @android.databinding.BindingAdapter("app:visibility")
    fun setVisibilty(view: View, isVisible: Boolean) {
        view.visibility = View.GONE
        if (isVisible) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    @JvmStatic
    @android.databinding.BindingAdapter("app:setDrawableLink")
    fun setDrawableLink(view: ImageView, link: String?) {
        if (link.isNullOrEmpty())
            return
        Picasso.get().cancelRequest(view)
        Picasso.get().load(Constants.NetworkService.POSTER_BASE_URL + link).into(view)
    }
        @JvmStatic
        @android.databinding.BindingAdapter("app:setDrawableYoutubeLink")
        fun setDrawableYoutubeLink(view: ImageView, link: String?) {
            if (link.isNullOrEmpty())
                return
            Picasso.get().cancelRequest(view)
            Picasso.get().load(Constants.NetworkService.YOUTUBE__IMAGE_URL + link + "/0.jpg").into(view)
        }
}