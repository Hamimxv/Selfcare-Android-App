package com.example.my_health;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddMealActivity extends AppCompatActivity {
    private EditText etMealName;
    private EditText etCalorieIntake;
    private Button btnSave;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        etMealName = findViewById(R.id.et_meal_name);
        etCalorieIntake = findViewById(R.id.et_calorie_intake);
        btnSave = findViewById(R.id.btn_save_meal);
        btnBack = findViewById(R.id.btn_back);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMealData();
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void saveMealData() {
        String mealName = etMealName.getText().toString();
        int calorieIntake = Integer.parseInt(etCalorieIntake.getText().toString());

        SharedPreferences sharedPreferences = getSharedPreferences("MealData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("mealName", mealName);
        editor.putInt("calorieIntake", calorieIntake);
        editor.apply();
    }
}
