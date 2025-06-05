package com.mirea.karyakina.thread;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mirea.karyakina.thread.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Thread mainThread = Thread.currentThread();
        binding.txtNumOfClasses.setText("Имя текущего потока: " + mainThread.getName());

        mainThread.setName("МОЙ НОМЕР ГРУППЫ: БИСО-01-20, НОМЕР ПО СПИСКУ: 16, МОЙ ЛЮБИИМЫЙ ФИЛЬМ: Матрица времени");
        binding.txtNumOfClasses.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(), "Stack:	" + Arrays.toString(mainThread.getStackTrace()));

        binding.btnNumOfClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        int numClasses = 11*2 + 7*2; /* В четные недели - 11 занятий; В нечетные - 7;*/

                        double averageClasses = (double) numClasses / 30; /* Возьмем как данность, что в месяце 30 дней */

                        binding.txtNumOfClasses.append("\n\nНовое имя потока: " + mainThread.getName());
                        binding.txtNumOfClasses.append("\nОбщее количество пар: " + numClasses);
                        binding.txtNumOfClasses.append("\nОбщее количество учебных дней: 30");
                        binding.txtNumOfClasses.append("\nCреднее количество пар в день: " + averageClasses);

                        /* ОБУЧАЮЩИЙ МАТЕРИАЛ НИЖЕ */
                        /*int numberThread = counter++;
                        Log.d("ThreadProject", String.format("Запущен поток № %d студентом группы № %s номер по " +
                                "списку	№ %d", numberThread, "БИСО-01-20", 16));
                        long endTime = System.currentTimeMillis() + 20 * 1000;
                        while (System.currentTimeMillis() < endTime) {
                            synchronized (this) {
                                try {
                                    wait(endTime - System.currentTimeMillis());
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject", "Выполнен поток № " + numberThread);
                        }*/
                    }
                }).start();
            }
        });
    }

}