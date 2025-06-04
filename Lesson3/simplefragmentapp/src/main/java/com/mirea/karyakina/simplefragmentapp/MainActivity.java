package com.mirea.karyakina.simplefragmentapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    private Fragment frag1, frag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View mainView = findViewById(R.id.main);
        if (mainView != null){
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        frag1 = new FirstFragment();
        frag2 = new SecondFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();

        Button btnFirstFragment = (Button) findViewById(R.id.btn_FirstFragment);
        Button btnSecondFragment = (Button) findViewById(R.id.btn_SecondFragment);

        if (btnFirstFragment != null && btnSecondFragment != null){
            btnFirstFragment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragmentManager.beginTransaction().replace(
                            R.id.fragmentContainerView,
                            frag1).commit();
                }
            });
            btnSecondFragment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragmentManager.beginTransaction().replace(
                            R.id.fragmentContainerView,
                            frag2).commit();
                }
            });
        }

    }
}