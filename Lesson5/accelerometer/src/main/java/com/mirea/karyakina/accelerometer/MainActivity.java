package com.mirea.karyakina.accelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensManager;
    private Sensor accelterometer;
    private TextView azimuthtextView;
    private TextView pitchtextView;
    private TextView rolltextView;

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
        sensManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelterometer = sensManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensManager.registerListener(this, accelterometer, SensorManager.SENSOR_DELAY_NORMAL);

        azimuthtextView = findViewById(R.id.textViewAzimuth);
        pitchtextView = findViewById(R.id.textViewPitch);
        rolltextView = findViewById(R.id.textViewRoll);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensManager.registerListener(this, accelterometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            azimuthtextView.setText(String.format("Azimuth: %s", x));
            pitchtextView.setText(String.format("Pitch: %s", y));
            rolltextView.setText(String.format("Roll: %s", z));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            switch (accuracy){
                case SensorManager.SENSOR_STATUS_UNRELIABLE:
                    Log.d("Sensor", "Акселерометр: Ненадежные данные!");
                    break;
                case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
                    Log.d("Sensor", "Акселерометр: Высокая точность!");
                    break;
            }
        }
    }

}