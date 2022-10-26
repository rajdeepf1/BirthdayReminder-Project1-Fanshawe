package com.rajdeepsingh.birthdayreminderapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BirthdayModelDataClass(
    @SerializedName("name")
     val nameOfPerson:String,
    @SerializedName("birthDay")
     val birthDayOfPerson:String): Parcelable
