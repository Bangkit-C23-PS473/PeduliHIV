package com.danielys.pedulihiv.data

import java.text.SimpleDateFormat
import java.util.Locale

object Global {
    var user: UserData = UserData("","","")
    fun dateFormatter(stringDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.getDefault())
        val date = inputFormat.parse(stringDate)
        return outputFormat.format(date!!)
    }
}