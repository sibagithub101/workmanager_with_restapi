package com.example.workmanager

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
@POST("validate/pan")
@Headers("Accept:application/json", "Content-Type:application/json")
  suspend fun panApiCall(@Body panRequest: PanRequest):Response<PanResp>
}