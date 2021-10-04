package com.example.whacamole_pure;

import android.text.InputFilter;
import android.text.Spanned;

public class NumericInputFilter implements InputFilter {

    private final int min, max;

    public NumericInputFilter(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public NumericInputFilter(String min, String max) {
        this.min = Integer.parseInt(min);
        this.max = Integer.parseInt(max);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange(min, max, input)) {
                return null;
            }
        } catch (NumberFormatException ignored) {
        }
        return "";
    }

    private boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}
