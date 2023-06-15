package com.danielys.pedulihiv.data

import com.danielys.pedulihiv.data.response.ActivitiesResponse
import com.danielys.pedulihiv.data.response.LoginResponse
import com.danielys.pedulihiv.data.response.MotivationrResponse
import com.danielys.pedulihiv.data.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("username") username: String,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("sex") sex: String
    ): Call<RegisterResponse>

    @GET("getmotivation")
    fun getMotivation():Call<MotivationrResponse>

    @GET("getactivitiesuser/{username}")
    fun getActivities(
        @Path("username") username: String
    ): Call<ActivitiesResponse>
}