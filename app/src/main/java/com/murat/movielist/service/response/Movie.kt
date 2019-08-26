package com.murat.movielist.service.response

import android.databinding.BindingAdapter
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Movie(
    @field:SerializedName("id")
    val id: Int = 0,
    @field:SerializedName("poster_path")
    val posterPath: String? = null,
    @field:SerializedName("title")
    val title: String? = null,
    @field:SerializedName("overview")
    val plot: String? = null,
    @field:SerializedName("release_date")
    val date: String? = null,
    @field:SerializedName("vote_average")
    val rating: String? = null,
    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,
    val posterBytes: ByteArray? = null


) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createByteArray()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(posterPath)
        parcel.writeString(title)
        parcel.writeString(plot)
        parcel.writeString(date)
        parcel.writeString(rating)
        parcel.writeString(backdropPath)
        parcel.writeByteArray(posterBytes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}