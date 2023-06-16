package com.danielys.pedulihiv.data

import com.danielys.pedulihiv.data.response.ActivitiesResponse
import com.danielys.pedulihiv.data.response.GetChatResponse
import com.danielys.pedulihiv.data.response.GetConsultationResponse
import com.danielys.pedulihiv.data.response.GetDoctorResponse
import com.danielys.pedulihiv.data.response.GetPostResponse
import com.danielys.pedulihiv.data.response.LoginResponse
import com.danielys.pedulihiv.data.response.MakeConsulResponse
import com.danielys.pedulihiv.data.response.MotivationrResponse
import com.danielys.pedulihiv.data.response.RegisterChatResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

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
    ): Call<RegisterChatResponse>

    @GET("getmotivation")
    fun getMotivation(): Call<MotivationrResponse>

    @GET("getactivitiesuser/{username}")
    fun getActivities(
        @Path("username") username: String
    ): Call<ActivitiesResponse>

    @FormUrlEncoded
    @POST("getuserconsultations")
    fun getConsultation(
        @Field("username") username: String
    ): Call<GetConsultationResponse>

    @GET("showchat")
    fun getChat(
        @Query("consultations_id") username: String
    ): Call<GetChatResponse>

    @Multipart
    @POST("insertchat")
    fun sendChat(
        @Part photo: MultipartBody.Part,
        @Part("consultations_id") consultations_id: RequestBody,
        @Part("sender_username") sender_username: RequestBody,
        @Part("text") text: RequestBody
    ): Call<RegisterChatResponse>

    //    @Multipart
//    @POST("insertchat")
//    fun sendChat(
//        @Part photo: MultipartBody.Part,
//        @Field("consultations_id") consultations_id: String,
//        @Field("sender_username") sender_username: String,
//        @Field("text") text : String
//    ): Call<RegisterChatResponse>
    @FormUrlEncoded
    @POST("insertchat")
    fun sendChat(
        @Field("consultations_id") consultations_id: String,
        @Field("sender_username") sender_username: String,
        @Field("text") text: String
    ): Call<RegisterChatResponse>

    @GET("getdoctors")
    fun getDoctor(): Call<GetDoctorResponse>

    @FormUrlEncoded
    @POST("makeconsul")
    fun makeConsul(
        @Field("users_username") users_username: String,
        @Field("doctors_username") doctors_username: String
    ): Call<MakeConsulResponse>

    @GET("getpost")
    fun getPost(
        @Query("users_username") username: String
    ): Call<GetPostResponse>
}