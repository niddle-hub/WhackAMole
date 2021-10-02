package com.example.whacamole_pure;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import java.util.Random;

public class HoleGrid extends GridLayout {

    private final int holeCount;


    public HoleGrid(Context context) {
        super(context);
        holeCount = 0;
    }

    public HoleGrid(Context context, int columns, int rows) {
        super(context);
        setColumnCount(columns);
        setRowCount(rows);
        setParams();
        holeCount = getColumnCount() * getRowCount();
        fillGrid();
    }

    private FrameLayout createInsideLayout() {
        FrameLayout frameLayout = new FrameLayout(getContext());
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        layoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        frameLayout.setLayoutParams(layoutParams);

        return frameLayout;
    }

    private void setParams() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        setLayoutParams(layoutParams);
    }

    private void fillGrid() {
        for (int i = 0; i < holeCount; i++) {
            Hole hole = new Hole(getContext());
            FrameLayout insideLayout = createInsideLayout();
            insideLayout.addView(hole);
            addView(insideLayout);
        }
    }

    public FrameLayout getRandomHole() {
        if (holeCount > 0) {
            return (FrameLayout) getChildAt(new Random().nextInt(holeCount));
        }
        return null;
    }

}
