package com.example.tfspushtool.network

import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


private const val BASE_URL = "http://192.168.8.101:5050/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()


interface PushTfsApiService {
    @POST("api/Algorithms/PushTFSUserDataToTfs")
    suspend fun pushTFSUserDataToTfs(@Body requestBody: TFSUserData): Boolean
}

object MarsApi {
    fun getInstance(): Retrofit {
        return retrofit
    }
}


data class TFSUserData(
    @Json(name = "tfsAddress")
    val tfsAddress: String,
    @Json(name = "tokenInformation")
    val tokenInformation: String,
    @Json(name = "userName")
    val userName: String,
    @Json(name = "message")
    val message: String
)