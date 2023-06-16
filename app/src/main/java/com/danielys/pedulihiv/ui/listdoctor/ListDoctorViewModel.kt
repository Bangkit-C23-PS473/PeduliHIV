package com.danielys.pedulihiv.ui.listdoctor

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danielys.pedulihiv.data.ApiConfig
import com.danielys.pedulihiv.data.Global
import com.danielys.pedulihiv.data.response.GetDoctorResponse
import com.danielys.pedulihiv.data.response.MakeConsulResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListDoctorViewModel: ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _dataDoctor = MutableLiveData<GetDoctorResponse>()
    val dataDoctor: LiveData<GetDoctorResponse> = _dataDoctor

    private val _responseConsul = MutableLiveData<MakeConsulResponse>()
    val responseConsul: LiveData<MakeConsulResponse> = _responseConsul

    fun getDoctor() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDoctor()
        client.enqueue(object : Callback<GetDoctorResponse> {
            override fun onResponse(
                call: Call<GetDoctorResponse>,
                response: Response<GetDoctorResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _dataDoctor.value = response.body()
                } else {
                    Log.e("Error", "message : ${response.message()}")
                }
            }
            override fun onFailure(call: Call<GetDoctorResponse>, t: Throwable) {
                _isLoading.value = false
            }

        })
    }

    fun makeConsul(usernameDoctor: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().makeConsul(Global.user.username,usernameDoctor)
        client.enqueue(object : Callback<MakeConsulResponse> {
            override fun onResponse(
                call: Call<MakeConsulResponse>,
                response: Response<MakeConsulResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _responseConsul.value = response.body()
                } else {
                    Log.e("Error", "message : ${response.message()}")
                }
            }
            override fun onFailure(call: Call<MakeConsulResponse>, t: Throwable) {
                _isLoading.value = false
            }

        })
    }
}