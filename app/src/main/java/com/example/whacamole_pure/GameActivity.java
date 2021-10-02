package com.example.whacamole_pure;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.CountDownTimer;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.os.Bundle;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Root layout filed*/
        LinearLayout root = new LinearLayout(this);
        root.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
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

        HoleGrid holeGrid = new HoleGrid(this, 3,3);
        Mole mole = new Mole(this);

        AtomicInteger currentScore = new AtomicInteger(-1);
        AtomicInteger currentTime = new AtomicInteger(31);

        mole.setOnClickListener(v -> {
            currentScore.getAndIncrement();
            scoreText.setText(String.valueOf(currentScore));
        });

        root.addView(holeGrid);

        SharedPreferences.Editor editor = getSharedPreferences("PlayerPrefs", MODE_PRIVATE).edit();
        SharedPreferences prefs = getSharedPreferences("PlayerPrefs", MODE_PRIVATE);
        int loadedScore = prefs.getInt("score", 0);

        new CountDownTimer(30000, 500) {

            @Override
            public void onTick(long millisUntilFinished) {
                mole.beginPopUp(holeGrid);
            }

            @Override
            public void onFinish() {
                if (loadedScore < currentScore.intValue()) {
                    editor.putInt("score", currentScore.intValue());
                    editor.apply();
                }
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
            }

        }.start();

        new CountDownTimer(30000, 1000) {

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
}
