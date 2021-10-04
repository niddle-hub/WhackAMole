package com.example.whacamole_pure;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class GameSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_settings);

        SharedPreferences.Editor editor = getSharedPreferences("PlayerPrefs", MODE_PRIVATE).edit();
        SharedPreferences prefs = getSharedPreferences("PlayerPrefs", MODE_PRIVATE);

        int x_text = prefs.getInt("grid_x", 3);
        int y_text = prefs.getInt("grid_y", 3);

        EditText grid_x_size_text = findViewById(R.id.editTextNumberX);
        EditText grid_y_size_text = findViewById(R.id.editTextNumberY);

        grid_x_size_text.setText(String.valueOf(x_text));
        grid_y_size_text.setText(String.valueOf(y_text));

        Button saveButton = findViewById(R.id.buttonSave);

        grid_x_size_text.setFilters(new InputFilter[]{new NumericInputFilter(1, 5)});
        grid_y_size_text.setFilters(new InputFilter[]{new NumericInputFilter(2, 5)});

        saveButton.setOnClickListener(v -> {
            if (grid_x_size_text.getText().toString().length() > 0 &&
                    grid_y_size_text.getText().toString().length() > 0) {
                editor.putInt("grid_x", Integer.parseInt(grid_x_size_text.getText().toString()));
                editor.putInt("grid_y", Integer.parseInt(grid_y_size_text.getText().toString()));
                editor.apply();
                startMainActivity();
            }
        });
    }

    private void startMainActivity() {
        Intent intent = new Intent(GameSettings.this, MainActivity.class);
        finish();
        startActivity(intent);
    }
}