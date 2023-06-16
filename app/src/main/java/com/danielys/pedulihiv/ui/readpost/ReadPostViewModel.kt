package com.danielys.pedulihiv.ui.readpost

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danielys.pedulihiv.data.ApiConfig
import com.danielys.pedulihiv.data.Global
import com.danielys.pedulihiv.data.response.ReadPostResponse
import com.danielys.pedulihiv.data.response.RegisterChatResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReadPostViewModel(): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _dataPost = MutableLiveData<ReadPostResponse>()
    val dataPost: LiveData<ReadPostResponse> = _dataPost

    fun readPost(id: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().readPost(id, Global.user.username)
        client.enqueue(object : Callback<ReadPostResponse> {
            override fun onResponse(
                call: Call<ReadPostResponse>,
                response: Response<ReadPostResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _dataPost.value = response.body()
                } else {
                    Log.e("Error", "message : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ReadPostResponse>, t: Throwable) {
                _isLoading.value = false
            }

        })
    }
}