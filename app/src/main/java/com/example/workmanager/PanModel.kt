package com.example.workmanager

import com.google.gson.annotations.SerializedName

data class PanRequest(

	@field:SerializedName("pan_num")
	val panNum: String? = null
)
data class PanResp(

	@field:SerializedName("userData")
	val userData: UserData? = null,

	@field:SerializedName("errorInfo")
	val errorInfo: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class UserData(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("panNumber")
	val panNumber: String? = null,

	@field:SerializedName("dateOfIssue")
	val dateOfIssue: String? = null
)
