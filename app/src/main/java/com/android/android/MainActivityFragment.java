package com.android.android;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainActivityFragment extends Fragment implements Constants {

    private MainPresenter presenter;
    private View mainCityContainer;
    private View mainCloudinessContainer;
    private View mainHumidityContainer;
    private TextView mainTemperature;
    private TextView mainCity;
    private TextView mainCloudiness;
    private TextView mainHumidity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_main, container, false);

        presenter = MainPresenter.getInstance();
        mainCityContainer = view.findViewById(R.id.main_city_container);
        mainCloudinessContainer = view.findViewById(R.id.main_cloudiness_container);
        mainHumidityContainer = view.findViewById(R.id.main_humidity_container);
        mainTemperature = view.findViewById(R.id.main_temperature);
        mainCity = view.findViewById(R.id.main_city);
        mainCloudiness = view.findViewById(R.id.main_cloudiness);
        mainHumidity = view.findViewById(R.id.main_humidity);

        mainCityContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSelectCityFragment();
            }
        });

        mainTemperature.setText(presenter.getTemperature());

        readIntent();
        return view;
    }

    private void startSelectCityFragment() {
        saveData();
        SelectCityFragment selectCityFragment = new SelectCityFragment();
        getFragmentManager().
                beginTransaction()
                .replace(R.id.fragment_container, selectCityFragment)
                .commit();
    }


    private void saveData() {
        presenter.setCity(mainCity.getText().toString());
        presenter.setCloudinessVisible(mainCloudinessContainer);
        presenter.setHumidityVisible(mainHumidityContainer);
    }


    private void readIntent() {
        final String city = presenter.getCity();
        final boolean isCloudiness = presenter.isCloudinessVisible();
        final boolean isHumidity = presenter.isHumidityVisible();

        if (city != null && city.equals(getResources().getString(R.string.city))) {
            mainCity.setHint(getResources().getString(R.string.enter_city));
        } else {
            mainCity.setText(city);
        }

        mainTemperature.setText(presenter.getTemperature());

        if (isCloudiness) {
            mainCloudinessContainer.setVisibility(View.VISIBLE);
        } else {
            mainCloudinessContainer.setVisibility(View.GONE);
        }

        if (isHumidity) {
            mainHumidityContainer.setVisibility(View.VISIBLE);
        } else {
            mainHumidityContainer.setVisibility(View.GONE);
        }


    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        readIntent();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.setTemperature(mainTemperature.getText().toString());
        Toast.makeText(getContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, "onSaveInstanceState()");
    }
}
