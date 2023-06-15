package com.danielys.pedulihiv.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.danielys.pedulihiv.data.UserPreferences
import com.danielys.pedulihiv.ui.login.LoginViewModel
import com.danielys.pedulihiv.ui.main.MainViewModel

class PrefViewModelProvider(private val pref: UserPreferences) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(pref) as T
        }
        else if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}