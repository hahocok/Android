package com.android.android;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.android.cards.Card;
import com.android.android.cards.CardSource;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.ViewHolder> {
    private CardSource dataSource;

    // Передаем в конструктор источник данных
    // В нашем случае это массив, но может быть и запросом к БД
    public CardsAdapter(CardSource dataSource){
        this.dataSource = dataSource;
    }

    // Создать новый элемент пользовательского интерфейса
    // Запускается менеджером
    @NonNull
    @Override
    public CardsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Создаем новый элемент пользовательского интерфейса
        // Через Inflater
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        // Здесь можно установить всякие параметры
        return new ViewHolder(v);
    }

    // Заменить данные в пользовательском интерфейсе
    // Вызывается менеджером
    @Override
    public void onBindViewHolder(@NonNull CardsAdapter.ViewHolder viewHolder, int i) {
        // Получить элемент из источника данных (БД, интернет...)
        // Вынести на экран используя ViewHolder
        Card soc = dataSource.getSoc(i);
        viewHolder.setData(soc.getDay(), soc.getTemperature());
    }

    // Вернуть размер данных, вызывается менеджером
    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    // Этот класс хранит связь между данными и элементами View
    // Сложные данные могут потребовать несколько View на
    // один пункт списка
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDays;
        private TextView tvTemperature;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDays = itemView.findViewById(R.id.item_day);
            tvTemperature = itemView.findViewById(R.id.item_temperature);
        }

        public void setData(String days, String temperature) {
            tvDays.setText(days);
            tvTemperature.setText(temperature);
        }
    }

}
