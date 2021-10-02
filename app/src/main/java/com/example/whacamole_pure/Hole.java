package com.example.whacamole_pure;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Hole extends androidx.appcompat.widget.AppCompatImageView {

    public Hole(Context context) {
        super(context);
        setParams();
    }

    private void setParams() {
        setImageResource(R.drawable.hole);
        setContentDescription("@null");
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        );
        setLayoutParams(layoutParams);
    }
}