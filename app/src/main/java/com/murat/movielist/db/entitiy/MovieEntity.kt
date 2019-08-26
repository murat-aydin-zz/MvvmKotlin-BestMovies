package com.murat.movielist.db.entitiy

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.murat.movielist.service.response.Movie

@Entity(tableName = "Movie")
data class MovieEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var posterPath: String?,
    var title: String?,
    var overview: String?,
    var release_date: String?,
    var vote_average: String?,
    var backdropPath: String?,
    var posterBytes: ByteArray?,
    var isfavorite: Int = 0
) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createByteArray(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(posterPath)
        parcel.writeString(title)
        parcel.writeString(overview)
        parcel.writeString(release_date)
        parcel.writeString(vote_average)
        parcel.writeString(backdropPath)
        parcel.writeByteArray(posterBytes)
        parcel.writeInt(isfavorite)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieEntity> {
        override fun createFromParcel(parcel: Parcel): MovieEntity {
            return MovieEntity(parcel)
        }

        override fun newArray(size: Int): Array<MovieEntity?> {
            return arrayOfNulls(size)
        }
    }

}
