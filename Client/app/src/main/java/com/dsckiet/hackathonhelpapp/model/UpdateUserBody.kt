package com.dsckiet.hackathonhelpapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UpdateUserBody(
    val uid:String="",
    val latitude:Double=0.0,
    val longitude:Double=0.0
):Parcelable
