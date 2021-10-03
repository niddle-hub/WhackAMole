package com.example.whacamole_pure;

import android.content.Context;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import java.io.Console;
import java.util.ArrayList;
import java.util.Random;

public class HoleGrid extends GridLayout {

    private final int holeCount;
    private final ArrayList<FrameLayout> places = new ArrayList<>();
    private FrameLayout lastReturnedPlace = null;

    public HoleGrid(Context context) {
        super(context);
        holeCount = 0;
    }

    public HoleGrid(Context context, int columns, int rows) {
        super(context);
        setColumnCount(columns);
        setRowCount(rows);
        setParams();
        holeCount = columns * rows;
        fillGrid();
    }

    private FrameLayout createLayoutContainer() {
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
        if (holeCount > 1) {
            for (int i = 0; i < holeCount; i++) {
                Hole hole = new Hole(getContext());
                FrameLayout container = createLayoutContainer();
                places.add(container);
                container.addView(hole);
                addView(container);
            }
        }
    }

    public FrameLayout pickANewRandomPlace() {
        if (holeCount > 1) {
            FrameLayout currentPlace;
            do {
                currentPlace = places.get(new Random().nextInt(places.size()));
            } while (currentPlace == lastReturnedPlace);
            lastReturnedPlace = currentPlace;
            return currentPlace;
        }
        return null;
    }
}
