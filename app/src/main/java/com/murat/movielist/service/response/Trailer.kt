package com.murat.movielist.service.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Trailer(
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("key")
    val key: String? = null
    ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(key)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Trailer> {
        override fun createFromParcel(parcel: Parcel): Trailer {
            return Trailer(parcel)
        }

        override fun newArray(size: Int): Array<Trailer?> {
            return arrayOfNulls(size)
        }
    }
}