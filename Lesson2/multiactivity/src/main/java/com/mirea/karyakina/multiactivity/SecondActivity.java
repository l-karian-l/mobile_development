package com.mirea.karyakina.multiactivity;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.mirea.karyakina.multiactivity.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    private TextView textViewAboutStudent;
    private String Tag = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String text_studentInfo = getIntent().getStringExtra("key");
        String text_New = getIntent().getStringExtra("input_text");

        textViewAboutStudent = findViewById(R.id.textViewAboutStudent);


        if (text_studentInfo != null) {
            textViewAboutStudent.setText("\n" + text_studentInfo);
            Log.i("SecondActivity", "Получен текст: " + text_studentInfo);
        } else if (text_New != null){
            textViewAboutStudent.setText("\n" + text_New);
            Log.i("SecondActivity", "Получен текст: " + text_New);
        }
    }

    private void onBackAction() {
        Log.d(Tag, "Activity закрывается");
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(Tag, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(Tag, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(Tag, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(Tag, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(Tag, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(Tag, "onDestroy");
    }


}