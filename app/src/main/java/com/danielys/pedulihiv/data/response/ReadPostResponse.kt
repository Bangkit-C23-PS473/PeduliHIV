package com.danielys.pedulihiv.data.response

data class ReadPostResponse(
	val data: DataReadPost? = null,
	val is_like: Boolean? = null,
	val error: Boolean? = null,
	val message: String? = null
)

data class DataReadPost(
	val date: String? = null,
	val photo_header: String? = null,
	val name: String? = null,
	val title: String? = null,
	val content: String? = null
)

