package com.murat.movielist.service.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class TMDBCreditsResponse(
    @field:SerializedName("cast")
    val cast: ArrayList<Cast>? = null,
    @field:SerializedName("crew")
    val crew: ArrayList<Crew>? = null

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readArrayList(Cast::class.java.classLoader) as ArrayList<Cast>,
        parcel.readArrayList(Crew::class.java.classLoader) as ArrayList<Crew>
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TMDBCreditsResponse> {
        override fun createFromParcel(parcel: Parcel): TMDBCreditsResponse {
            return TMDBCreditsResponse(parcel)
        }

        override fun newArray(size: Int): Array<TMDBCreditsResponse?> {
            return arrayOfNulls(size)
        }
    }
}