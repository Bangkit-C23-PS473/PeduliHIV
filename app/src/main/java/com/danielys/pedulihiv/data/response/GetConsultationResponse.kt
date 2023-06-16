package com.danielys.pedulihiv.data.response

data class GetConsultationResponse(
	val message: String? = null,
	val consultations: List<ConsultationsItem?>? = null
)

data class ConsultationsItem(
	val date: String? = null,
	val profile_photo: String? = null,
	val review: Any? = null,
	val name: String? = null,
	val consultationsId: Int? = null
)

