package com.dsckiet.hackathonhelpapp.network

import com.dsckiet.hackathonhelpapp.model.*
import retrofit2.Call
import retrofit2.http.*

interface AppNetworkInterface {
    @PUT("users")
    fun updateUser(
        @Body updateUserBody: UpdateUserBody
    ): Call<UpdateUserResponse>

    @GET("pings/emergency")
    fun getEmergency(
        @Header("email") email: String
    ): Call<EmergencyResponse>

    @GET("pings")
    fun getGeneralHelp(
        @Header("email") email: String
    ): Call<GeneralHelpResponse>

    @POST("pings")
    fun askHelp(
        @Header("email") email: String,
        @Body addHelpBody: AddHelpBody

    ): Call<AddHelpResponse>

    @POST("pings")
    fun askEmergency(
        @Header("email") email: String,
        @Body addEmergencyBody: AddEmergencyBody
    ): Call<AddHelpResponse>
}
