package com.example.workmanager;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class JavaMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_main);
        findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpWorker();
            }
        });
    }

    private void setUpWorker() {
        Data data = new Data.Builder()
                .putString("data", "ATEPJ9091M")
                .putString("data2", "siba")
                .build();
        Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(JavaPanWorkManager.class, 15, TimeUnit.MINUTES)
                .setInputData(data).setConstraints(constraints)
                // add when cancel periodic work  .addTag("my_periodic_work_tag")
                .build();
        WorkManager.getInstance(this).enqueue(periodicWorkRequest);
        //add when cancel periodic work   WorkManager.getInstance(this).enqueueUniquePeriodicWork("my_periodic_work_tag", ExistingPeriodicWorkPolicy.KEEP,periodicWorkRequest);
        //add when cancel periodic work  WorkManager.getInstance(this).cancelAllWorkByTag("my_periodic_work_tag")
    }
}