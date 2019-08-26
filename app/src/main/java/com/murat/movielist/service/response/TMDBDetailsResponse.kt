package com.murat.movielist.service.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class TMDBDetailsResponse(

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("imdb_id")
    val imdbId: String? = null,

    @field:SerializedName("video")
    val video: Boolean = false,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("revenue")
    val revenue: Int = 0,

    @field:SerializedName("popularity")
    val popularity: Double = 0.toDouble(),

    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("vote_count")
    val voteCount: Int = 0,

    @field:SerializedName("budget")
    val budget: Int = 0,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("original_title")
    val originalTitle: String? = null,

    @field:SerializedName("runtime")
    val runtime: Int = 0,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double = 0.toDouble(),

    @field:SerializedName("tagline")
    val tagline: String? = null,

    @field:SerializedName("adult")
    val adult: Boolean = false,

    @field:SerializedName("homepage")
    val homepage: String? = null,

    @field:SerializedName("status")
    val status: String? = null

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(originalLanguage)
        parcel.writeString(imdbId)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeString(title)
        parcel.writeString(backdropPath)
        parcel.writeInt(revenue)
        parcel.writeDouble(popularity)
        parcel.writeInt(id)
        parcel.writeInt(voteCount)
        parcel.writeInt(budget)
        parcel.writeString(overview)
        parcel.writeString(originalTitle)
        parcel.writeInt(runtime)
        parcel.writeString(posterPath)
        parcel.writeString(releaseDate)
        parcel.writeDouble(voteAverage)
        parcel.writeString(tagline)
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeString(homepage)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TMDBDetailsResponse> {
        override fun createFromParcel(parcel: Parcel): TMDBDetailsResponse {
            return TMDBDetailsResponse(parcel)
        }

        override fun newArray(size: Int): Array<TMDBDetailsResponse?> {
            return arrayOfNulls(size)
        }
    }
}