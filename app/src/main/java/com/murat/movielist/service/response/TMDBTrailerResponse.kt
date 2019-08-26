package com.murat.movielist.service.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class TMDBTrailerResponse(
    @field:SerializedName("results")
    val results: ArrayList<Trailer>? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readArrayList(Trailer::class.java.classLoader) as ArrayList<Trailer>

        ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TMDBTrailerResponse> {
        override fun createFromParcel(parcel: Parcel): TMDBTrailerResponse {
            return TMDBTrailerResponse(parcel)
        }

        override fun newArray(size: Int): Array<TMDBTrailerResponse?> {
            return arrayOfNulls(size)
        }
    }
}