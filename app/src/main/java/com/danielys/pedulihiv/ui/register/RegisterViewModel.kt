package com.danielys.pedulihiv.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danielys.pedulihiv.data.ApiConfig
import com.danielys.pedulihiv.data.response.LoginResponse
import com.danielys.pedulihiv.data.response.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _dataRegister = MutableLiveData<RegisterResponse>()
    val dataRegister: LiveData<RegisterResponse> = _dataRegister

    fun register(username: String, name: String, email: String, password: String, sex: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().register(username, name, email, password, sex)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _dataRegister.value = response.body()
                } else {
                    Log.e("Error", "message : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _isLoading.value = false
            }

        })
    }
}