package com.danielys.pedulihiv.data.response

data class ActivitiesResponse(
	val data: List<DataItem?>? = null,
	val message: String? = null
)

data class DataItem(
	val name: String? = null,
	val activitiesId: Int? = null,
	val logo: String? = null,
	val description: Any? = null,
	val time: String? = null
)

