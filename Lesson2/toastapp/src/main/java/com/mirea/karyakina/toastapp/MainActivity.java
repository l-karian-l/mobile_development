package com.mirea.karyakina.toastapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText edTextToast;
    private EditText edTextGroup;
    private EditText edTextNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }

    public void showToast(View view) {
        edTextToast = findViewById(R.id.ed_txtToast);
        edTextGroup = findViewById(R.id.ed_txtGroup);
        edTextNum = findViewById(R.id.ed_txtNum);

        String messageT = edTextToast.getText().toString();
        int countT = messageT.length();
        String messageG = edTextGroup.getText().toString();
        int countG = messageG.length();
        String messageN = edTextNum.getText().toString();

        if (countG == 0 && messageN.isEmpty()) {
            String message = "СТУДЕНТ --" + " ГРУППА -- " + " Количество символов - " + countT;
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        } else if (countG == 0){
            String message = "СТУДЕНТ № " + messageN +" ГРУППА -- " + " Количество символов -" + countT;
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        } else if (messageN.isEmpty()) {
            String message = "СТУДЕНТ -- " + "ГРУППА " + messageG + " Количество символов - " + countT;
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        } else {
            String message = "СТУДЕНТ № " + messageN + " ГРУППА " + messageG + " Количество символов - " + countT;
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }
}