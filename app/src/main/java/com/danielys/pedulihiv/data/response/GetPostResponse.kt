package com.danielys.pedulihiv.data.response

data class GetPostResponse(
	val data: List<DataItemPost?>? = null
)

data class DataItemPost(
	val is_like: Boolean? = null,
	val posts_id: Int? = null,
	val title: String? = null,
	val content: String? = null,
	val photo_header: String? = null
)

