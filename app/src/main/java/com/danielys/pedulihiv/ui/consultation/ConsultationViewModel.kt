package com.danielys.pedulihiv.ui.consultation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danielys.pedulihiv.data.ApiConfig
import com.danielys.pedulihiv.data.Global
import com.danielys.pedulihiv.data.response.GetConsultationResponse
import com.danielys.pedulihiv.data.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConsultationViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _dataConsultation = MutableLiveData<GetConsultationResponse>()
    val dataConsultation: LiveData<GetConsultationResponse> = _dataConsultation

    fun getConsultation() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getConsultation(Global.user.username)
        client.enqueue(object : Callback<GetConsultationResponse> {
            override fun onResponse(
                call: Call<GetConsultationResponse>,
                response: Response<GetConsultationResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _dataConsultation.value = response.body()
                } else {
                    Log.e("Error", "message : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GetConsultationResponse>, t: Throwable) {
                _isLoading.value = false
            }

        })
    }
}