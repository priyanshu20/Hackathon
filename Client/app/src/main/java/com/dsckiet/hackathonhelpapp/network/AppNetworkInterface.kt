package com.dsckiet.hackathonhelpapp.network

import com.dsckiet.hackathonhelpapp.model.UpdateUserBody
import com.dsckiet.hackathonhelpapp.model.UpdateUserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface AppNetworkInterface {
    @PUT("users")
    fun updateUser(
        @Body updateUserBody: UpdateUserBody
    ): Call<UpdateUserResponse>


}