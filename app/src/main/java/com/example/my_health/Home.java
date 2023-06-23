package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {
    CardView labtest, buymedicine, findmydoc, articles, orders, logout;
    CardView bmicalculator, stepcalculator, calorietracker; // Added CalorieTracker card

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        labtest = findViewById(R.id.c_labtest);
        buymedicine = findViewById(R.id.c_buymedicine);
        findmydoc = findViewById(R.id.c_finddoc);
        articles = findViewById(R.id.c_articles);
        orders = findViewById(R.id.c_orderdetails);
        logout = findViewById(R.id.logout);
        bmicalculator = findViewById(R.id.c_bmicalculator);
        stepcalculator = findViewById(R.id.c_stepcalculator);
        calorietracker = findViewById(R.id.c_calorietracker); // Initialize CalorieTracker card

        labtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, labtest.class));
            }
        });

        findmydoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, findmydoc.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Login.class));
            }
        });

        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, myorders.class));
            }
        });

        buymedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, medicines.class));
            }
        });

        articles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, articles.class));
            }
        });

        bmicalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, BmiCalculator.class));
            }
        });

        stepcalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, StepCalculator.class));
            }
        });

        calorietracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, CalorieTracker.class));
            }
        });
    }
}
