package com.example.workmanager

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PanWorkManager(private val context: Context, params:WorkerParameters): Worker(context,params) {
    override fun doWork(): Result {
        Log.e(TAG, "WorkerCalled............!!!")
        val repo = (context as MyApplication).repo
        val data = inputData.getString("data")
        CoroutineScope(Dispatchers.IO).launch {
            repo.panAPiCall(PanRequest(data))
        }
        return Result.success()
    }
    }
