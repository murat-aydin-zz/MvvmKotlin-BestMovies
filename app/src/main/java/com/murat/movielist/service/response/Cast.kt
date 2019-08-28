package com.murat.movielist.service.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Cast(
    @field:SerializedName("id")
    val id: Int = 0,
    @field:SerializedName("name")
    val name: String?  = null,
    @field:SerializedName("profile_path")
    val profilePath: String? = null,
    @field:SerializedName("cast_id")
    val castId: Int = 0,
    @field:SerializedName("character")
    val character: String? = null,
    @field:SerializedName("credit_id")
    val creditId: String? = null

    ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(profilePath)
        parcel.writeInt(castId)
        parcel.writeString(character)
        parcel.writeString(creditId)
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