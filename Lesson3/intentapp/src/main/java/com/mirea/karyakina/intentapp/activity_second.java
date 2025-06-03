package com.mirea.karyakina.intentapp;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;


import com.mirea.karyakina.intentapp.databinding.ActivitySecondBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class activity_second extends AppCompatActivity {
    private TextView textWhatTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String text_whatTime = getIntent().getStringExtra("time");

        textWhatTime = findViewById(R.id.txtWhatTime);
        textWhatTime.setText("\n" + text_whatTime);

    }

    public void onNewActivity2(View view){
        Intent intent = new Intent(this, activity_third.class);

        int studentNum = 16;
        intent.putExtra("num_student", studentNum);

        startActivities(new Intent[]{intent});
    }

}