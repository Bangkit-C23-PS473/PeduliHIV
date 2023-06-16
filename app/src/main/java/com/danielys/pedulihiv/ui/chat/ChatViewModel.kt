package com.danielys.pedulihiv.ui.chat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danielys.pedulihiv.data.ApiConfig
import com.danielys.pedulihiv.data.Global
import com.danielys.pedulihiv.data.response.GetChatResponse
import com.danielys.pedulihiv.data.response.GetConsultationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _dataChat = MutableLiveData<GetChatResponse>()
    val dataChat: LiveData<GetChatResponse> = _dataChat

    fun getConsultation(id:String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getChat(id)
        client.enqueue(object : Callback<GetChatResponse> {
            override fun onResponse(
                call: Call<GetChatResponse>,
                response: Response<GetChatResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _dataChat.value = response.body()
                } else {
                    Log.e("Error", "message : ${response.message()}")
                }
            }
            override fun onFailure(call: Call<GetChatResponse>, t: Throwable) {
                _isLoading.value = false
            }

        })
    }
}