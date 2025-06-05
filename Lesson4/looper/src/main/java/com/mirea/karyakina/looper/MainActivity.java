package com.mirea.karyakina.looper;

import android.nfc.Tag;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.mirea.karyakina.looper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding	binding	=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Handler mainHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void	handleMessage(Message msg){
                String result = msg.getData().getString("result");
                Log.d(MainActivity.class.getSimpleName(), "Результат: " + result);
            }
        };

        NewLooper newLooper = new NewLooper(mainHandler);
        newLooper.start();

        binding.btnLoop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void	onClick(View v){
                int age = Integer.parseInt(binding.edAge.getText().toString());
                String job = binding.edWork.getText().toString();

                if (age <= 0){
                    Log.d(MainActivity.class.getSimpleName(), "Ошибка! Некорректное число!" );
                }

                Message	msg	= Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putInt("age", age);
                bundle.putString("job", job);
                msg.setData(bundle);
                newLooper.mHandler.sendMessage(msg);

            }
        });


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Очищаем binding при уничтожении Activity
        binding = null;
    }
}