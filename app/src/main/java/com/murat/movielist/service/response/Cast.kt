package com.murat.movielist.service.response

import android.databinding.BindingAdapter
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Cast(
    @field:SerializedName("name")
    val name: String?  = null,
    @field:SerializedName("profile_path")
    val profilePath: String? = null
    ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(profilePath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cast> {
        override fun createFromParcel(parcel: Parcel): Cast {
            return Cast(parcel)
        }

        override fun newArray(size: Int): Array<Cast?> {
            return arrayOfNulls(size)
        }
    }
}