package com.danielys.pedulihiv.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danielys.pedulihiv.data.ApiConfig
import com.danielys.pedulihiv.data.response.LoginResponse
import com.danielys.pedulihiv.data.response.MotivationrResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _dataMotivation = MutableLiveData<MotivationrResponse>()
    val dataMotivation: LiveData<MotivationrResponse> = _dataMotivation

    fun getMotivation() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getMotivation()
        client.enqueue(object : Callback<MotivationrResponse> {
            override fun onResponse(
                call: Call<MotivationrResponse>,
                response: Response<MotivationrResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _dataMotivation.value = response.body()
                } else {
                    Log.e("Error", "message : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MotivationrResponse>, t: Throwable) {
                _isLoading.value = false
            }

        })
    }
}