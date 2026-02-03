package com.example.androidhw2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast; // For displaying error messages

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber1;
    private EditText editTextNumber2;
    private Button buttonAdd;
    private Button buttonSubtract;
    private Button buttonMultiply;
    private Button buttonDivide;
    private TextView textViewResult;

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

        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSubtract = findViewById(R.id.buttonSubtract);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonDivide = findViewById(R.id.buttonDivide);
        textViewResult = findViewById(R.id.textViewResult);

        buttonAdd.setOnClickListener(v -> calculateAndDisplayResult("+"));
        buttonSubtract.setOnClickListener(v -> calculateAndDisplayResult("-"));
        buttonMultiply.setOnClickListener(v -> calculateAndDisplayResult("*"));
        buttonDivide.setOnClickListener(v -> calculateAndDisplayResult("/"));
    }

    private void calculateAndDisplayResult(String operation) {
        String num1Str = editTextNumber1.getText().toString();
        String num2Str = editTextNumber2.getText().toString();

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double result = 0;
            String operatorSymbol = "";

            switch (operation) {
                case "+":
                    result = num1 + num2;
                    operatorSymbol = "+";
                    break;
                case "-":
                    result = num1 - num2;
                    operatorSymbol = "-";
                    break;
                case "*":
                    result = num1 * num2;
                    operatorSymbol = "*";
                    break;
                case "/":
                    if (num2 == 0) {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                        textViewResult.setText("Result: Error (Division by zero)");
                        return;
                    }
                    result = num1 / num2;
                    operatorSymbol = "/";
                    break;
            }
            textViewResult.setText(String.format("Result: %.1f %s %.1f = %.1f", num1, operatorSymbol, num2, result));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show();
            textViewResult.setText("Result: Error (Invalid input)");
        }
    }
}