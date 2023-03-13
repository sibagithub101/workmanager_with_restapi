package com.example.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
public class JavaPanWorkManager extends Worker {
    Context context;
    WorkerParameters parameters;
    public JavaPanWorkManager(Context context, WorkerParameters parameters){
        super(context,parameters);
        this.context = context;
        this.parameters = parameters;

    }
    @NonNull
    @Override
    public Result doWork() {
        Log.e("TAG", "WorkerCalled............!!!");
        MyApplication application = (MyApplication) context.getApplicationContext();
        PanRepo panRepo = application.getRepo();
        String data1 = getInputData().getString("data");
        String data2 = getInputData().getString("data2");
        //panRepo.panAPiCall(new PanRequest(data1));
        return Result.success();
    }
}
