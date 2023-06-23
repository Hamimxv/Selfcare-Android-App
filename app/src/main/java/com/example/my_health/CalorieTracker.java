package com.example.my_health;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CalorieTracker extends AppCompatActivity {
    private EditText etMealName;
    private EditText etCalorieIntake;
    private Button btnSave;
    private TextView tvTotalCalorie;
    private Button btnBack;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_tracker);

        etMealName = findViewById(R.id.et_meal_name);
        etCalorieIntake = findViewById(R.id.et_calorie_intake);
        btnSave = findViewById(R.id.btn_save);
        tvTotalCalorie = findViewById(R.id.tv_total_calorie);
        btnBack = findViewById(R.id.btn_back);

        // Create or open the SQLite database
        database = openOrCreateDatabase("CalorieTrackerDB", MODE_PRIVATE, null);

        // Create the "meals" table if it doesn't exist
        database.execSQL("CREATE TABLE IF NOT EXISTS meals (meal_name VARCHAR, calorie_intake INT);");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMealData();
                updateTotalCalorie();
                etMealName.getText().clear();
                etCalorieIntake.getText().clear();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Load and display the total calorie on activity creation
        updateTotalCalorie();
    }

    private void saveMealData() {
        String mealName = etMealName.getText().toString();
        int calorieIntake = Integer.parseInt(etCalorieIntake.getText().toString());

        // Insert the meal data into the "meals" table
        String insertQuery = "INSERT INTO meals (meal_name, calorie_intake) VALUES ('" + mealName + "', " + calorieIntake + ");";
        database.execSQL(insertQuery);
    }

    private void updateTotalCalorie() {
        // Retrieve the total calorie from the "meals" table
        String selectQuery = "SELECT SUM(calorie_intake) FROM meals;";
        Cursor cursor = database.rawQuery(selectQuery, null);
        int totalCalorie = 0;
        if (cursor.moveToFirst()) {
            totalCalorie = cursor.getInt(0);
        }
        cursor.close();

        // Update the total calorie TextView
        tvTotalCalorie.setText("Total Calorie: " + totalCalorie);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database when the activity is destroyed
        if (database != null) {
            database.close();
        }
    }
}