package com.example.whacamole_pure;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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
        FrameLayout currentHolePlace = holeGrid.pickANewRandomPlace();
        if (currentHolePlace != null) {
            if(getParent() != null) {
                ((ViewGroup) getParent()).removeView(this);
            }
            if (currentHolePlace.getChildAt(this.getId()) != null) {
                currentHolePlace.removeView(this);
            }
            currentHolePlace.addView(this);
        }
    }
}
