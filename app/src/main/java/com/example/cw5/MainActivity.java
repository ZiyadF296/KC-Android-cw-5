package com.example.cw5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    // The onCreate method is called when the activity is first created.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_main);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        // The UI has 4 input fields, and one button. Below are the variables that will
        // hold the input values, and the button that will be used to navigate to the
        // next activity.
        EditText nameField = findViewById(R.id.name_input);
        EditText surnameField = findViewById(R.id.surname_input);
        EditText heightField = findViewById(R.id.height_input);
        EditText ageField = findViewById(R.id.age_input);

        Button nextButton = findViewById(R.id.continue_button);

        // The onClick method is called when the button is clicked.
        nextButton.setOnClickListener((v) -> {
            // Validate the text before moving on to the next activity.
            final List<Boolean> conditions = new ArrayList<>();

            conditions.add(!nameField.getText().toString().isEmpty());
            conditions.add(!surnameField.getText().toString().isEmpty());
            conditions.add(!ageField.getText().toString().isEmpty());
            conditions.add(!heightField.getText().toString().isEmpty());

            // Something in the form is not valid. Ignore activity navigation.
            if (conditions.contains(false)) {
                Snackbar.make(v, "Please fill in all fields.", Snackbar.LENGTH_LONG).show();
                return;
            }

            // Validate the numbers in the form are within valid ranges.
            final int age = Integer.parseInt(ageField.getText().toString());
            final int height = Integer.parseInt(heightField.getText().toString());

            if ((age < 5 || age > 120) || (height < 50 || height > 250)) {
                Snackbar.make(v, "Invalid age or height.", Snackbar.LENGTH_LONG).show();
                return;
            }

            // The next activity is started by getting the intent and setting the
            // class to the next activity.
            final android.content.Intent intent = new android.content.Intent(this, IdentityActivity.class);

            // The input values are put into the intent as extras.
            intent.putExtra("name", capitalize(nameField.getText().toString()));
            intent.putExtra("surname", capitalize(surnameField.getText().toString()));
            intent.putExtra("height", heightField.getText().toString());
            intent.putExtra("age", ageField.getText().toString());

            // The intent is started.
            startActivity(intent);
        });
    }

    // Helper method to capitalize the first letter of each word in a string.
    private String capitalize(String s) {
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String w : words) {
            sb.append(Character.toUpperCase(w.charAt(0))).append(w.substring(1)).append(" ");
        }

        return sb.toString().trim();
    }
}