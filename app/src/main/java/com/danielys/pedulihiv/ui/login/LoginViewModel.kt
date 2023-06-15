package com.danielys.pedulihiv.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielys.pedulihiv.data.ApiConfig
import com.danielys.pedulihiv.data.UserPreferences
import com.danielys.pedulihiv.data.response.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val userPreferences: UserPreferences) : ViewModel(){
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _dataLogin = MutableLiveData<LoginResponse>()
    val dataLogin: LiveData<LoginResponse> = _dataLogin

    fun login(username: String, password: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().login(username, password)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _dataLogin.value = response.body()
                } else {
                    Log.e("Error", "message : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
            }

        })
    }

    fun setGlobal(username: String, name:String, photo:String){
        viewModelScope.launch {
            with(userPreferences){
                setName(name)
                setUsername(username)
                setPhoto(photo)
            }
        }
    }
}