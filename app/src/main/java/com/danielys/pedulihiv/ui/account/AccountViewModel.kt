package com.danielys.pedulihiv.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielys.pedulihiv.data.Global
import com.danielys.pedulihiv.data.UserData
import com.danielys.pedulihiv.data.UserPreferences
import com.danielys.pedulihiv.data.response.LoginResponse
import kotlinx.coroutines.launch

class AccountViewModel(private val userPreferences: UserPreferences) : ViewModel() {
    private val _logoutResponse = MutableLiveData<Boolean>()
    val logoutResponse : LiveData<Boolean> = _logoutResponse
    fun unsetData(){
        _logoutResponse.value = false
        viewModelScope.launch{
            userPreferences.setName("")
            userPreferences.setPhoto("")
            userPreferences.setUsername("")
            Global.user = UserData("","","")
            _logoutResponse.value = true
        }
    }
}