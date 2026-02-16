package com.example.androidhw5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner citySpinner;
    private ImageView cityImageView;

    // 1. Массив названий городов (то, что видит пользователь)
    String[] cityNames = {"Москва", "Париж", "Нью-Йорк"};

    // 2. Массив ресурсов картинок (должен совпадать по порядку с названиями!)
    // Внимание: Убедись, что файлы moscow, paris, new_york лежат в res/drawable
    int[] cityImages = {
            R.drawable.moscow,
            R.drawable.paris,
            R.drawable.new_york
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        citySpinner = findViewById(R.id.citySpinner);
        cityImageView = findViewById(R.id.cityImageView);

        // Создаем адаптер (связывает массив данных и внешний вид спиннера)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                cityNames
        );

        // Указываем вид выпадающего списка (стандартный андроид)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Применяем адаптер к спиннеру
        citySpinner.setAdapter(adapter);

        // Обработка выбора элемента
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // position - это номер выбранного элемента (0, 1 или 2)

                // Меняем картинку в зависимости от позиции
                cityImageView.setImageResource(cityImages[position]);

                // (Дополнительно) Можно вывести всплывающее сообщение
                Toast.makeText(MainActivity.this, "Выбран: " + cityNames[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Ничего не делать
            }
        });
    }
}