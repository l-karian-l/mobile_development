package com.mirea.karyakina.data_thread;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mirea.karyakina.data_thread.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;

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

        binding.txtExperiment.setText("\nПояснение:\n");
        binding.txtExperiment.append("- runOnUiThread(Runnable) - Выполняет код в UI-потоке сразу после вызова;" +
                "\n - View.post(Runnable) - Добавляет задачу в очередь сообщений UI-потока и выполняется после текущих задач;" +
                "\n - View.postDelayed(Runnable, delay) - Выполняет код через указанное время (если указать 0, то будет 2м по скорости);\n");

        binding.txtExperiment.append("\nРезультаты эксперимента: ");

        binding.btnExperiment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Runnable runn1 = new Runnable() {
                    public void run() {
                        binding.txtExperiment.append("\nrunn1 = runOnUiThread\n");
                    }
                };
                final Runnable runn2 = new Runnable() {
                    public void run() {
                        binding.txtExperiment.append("runn2 = post\n");
                    }
                };
                final Runnable runn3 = new Runnable() {
                    public void run() {
                        binding.txtExperiment.append("runn3 = postDelayed\n");
                    }
                };

                Thread test = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            TimeUnit.SECONDS.sleep(2);
                            runOnUiThread(runn1);
                            TimeUnit.SECONDS.sleep(1);
                            binding.txtExperiment.postDelayed(runn3, 100);
                            binding.txtExperiment.post(runn2);

                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });
                test.start();
            }});
    }
}
