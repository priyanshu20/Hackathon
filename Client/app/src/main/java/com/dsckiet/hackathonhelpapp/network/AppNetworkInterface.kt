package com.dsckiet.hackathonhelpapp.network

import com.dsckiet.hackathonhelpapp.model.EmergencyResponse
import com.dsckiet.hackathonhelpapp.model.GeneralHelpResponse
import com.dsckiet.hackathonhelpapp.model.UpdateUserBody
import com.dsckiet.hackathonhelpapp.model.UpdateUserResponse
import retrofit2.Call
import retrofit2.http.*

interface AppNetworkInterface {
    @PUT("users")
    fun updateUser(
        @Body updateUserBody: UpdateUserBody
    ): Call<UpdateUserResponse>

@GET("pings/emergency")
fun getEmergency(
    @Header("email") email:String
):Call<EmergencyResponse>

    @GET("pings")
    fun getGeneralHelp(
        @Header("email") email:String
    ):Call<GeneralHelpResponse>
}
