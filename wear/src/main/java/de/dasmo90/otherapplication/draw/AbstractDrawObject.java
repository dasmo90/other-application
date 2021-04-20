package de.dasmo90.otherapplication.draw;

import android.graphics.Canvas;

public abstract class AbstractDrawObject {

    public abstract void initialize();

    public abstract void draw(Canvas canvas, float radius);
}
