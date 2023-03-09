package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity2 : AppCompatActivity() {
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        button = findViewById(R.id.btnSubmit)
        button.setOnClickListener {
            setUpWorker()
        }
    }
    private fun setUpWorker() {
        val inputData = Data.Builder()
            .putString("data", "ATEPJ9091M")
            .build()

        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest =
            PeriodicWorkRequest.Builder(PanWorkManager::class.java, 15, TimeUnit.MINUTES)
                .setInputData(inputData)
                .setConstraints(constraint)
                .build()
        WorkManager.getInstance(this).enqueue(workerRequest)
    }
}