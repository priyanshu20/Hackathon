package com.dsckiet.hackathonhelpapp.model


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddHelpResponse(
    val data: String="",
    val error: Boolean=false,
    val message: String=""
):Parcelable

