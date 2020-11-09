package com.android.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivitySelectCity extends AppCompatActivity implements Constants {

    private Button btnSelectCity;
    private EditText etSelectCity;
    private CheckBox cbCloudiness;
    private CheckBox cbHumidity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);

        btnSelectCity = findViewById(R.id.btn_select_city);
        cbCloudiness = findViewById(R.id.cb_cloudiness);
        cbHumidity = findViewById(R.id.cb_humidity);
        etSelectCity = findViewById(R.id.et_select_city);

        readIntent();

        btnSelectCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(CITY, etSelectCity.getText().toString());
                intent.putExtra(CLOUDINESS, cbCloudiness.isChecked());
                intent.putExtra(HUMIDITY, cbHumidity.isChecked());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void readIntent() {
        Intent intent = getIntent();
        String city = intent.getStringExtra(CITY);
        boolean isCloudiness = intent.getBooleanExtra(CLOUDINESS, false);
        boolean isHumidity = intent.getBooleanExtra(HUMIDITY, false);

        if (city != null && city.equals(getResources().getString(R.string.City))) {
            etSelectCity.setHint(getResources().getString(R.string.enter_city));
        } else {
            etSelectCity.setText(city);
        }

        cbCloudiness.setChecked(isCloudiness);
        cbHumidity.setChecked(isHumidity);
    }
}
