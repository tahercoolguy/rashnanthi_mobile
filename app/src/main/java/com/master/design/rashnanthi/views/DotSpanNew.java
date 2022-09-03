package com.master.design.rashnanthi.views;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;


public class DotSpanNew implements LineBackgroundSpan {

    /**
     * Default radius used
     */
    public static final float DEFAULT_RADIUS = 5;

    private final float radius;
    private final int color;

    /**
     * Create a span to draw a dot using default radius and color
     *
     * @see #DotSpanNew(float, int)
     * @see #DEFAULT_RADIUS
     */
    public DotSpanNew() {
        this.radius = DEFAULT_RADIUS;
        this.color = 0;
    }

    /**
     * Create a span to draw a dot using a specified color
     *
     * @param color color of the dot
     * @see #DotSpanNew(float, int)
     * @see #DEFAULT_RADIUS
     */
    public DotSpanNew(int color) {
        this.radius = DEFAULT_RADIUS;
        this.color = color;
    }

    /**
     * Create a span to draw a dot using a specified radius
     *
     * @param radius radius for the dot
     * @see #DotSpanNew(float, int)
     */
    public DotSpanNew(float radius) {
        this.radius = radius;
        this.color = 0;
    }

    /**
     * Create a span to draw a dot using a specified radius and color
     *
     * @param radius radius for the dot
     * @param color  color of the dot
     */
    public DotSpanNew(float radius, int color) {
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void drawBackground(
            Canvas canvas, Paint paint,
            int left, int right, int top, int baseline, int bottom,
            CharSequence charSequence,
            int start, int end, int lineNum
    ) {
        int oldColor = paint.getColor();
        if (color != 0) {
            paint.setColor(color);
        }
        canvas.drawCircle(left + right / 2, left + right / 2 + radius, radius, paint);
        paint.setColor(oldColor);
    }
}


