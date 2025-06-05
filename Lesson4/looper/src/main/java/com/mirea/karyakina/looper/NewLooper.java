package com.mirea.karyakina.looper;

import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

public class NewLooper extends Thread{
    public Handler mHandler;
    private Handler mainHandler;
    public NewLooper(Handler mainThreadHandler)	{
        mainHandler =mainThreadHandler;
    }

    public void run() {
        Log.d("NewLooper","run");
        Looper.prepare();

        mHandler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                int age = msg.getData().getInt("age", 0);
                String job = msg.getData().getString("job");

                try {
                    Thread.sleep(age * 100L);
                    String result = "После задержки в " + age + " секунд:\n" +
                            "Ваш возраст: " + age + "\n" +
                            "Ваша профессия: " + job;

                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("result", result);
                    message.setData(bundle);
                    mainHandler.sendMessage(message);

                } catch (InterruptedException e) {
                    Message err = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("result", "Ошибка обработки");
                    err.setData(bundle);
                    mainHandler.sendMessage(err);
                }
            }
        };
        Looper.loop();
    }
}
