package com.danielys.pedulihiv.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.danielys.pedulihiv.data.Global
import com.danielys.pedulihiv.data.UserPreferences
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val userPreferences: UserPreferences) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getUsername(): LiveData<String> {
        return userPreferences.getUsername().asLiveData()
    }

    fun setGlobal() {
        viewModelScope.launch {
            userPreferences.getName().collect {
                Global.user?.name = it
            }
            userPreferences.getUsername().collect {
                Global.user?.username = it
            }
            userPreferences.getPhoto().collect {
                Global.user?.profilePhoto = it
            }
        }
    }

}