package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BmiCalculator extends AppCompatActivity {
    private EditText ageEditText, heightEditText, weightEditText;
    private Button calculateButton, backButton;
    private TextView bmiScoreText, bmiCategoryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        // Initialize views
        ageEditText = findViewById(R.id.edit_age);
        heightEditText = findViewById(R.id.edit_height);
        weightEditText = findViewById(R.id.edit_weight);
        calculateButton = findViewById(R.id.calculate_button);
        backButton = findViewById(R.id.back_button);
        bmiScoreText = findViewById(R.id.bmi_score_text);
        bmiCategoryText = findViewById(R.id.bmi_category_text);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void calculateBMI() {
        // Get user input values
        int age = Integer.parseInt(ageEditText.getText().toString());
        float height = Float.parseFloat(heightEditText.getText().toString());
        float weight = Float.parseFloat(weightEditText.getText().toString());

        // Calculate BMI
        float bmi = weight / ((height / 100) * (height / 100));

        // Display BMI score
        bmiScoreText.setText(String.format("BMI Score: %.2f", bmi));

        // Determine BMI category
        String category;
        if (bmi < 18.5) {
            category = "Underweight";
        } else if (bmi < 25) {
            category = "Normal Weight";
        } else if (bmi < 30) {
            category = "Overweight";
        } else {
            category = "Obese";
        }

        // Display BMI category
        bmiCategoryText.setText("Category: " + category);
    }
}
