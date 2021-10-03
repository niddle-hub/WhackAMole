package com.example.whacamole_pure;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import java.util.concurrent.atomic.AtomicInteger;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int gameDuration = 30000; //in milliseconds
        int timeToCatchAMole = 500; //in milliseconds

        /*Root layout filed*/
        LinearLayout root = new LinearLayout(this);
        root.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        root.setBackgroundColor(ContextCompat.getColor(this, R.color.ground));
        root.setOrientation(LinearLayout.VERTICAL);
        /*End root layout filed*/

        setContentView(root);

        /*Table row filed*/
        TableRow textRow = new TableRow(this);
        LinearLayout.LayoutParams rowLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        rowLayoutParams.topMargin = 10;
        rowLayoutParams.leftMargin = 15;
        rowLayoutParams.rightMargin = 15;
        textRow.setLayoutParams(rowLayoutParams);
        /*End table row filed*/

        /*Score text filed*/
        TextView scoreText = new TextView(this);
        TableRow.LayoutParams scoreTextParams = new TableRow.LayoutParams();
        scoreTextParams.width = TableRow.LayoutParams.WRAP_CONTENT;
        scoreTextParams.height = TableRow.LayoutParams.WRAP_CONTENT;
        scoreTextParams.weight = 1;
        scoreText.setText("0");
        scoreText.setGravity(Gravity.START);
        scoreText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        scoreText.setLayoutParams(scoreTextParams);
        /*End score text filed*/

        /*Timer text filed*/
        TextView timerText = new TextView(this);
        TableRow.LayoutParams timerTextParams = new TableRow.LayoutParams();
        timerTextParams.width = TableRow.LayoutParams.WRAP_CONTENT;
        timerTextParams.height = TableRow.LayoutParams.WRAP_CONTENT;
        timerTextParams.weight = 1;
        timerText.setGravity(Gravity.END);
        timerText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        timerText.setLayoutParams(timerTextParams);
        /*End timer text filed*/

        textRow.addView(scoreText);
        textRow.addView(timerText);
        root.addView(textRow);

        HoleGrid holeGrid = new HoleGrid(this, 3, 3);
        Mole mole = new Mole(this);

        AtomicInteger currentScore = new AtomicInteger(-1);
        AtomicInteger currentTime = new AtomicInteger(gameDuration / 1000 + 1);

        mole.setOnClickListener(v -> {
            currentScore.incrementAndGet();
            scoreText.setText(String.valueOf(currentScore));
        });

        root.addView(holeGrid);

        SharedPreferences.Editor editor = getSharedPreferences("PlayerPrefs", MODE_PRIVATE).edit();
        SharedPreferences prefs = getSharedPreferences("PlayerPrefs", MODE_PRIVATE);
        int loadedScore = prefs.getInt("score", 0);

        new CountDownTimer(gameDuration, timeToCatchAMole) {

            @Override
            public void onTick(long millisUntilFinished) {
                mole.beginPopUp(holeGrid);
            }

            @Override
            public void onFinish() {
                if (loadedScore < currentScore.get()) {
                    editor.putInt("score", currentScore.intValue());
                    editor.apply();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this, R.style.Theme_AppCompat);
                builder.setTitle("Конец игры")
                        .setMessage("Вы набрали: " + currentScore.get() + " Ваш лучший счёт: " + loadedScore + " Хотите продолжить ?")
                        .setPositiveButton("Начать заново", (dialog, id) -> recreate())
                        .setNegativeButton("Выйти в меню", (dialog, id) -> startMainActivity());
                builder.create().show();
            }
        }.start();

        new CountDownTimer(gameDuration, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                currentTime.decrementAndGet();
                timerText.setText(String.valueOf(currentTime));
            }

            @Override
            public void onFinish() {
            }
        }.start();
    }

    private void startMainActivity() {
        Intent intent = new Intent(GameActivity.this, MainActivity.class);
        finish();
        startActivity(intent);
    }

}
