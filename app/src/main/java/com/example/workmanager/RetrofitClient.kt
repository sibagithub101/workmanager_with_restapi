package com.example.workmanager

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        fun getLoginRetrofitInstance(): Retrofit {
            val logging = HttpLoggingInterceptor()
            logging.level = (HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
            client.addInterceptor(logging)

            return Retrofit.Builder()
                .baseUrl("https://kotak-agent-onboarding-twdwtabx5q-uc.a.run.app/KM/")
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
    }
}