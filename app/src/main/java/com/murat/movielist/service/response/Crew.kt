package com.murat.movielist.service.response

import android.databinding.BindingAdapter
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Crew(
    @field:SerializedName("job")
    val job: String?  = null,
    @field:SerializedName("name")
    val name: String? = null
    ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(job)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Crew> {
        override fun createFromParcel(parcel: Parcel): Crew {
            return Crew(parcel)
        }

        override fun newArray(size: Int): Array<Crew?> {
            return arrayOfNulls(size)
        }
    }
}