package de.dasmo90.otherapplication.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.Nullable;

public class DrawView extends View {

    private final List<AbstractDrawObject> drawObjects = new LinkedList<>();

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    protected final void initialize(List<AbstractDrawObject> drawObjects) {
        this.drawObjects.addAll(drawObjects);
        this.drawObjects.forEach(AbstractDrawObject::initialize);
        setOnTouchListener((v, event) -> {
            final float radius = measureRadius();
            boolean touched = DrawView.this.drawObjects.stream()
                    .filter(AbstractTouchableDrawObject.class::isInstance)
                    .map(AbstractTouchableDrawObject.class::cast)
                    .map(touchable -> touchable.onTouch(event, radius))
                    .reduce(false, (a, b) -> a || b);
            if (touched) {
                DrawView.this.invalidate();
            }
            if (MotionEvent.ACTION_DOWN == event.getAction()) {
                touched |= DrawView.this.performClick();
            }
            return touched;
        });
    }

    @Override
    public void onDraw(Canvas canvas) {
        final float radius = measureRadius();
        this.drawObjects.forEach(object -> object.draw(canvas, radius));
    }

    private float measureRadius() {
        return (float) Math.min(getMeasuredWidth(), getMeasuredHeight()) / 2;
    }
}
