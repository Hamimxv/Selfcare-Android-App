package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomMealPlansActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_meal_plans);

        // Set the title of the activity
        setTitle("Custom Meal Plans");

        // Get references to the meal plan text views
        TextView mealPlan1TextView = findViewById(R.id.tv_meal_plan_1);
        TextView mealPlan2TextView = findViewById(R.id.tv_meal_plan_2);
        TextView mealPlan3TextView = findViewById(R.id.tv_meal_plan_3);

        // Set the meal plan details
        mealPlan1TextView.setText("Meal Plan 1 details");
        mealPlan2TextView.setText("Meal Plan 2 details");
        mealPlan3TextView.setText("Meal Plan 3 details");

        // Get reference to the back button
        Button backButton = findViewById(R.id.btn_back);

        // Set a click listener on the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity to go back
                finish();
            }
        });
    }
}
