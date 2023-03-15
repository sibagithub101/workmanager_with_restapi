package com.example.workmanager;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class JavaMainActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_main);
        startLogoutTimer();
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
                .addTag("my_periodic_work_tag")
                .build();
        WorkManager.getInstance(this).enqueue(periodicWorkRequest);

       WorkManager.getInstance(this).enqueueUniquePeriodicWork("my_periodic_work_tag", ExistingPeriodicWorkPolicy.KEEP,periodicWorkRequest);
       WorkManager.getInstance(this).cancelAllWorkByTag("my_periodic_work_tag");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        countDownTimer.cancel();
        startLogoutTimer();
        return super.dispatchTouchEvent(ev);

    }

    private void startLogoutTimer() {
      countDownTimer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                Log.e(TAG, "seconds_remaining"+millisUntilFinished / 1000);
            }
            public void onFinish() {
                Log.e(TAG, "onTimerFinishDone: ");
            }
        }.start();

    }
}