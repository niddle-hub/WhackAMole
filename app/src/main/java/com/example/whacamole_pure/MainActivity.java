package com.example.whacamole_pure;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(view -> {
            Intent gameIntent = new Intent(this, GameActivity.class);
            startActivity(gameIntent);
        });

        SharedPreferences prefs = getSharedPreferences("PlayerPrefs", MODE_PRIVATE);
        int loadedScore = prefs.getInt("score", 0);

        TextView bestScoreText = findViewById(R.id.textViewScore);
        bestScoreText.setText(String.valueOf(loadedScore));
    }
}