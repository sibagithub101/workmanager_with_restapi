package com.example.workmanager

import android.app.Application

class MyApplication:Application() {
    lateinit var repo: PanRepo
    override fun onCreate() {
        super.onCreate()
         init()
    }
    private fun init() {
        val apiService = RetrofitClient.getLoginRetrofitInstance().create(ApiService::class.java)
        repo = PanRepo(apiService)
    }
}