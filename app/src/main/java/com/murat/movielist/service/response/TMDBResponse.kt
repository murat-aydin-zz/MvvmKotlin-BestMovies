package com.murat.movielist.service.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.murat.movielist.db.entitiy.MovieEntity
import java.util.ArrayList

data class TMDBResponse(
    @field:SerializedName("page")
    val page: Int = 0,
    @field:SerializedName("results")
    val results: ArrayList<MovieEntity>? = null,
    @field:SerializedName("total_results")
    val totalResults: Int = 0,
    @field:SerializedName("total_pages")
    val totalPages: Int = 0

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readArrayList(MovieEntity::class.java.classLoader) as ArrayList<MovieEntity>,
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(page)
        parcel.writeInt(totalResults)
        parcel.writeInt(totalPages)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TMDBResponse> {
        override fun createFromParcel(parcel: Parcel): TMDBResponse {
            return TMDBResponse(parcel)
        }

        override fun newArray(size: Int): Array<TMDBResponse?> {
            return arrayOfNulls(size)
        }
    }
}
