package com.dsckiet.hackathonhelpapp.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddHelpBody(
    val description: String = "",
    val forKids: String = "false",
    val latitude: String="0.0",
    val longitude: String="0.0",
    val tag: String = "",
    val vCounter: String=""

) : Parcelable