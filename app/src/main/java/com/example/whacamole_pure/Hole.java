package com.example.whacamole_pure;

import android.content.Context;
import android.widget.FrameLayout;

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