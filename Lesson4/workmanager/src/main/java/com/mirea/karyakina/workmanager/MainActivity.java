package com.mirea.karyakina.workmanager;

import android.app.ApplicationErrorReport;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Constraints constraints	= new Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                /*.setRequiredNetworkType(NetworkType.UNMETERED)*/
                .build();

        WorkRequest	uploadWorkRequest = new	OneTimeWorkRequest.Builder(UploadWorker.class)
                .setConstraints(constraints)
                .build();

        WorkManager
                .getInstance(this)
                .enqueue(uploadWorkRequest);


        WorkManager.getInstance(this).enqueue(uploadWorkRequest);
    }
}