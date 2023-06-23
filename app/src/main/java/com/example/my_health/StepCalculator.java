package com.example.my_health;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class StepCalculator extends AppCompatActivity implements LocationListener {
    private TextView stepCountText, progressStart, progressEnd;
    private EditText goalInput;
    private ProgressBar progressBar;
    private Button setGoalButton, backButton;
    private LocationManager locationManager;
    private boolean isLocationPermissionGranted;
    private int stepCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_calculator);

        // Initialize views
        stepCountText = findViewById(R.id.step_count_text);
        progressStart = findViewById(R.id.progress_start);
        progressEnd = findViewById(R.id.progress_end);
        goalInput = findViewById(R.id.goal_input);
        progressBar = findViewById(R.id.progress_bar);
        setGoalButton = findViewById(R.id.set_goal_button);
        backButton = findViewById(R.id.back_button);

        // Set click listeners
        setGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGoal();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Initialize step counter and location manager
        stepCount = 0;
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Check location permission
        isLocationPermissionGranted = checkLocationPermission();

        if (isLocationPermissionGranted) {
            startLocationUpdates();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isLocationPermissionGranted) {
            startLocationUpdates();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    private void setGoal() {
        String goalText = goalInput.getText().toString().trim();
        if (!goalText.isEmpty()) {
            int goal = Integer.parseInt(goalText);
            updateProgressBar(stepCount, goal);
        }
    }

    private void updateProgressBar(int currentProgress, int maxProgress) {
        progressBar.setProgress(currentProgress);
        progressBar.setMax(maxProgress);
        progressStart.setText(String.valueOf(currentProgress));
        progressEnd.setText(String.valueOf(maxProgress));
    }

    private boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission not granted, request it
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
            return false;
        } else {
            // Permission already granted
            return true;
        }
    }

    private void startLocationUpdates() {
        if (isLocationPermissionGranted) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                // Location permission not granted, request it
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        1);
            } else {
                // Location permission already granted, start location updates
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            }
        }
    }


    private void stopLocationUpdates() {
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        // Use the location data as needed
        // For example, you can calculate step count based on location changes
        stepCount++;
        stepCountText.setText(String.valueOf(stepCount));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // No implementation needed
    }

    @Override
    public void onProviderEnabled(String provider) {
        // No implementation needed
    }

    @Override
    public void onProviderDisabled(String provider) {
        // No implementation needed
    }
}
