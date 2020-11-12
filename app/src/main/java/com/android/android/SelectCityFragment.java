package com.android.android;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SelectCityFragment extends Fragment implements Constants {

    private Button btnSelectCity;
    private EditText etSelectCity;
    private CheckBox cbCloudiness;
    private CheckBox cbHumidity;
    private MainPresenter presenter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity_select_city, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);

        btnSelectCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainFragment();
            }
        });

        readIntent();
    }

    private void startMainFragment() {
        saveData();
        SelectCityFragment selectCityFragment = new SelectCityFragment();
        getFragmentManager().
                beginTransaction()
                .replace(R.id.fragment_container, selectCityFragment)
                .commit();
    }


    private void saveData() {
        presenter.setCity(etSelectCity.getText().toString());
        presenter.setRandomTemperature();
        presenter.setCloudinessVisible(cbCloudiness);
        presenter.setHumidityVisible(cbHumidity);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        saveData();
        super.onSaveInstanceState(outState);
    }

    private void initViews(View view) {
        presenter = MainPresenter.getInstance();
        btnSelectCity = view.findViewById(R.id.btn_select_city);
        cbCloudiness = view.findViewById(R.id.cb_cloudiness);
        cbHumidity = view.findViewById(R.id.cb_humidity);
        etSelectCity = view.findViewById(R.id.et_select_city);
    }

    private void readIntent() {
        String city = presenter.getCity();
        boolean isCloudiness = presenter.isCloudinessVisible();
        boolean isHumidity = presenter.isHumidityVisible();

        if (city != null && city.equals(getResources().getString(R.string.city))) {
            etSelectCity.setHint(getResources().getString(R.string.enter_city));
        } else {
            etSelectCity.setText(city);
        }

        cbCloudiness.setChecked(isCloudiness);
        cbHumidity.setChecked(isHumidity);
    }
}
