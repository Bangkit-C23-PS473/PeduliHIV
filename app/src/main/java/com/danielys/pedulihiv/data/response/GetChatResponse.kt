package com.danielys.pedulihiv.data.response

data class GetChatResponse(
	val doctor: Doctor? = null,
	val data: List<DataItemChat?>? = null,
	val error: Boolean? = null,
	val message: String? = null
)

data class DataItemChat(
	val sender_username: String? = null,
	val photo: Any? = null,
	val consultations_id: Int? = null,
	val text: String? = null,
	val time: String? = null
)

data class Doctor(
	val name: String? = null,
	val username: String? = null
)

