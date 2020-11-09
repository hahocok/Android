package com.android.android;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Constants {
    private static final String LOG_TAG = "My Log";

    private MainPresenter presenter;
    private TextView mainTemperature;
    private TextView mainCity;
    private TextView mainCloudiness;
    private TextView mainHumidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = MainPresenter.getInstance();
        mainTemperature = findViewById(R.id.main_temperature);
        mainCity = findViewById(R.id.main_city);
        mainCloudiness = findViewById(R.id.main_cloudiness);
        mainHumidity = findViewById(R.id.main_humidity);

        mainCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivitySelectCity.class);
                intent.putExtra(CITY, mainCity.getText().toString());
                intent.putExtra(CLOUDINESS, isVisible(mainCloudiness));
                intent.putExtra(HUMIDITY, isVisible(mainHumidity));
                startActivityForResult(intent, 1);
            }
        });

        String instanceState;
        if (savedInstanceState == null){
            instanceState = "Первый запуск!";
        }
        else{
            instanceState = "Повторный запуск!";
        }
        Toast.makeText(getApplicationContext(), instanceState + " - onCreate()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, instanceState + " - onCreate()");
    }

    private boolean isVisible(View view) {
        int isVisible = view.getVisibility();
        if (isVisible == View.VISIBLE) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode != REQUEST_CODE) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        if (resultCode == RESULT_OK && data != null){
            String city = data.getStringExtra(CITY);
            boolean isCloudiness = data.getBooleanExtra(CLOUDINESS, false);
            boolean isHumidity = data.getBooleanExtra(HUMIDITY, false);

            if (city != null) {
                mainCity.setText(city);
            }

            if (isCloudiness) {
                mainCloudiness.setVisibility(View.VISIBLE);
            } else {
                mainCloudiness.setVisibility(View.GONE);
            }

            if (isHumidity) {
                mainHumidity.setVisibility(View.VISIBLE);
            } else {
                mainHumidity.setVisibility(View.GONE);
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "onStart()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);

        mainTemperature.setText(presenter.getTemperature());

        Toast.makeText(getApplicationContext(), "Повторный запуск!! - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "Повторный запуск!! - onRestoreInstanceState()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "onPause()");
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        presenter.setTemperature(mainTemperature.getText().toString());
        Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "onSaveInstanceState()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "onRestart()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "onStop()");
    }

}