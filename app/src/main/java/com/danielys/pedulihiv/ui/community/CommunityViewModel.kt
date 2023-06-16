package com.danielys.pedulihiv.ui.community

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danielys.pedulihiv.data.ApiConfig
import com.danielys.pedulihiv.data.Global
import com.danielys.pedulihiv.data.response.GetChatResponse
import com.danielys.pedulihiv.data.response.GetPostResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _dataPost = MutableLiveData<GetPostResponse>()
    val dataPost: LiveData<GetPostResponse> = _dataPost

    fun getPost() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getPost(Global.user.username)
        client.enqueue(object : Callback<GetPostResponse> {
            override fun onResponse(
                call: Call<GetPostResponse>,
                response: Response<GetPostResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _dataPost.value = response.body()
                } else {
                    Log.e("Error", "message : ${response.message()}")
                }
            }
            override fun onFailure(call: Call<GetPostResponse>, t: Throwable) {
                _isLoading.value = false
            }
        })
    }
}