package com.dsckiet.hackathonhelpapp.network

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AppNetwork {

    private const val BASE_URL = "https://eaeae1468737.ngrok.io"

    fun getClient(context: Context): AppNetworkInterface {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


        val httpClient = OkHttpClient.Builder()
            .build()


        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(httpClient)
            .build()


        val retrofitService: AppNetworkInterface by lazy {
            retrofit.create(AppNetworkInterface::class.java)
        }
        return retrofitService
    }
}