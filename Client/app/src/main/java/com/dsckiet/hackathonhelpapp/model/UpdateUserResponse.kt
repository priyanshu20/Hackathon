package com.dsckiet.hackathonhelpapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UpdateUserResponse(
    val message:String="",
    val error:Boolean=false,
    val data:String?=null
):Parcelable

