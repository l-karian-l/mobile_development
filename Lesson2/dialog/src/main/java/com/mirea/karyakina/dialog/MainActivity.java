package com.mirea.karyakina.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }

    public void onClickShowDialog(View view) {
        AlertDialogFragment dialogFragment = new AlertDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
    }

    public void onOkClicked() {
        Toast.makeText(this, "Вы выбрали кнопку \"Иду дальше\"!", Toast.LENGTH_LONG).show();
    }

    public void onCancelClicked() {
        Toast.makeText(this, "Вы выбрали кнопку \"Нет\"!", Toast.LENGTH_LONG).show();
    }

    public void onNeutralClicked() {
        Toast.makeText(this, "Вы выбрали кнопку \"На паузе\"!", Toast.LENGTH_LONG).show();
    }

    public void onClickShowSnackbar(View view) {
        Snackbar.make(findViewById(android.R.id.content), "Это Snackbar сообщение", Snackbar.LENGTH_LONG)
                .setAction("Действие", v -> {
                    Toast.makeText(this, "Действие выполнено!", Toast.LENGTH_SHORT).show();
                })
                .show();
    }

    public void showTimePickerDialog(View view) {
        DialogFragment timeDialog = new MyTimeDialogFragment();
        timeDialog.show(getSupportFragmentManager(), "Реализация TimePicker");
    }

    public void onDateShowDialog(View view) {
        DialogFragment dateDialog = new MyDateDialogFragment();
        dateDialog.show(getSupportFragmentManager(), "Реализация DatePicker");
    }

    public void onShowProgressDialog(View view) {
        DialogFragment progDialog = new MyProgressDialogFragment();
        progDialog.show(getSupportFragmentManager(), "Реализация DatePicker");
    }

}
