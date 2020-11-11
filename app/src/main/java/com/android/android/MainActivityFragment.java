package com.android.android;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.android.cards.SocSource;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivityFragment extends Fragment implements Constants {

    private MainPresenter presenter;
    private View mainCityContainer;
    private View mainCloudinessContainer;
    private View mainHumidityContainer;
    private TextView mainTemperature;
    private TextView mainCity;

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

        mainCityContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectCityFragment selectCityFragment = new SelectCityFragment();

                Bundle bundle = new Bundle();
                bundle.putString(CITY, mainCity.getText().toString());
                bundle.putBoolean(CLOUDINESS, isVisible(mainCloudinessContainer));
                bundle.putBoolean(HUMIDITY, isVisible(mainHumidityContainer));

                selectCityFragment.setArguments(bundle);

                getFragmentManager().
                        beginTransaction()
                        .replace(R.id.fragment_container, selectCityFragment)
                        .commit();

            }
        });


        mainTemperature.setText(presenter.getTemperature());

        SocSource sourceData = new SocSource(getResources());
        initRecyclerView(view, sourceData.build());


        readIntent();
        return view;
    }

    private void initRecyclerView(View view, SocSource data){
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        // Эта установка служит для повышения производительности системы
        recyclerView.setHasFixedSize(true);

        // Будем работать со встроенным менеджером
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        // Установим адаптер
        SocnetAdapter adapter = new SocnetAdapter(data);
        recyclerView.setAdapter(adapter);

        // Добавим разделитель карточек
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(),  LinearLayoutManager.HORIZONTAL);
        itemDecoration.setDrawable(getActivity().getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(itemDecoration);

    }


    private boolean isVisible(View view) {
        int isVisible = view.getVisibility();
        if (isVisible == View.VISIBLE) {
            return true;
        } else {
            return false;
        }
    }

    private void readIntent() {
        Bundle args = getArguments();
        String city = null;
        boolean isCloudiness = false;
        boolean isHumidity = false;
        if (args != null) {
            city = args.getString(CITY);
            isCloudiness = args.getBoolean(CLOUDINESS, false);
            isHumidity = args.getBoolean(HUMIDITY, false);

            if (city != null && city.equals(getResources().getString(R.string.city))) {
                mainCity.setHint(getResources().getString(R.string.enter_city));
            } else {
                mainCity.setText(city);
                mainCity.setGravity(Gravity.CENTER_HORIZONTAL);
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
        } else {
            mainCloudinessContainer.setVisibility(View.VISIBLE);
            mainHumidityContainer.setVisibility(View.VISIBLE);
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
