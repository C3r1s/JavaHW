package com.example.androidhw4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private Button option1Button;
    private Button option2Button;
    private TextView currentLevelTextView;
    private Button backButton; // Новая кнопка

    private Map<Integer, List<Question>> questionsByLevel;
    private int currentLevel = 1;
    private int currentQuestionIndex = 0;

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

        // Инициализация View
        questionTextView = findViewById(R.id.questionTextView);
        option1Button = findViewById(R.id.option1Button);
        option2Button = findViewById(R.id.option2Button);
        currentLevelTextView = findViewById(R.id.currentLevelTextView);
        backButton = findViewById(R.id.backButton);

        // 1. Получаем уровень, который передали из LevelSelectActivity
        // Если ничего не передали (на всякий случай), будет 1
        currentLevel = getIntent().getIntExtra("SELECTED_LEVEL", 1);

        // Обновляем заголовок
        currentLevelTextView.setText("Level " + currentLevel);

        // 2. Настраиваем кнопку "Назад"
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // finish() закрывает текущую активность и возвращает нас на предыдущую (LevelSelect)
                finish();
            }
        });

        questionsByLevel = new HashMap<>();
        populateQuestions();

        // 3. Обработчики кнопок ответов
        option1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You chose option 1", Toast.LENGTH_SHORT).show();
                displayNextQuestion();
            }
        });

        option2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You chose option 2", Toast.LENGTH_SHORT).show();
                displayNextQuestion();
            }
        });

        // Показываем первый вопрос
        displayNextQuestion();
    }

    private void populateQuestions() {
        // Level 1 questions
        addQuestion(1, "Be able to fly or be able to turn invisible?", "Fly", "Turn Invisible");
        addQuestion(1, "Have a pause button or a rewind button in life?", "Pause Button", "Rewind Button");

        // Level 2 questions
        addQuestion(2, "Fight one horse-sized duck or one hundred duck-sized horses?", "One Horse-Sized Duck", "100 Duck-Sized Horses");
        addQuestion(2, "Speak all languages or be able to talk to animals?", "All Languages", "Talk to Animals");

        // Level 3 questions
        addQuestion(3, "Always be 10 minutes late or always be 20 minutes early?", "10 Minutes Late", "20 Minutes Early");
        addQuestion(3, "Have unlimited money or unlimited time?", "Unlimited Money", "Unlimited Time");

        // Можно добавить заглушки для других уровней, чтобы приложение не падало, если выбрать уровень 4+
        for (int i = 4; i <= 21; i++) {
            addQuestion(i, "Question for level " + i, "Option A", "Option B");
        }
    }

    private void addQuestion(int level, String questionText, String option1, String option2) {
        if (!questionsByLevel.containsKey(level)) {
            questionsByLevel.put(level, new ArrayList<>());
        }
        questionsByLevel.get(level).add(new Question(questionText, option1, option2, level));
    }

    private void displayNextQuestion() {
        List<Question> currentLevelQuestions = questionsByLevel.get(currentLevel);

        if (currentLevelQuestions != null && !currentLevelQuestions.isEmpty()) {
            if (currentQuestionIndex < currentLevelQuestions.size()) {
                Question currentQuestion = currentLevelQuestions.get(currentQuestionIndex);
                questionTextView.setText(currentQuestion.getQuestionText());
                option1Button.setText(currentQuestion.getOption1());
                option2Button.setText(currentQuestion.getOption2());
                currentQuestionIndex++;
            } else {
                Toast.makeText(MainActivity.this, "Level Complete!", Toast.LENGTH_SHORT).show();
                // Когда вопросы кончились - выходим в меню уровней
                finish();
            }
        } else {
            questionTextView.setText("No questions for this level.");
            option1Button.setText("-");
            option2Button.setText("-");
        }
    }

    // Inner class for Question
    private static class Question {
        private String questionText;
        private String option1;
        private String option2;
        private int level;

        public Question(String questionText, String option1, String option2, int level) {
            this.questionText = questionText;
            this.option1 = option1;
            this.option2 = option2;
            this.level = level;
        }

        public String getQuestionText() { return questionText; }
        public String getOption1() { return option1; }
        public String getOption2() { return option2; }
    }
}