package com.mirea.karyakina.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Locale;

public class MyDateDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(requireActivity(),
                (view, year1, month1, dayOfMonth) -> {
                    String date = String.format(Locale.getDefault(),
                            "%02d.%02d.%d", dayOfMonth, month1 + 1, year1);
                    Toast.makeText(getActivity(),"Выбрана дата: " + date, Toast.LENGTH_SHORT).show();
                }, year, month, day);
    }
}

