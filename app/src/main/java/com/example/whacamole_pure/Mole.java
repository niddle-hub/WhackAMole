package com.example.whacamole_pure;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class Mole extends androidx.appcompat.widget.AppCompatImageView {

    public Mole(Context context) {
        super(context);
        setParams();
    }

    private void setParams() {
        setClickable(true);
        setImageResource(R.drawable.mole);
        setContentDescription("@null");
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.gravity = Gravity.CENTER;
        setLayoutParams(layoutParams);
    }

    public void beginPopUp(HoleGrid holeGrid) {
        FrameLayout currentHolePlace = holeGrid.getRandomHole();
        if (currentHolePlace != null) {
            if(getParent() != null) {
                ((ViewGroup) getParent()).removeView(this);
            }
            currentHolePlace.addView(this);
        }
    }
}
