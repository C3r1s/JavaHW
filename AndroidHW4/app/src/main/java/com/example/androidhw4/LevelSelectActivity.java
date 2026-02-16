package com.example.androidhw4;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LevelSelectActivity extends AppCompatActivity {

    private static final int NUMBER_OF_LEVELS = 21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_level_select);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        GridLayout levelGrid = findViewById(R.id.levelGrid);

        // Получаем размер кнопки из ресурсов (50dp или больше, если изменили dimens.xml)
        int buttonSize = (int) getResources().getDimension(R.dimen.level_button_size);
        // Отступы между кнопками (конвертируем 8dp в пиксели для аккуратности)
        int margin = (int) (8 * getResources().getDisplayMetrics().density);

        for (int i = 1; i <= NUMBER_OF_LEVELS; i++) {
            Button levelButton = new Button(this);
            levelButton.setText(String.valueOf(i));

            // --- СТИЛИЗАЦИЯ ПОД СКРИНШОТ ---
            levelButton.setTextColor(Color.WHITE); // Белый текст
            levelButton.setTextSize(22); // Размер шрифта больше
            levelButton.setTypeface(null, Typeface.BOLD); // Жирный шрифт
            // Добавляем тень тексту для контраста (радиус, смещение X, смещение Y, цвет)
            levelButton.setShadowLayer(3, 2, 2, Color.parseColor("#33000000"));

            // Центрирование текста
            levelButton.setGravity(Gravity.CENTER);

            // Устанавливаем наш кастомный фон (синий с желтой рамкой)
            levelButton.setBackgroundResource(R.drawable.level_button_background);

            // --- ПАРАМЕТРЫ РАСПОЛОЖЕНИЯ ---
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = buttonSize;
            params.height = buttonSize;
            params.setMargins(margin, margin, margin, margin);

            // Важно для GridLayout, чтобы кнопки шли по порядку и переносились
            // Если в XML задан columnCount="7", здесь можно не указывать rowSpec/columnSpec явно,
            // но для надежности можно оставить пустым конструктор.

            levelButton.setLayoutParams(params);

            // Обработчик нажатия
            levelButton.setOnClickListener(createLevelClickListener(i));

            levelGrid.addView(levelButton);
        }
    }

    private View.OnClickListener createLevelClickListener(final int level) {
        return v -> {
            Intent intent = new Intent(LevelSelectActivity.this, MainActivity.class);
            intent.putExtra("SELECTED_LEVEL", level);
            startActivity(intent);
        };
    }
}