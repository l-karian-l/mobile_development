package com.mirea.karyakina.intentapp;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.mirea.karyakina.intentapp.databinding.ActivityThirdBinding;

public class activity_third extends AppCompatActivity {

    private TextView textStudentNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        int num_StudNum = getIntent().getIntExtra("num_student", 0);
        int square = num_StudNum * num_StudNum;
        String squareStr = String.valueOf(square);

        textStudentNum = findViewById(R.id.txt_InfoStudentNum);
        textStudentNum.setText("КВАДРАТ ЗНАЧЕНИЯ МОЕГО НОМЕРА ПО СПИСКУ В ГРУППЕ СОСТАВЛЯЕТ ЧИСЛО " + squareStr);
    }
}