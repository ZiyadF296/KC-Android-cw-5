package com.example.cw5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class IdentityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_identity);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        // Load the intent that was passed from the previous activity.
        final Intent intent = getIntent();
        final Bundle extras = intent.getExtras();

        // Variables
        final String name = extras.getString("name");
        final String surname = extras.getString("surname");
        final String age = extras.getString("age");
        final String height = extras.getString("height");

        // Set the UI elements to the values that were passed from the previous
        // activity.
        final TextView title = findViewById(R.id.identity_title);

        title.setText(String.format("Hello, %s %s", name, surname));

        // Set the age and height fields to the values that were passed from the
        // previous activity.
        final TextView ageField = findViewById(R.id.age_value);
        final TextView heightField = findViewById(R.id.height_value);

        ageField.setText(age);
        heightField.setText(height);

        // Set a listener for the back button and reset go back to the previous
        // activity.
        final Button backButton = findViewById(R.id.go_back);

        backButton.setOnClickListener((v) -> finish());
    }
}