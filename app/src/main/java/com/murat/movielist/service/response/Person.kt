package com.murat.movielist.service.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Person(

	@field:SerializedName("birthday")
	val birthday: String? = null,
	@field:SerializedName("also_known_as")
	val alsoKnownAs: List<String?>? = null,
	@field:SerializedName("gender")
	val gender: Int? = 0,
	@field:SerializedName("known_for_department")
	val knownForDepartment: String? = null,
	@field:SerializedName("profile_path")
	val profilePath: String? = null,
	@field:SerializedName("biography")
	val biography: String? = null,
	@field:SerializedName("place_of_birth")
	val placeOfBirth: String? = null,
	@field:SerializedName("name")
	val name: String? = null,
	@field:SerializedName("id")
	val id: Int? = 0
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.createStringArrayList(),
		parcel.readInt(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readInt()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(birthday)
		parcel.writeStringList(alsoKnownAs)
		parcel.writeValue(gender)
		parcel.writeString(knownForDepartment)
		parcel.writeString(profilePath)
		parcel.writeString(biography)
		parcel.writeString(placeOfBirth)
		parcel.writeString(name)
		parcel.writeValue(id)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Person> {
		override fun createFromParcel(parcel: Parcel): Person {
			return Person(parcel)
		}

		override fun newArray(size: Int): Array<Person?> {
			return arrayOfNulls(size)
		}
	}
}
