package com.mirea.karyakina.exploringbinding;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.mirea.karyakina.exploringbinding.databinding.ActivityMainBinding;

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

        binding.txtNameApp.setText("Музыка студента под номером 16");

        binding.seekBar.setMax(300);
        binding.seekBar.setProgress(0);

        binding.btnLastSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentProgress = binding.seekBar.getProgress();
                if (currentProgress != 0 ){
                    binding.seekBar.setProgress(0);
                }
            }
        });

        binding.btnPlaySong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i <= 300; i++) {
                    try {
                        Thread.sleep(1); // Задержка для имитации
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    binding.seekBar.setProgress(i, true);
                }
            }
        });

        binding.btnNextSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentProgress = binding.seekBar.getProgress();
                if (currentProgress < 30000){
                    binding.seekBar.setProgress(30000);
                }
            }
        });

    }



}