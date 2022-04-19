package com.example.tfspushtool.apiservice

import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


private const val BASE_URL = "http://192.168.8.101:666/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()


interface PushTfsApiService {
    @POST("api/TFS/PushTfsDataToTfs")
    suspend fun pushTFSUserDataToTfs(@Body requestBody: TfsData): Boolean
}

object TfsApi {
    fun getInstance(): Retrofit {
        return retrofit
    }
}


data class TfsData(
    @Json(name = "tfsAddress")
    val tfsAddress: String,
    @Json(name = "token")
    val token: String,
    @Json(name = "backlogTitle")
    val backlogTitle: String,
    @Json(name = "assignedTo")
    val assignedTo: String,
    @Json(name = "iterationPath")
    val iterationPath: String,
    @Json(name = "areaPath")
    val areaPath: String,
    @Json(name = "workItemContent")
    val workItemContent: String
)