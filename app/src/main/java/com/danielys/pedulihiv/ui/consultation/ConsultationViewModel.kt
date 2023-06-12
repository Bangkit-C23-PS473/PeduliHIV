package com.danielys.pedulihiv.ui.consultation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConsultationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is consultation Fragment"
    }
    val text: LiveData<String> = _text
}