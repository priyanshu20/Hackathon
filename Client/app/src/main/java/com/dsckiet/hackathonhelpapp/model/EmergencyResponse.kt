package com.dsckiet.hackathonhelpapp.model


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EmergencyResponse(
    val data: List<Data>?=null,
    val error: Boolean=false,
    val message: String=""
):Parcelable

@Parcelize
data class Location(
    val coordinates: List<Double>?=null,
    val type: String=""
):Parcelable

@Parcelize
data class Data(
    val createdAt: String="",
    val description: String="",
    val forKids: Boolean=false,
    @Json(name = "_id")
    val id: String="",
    val location: Location?=null,
    val reward: String="",
    val status: String="",
    val tag: String="",
    val uid: String="",
    val updatedAt: String="",
    @Json(name = "__v")
    val v: Int=0

):Parcelable