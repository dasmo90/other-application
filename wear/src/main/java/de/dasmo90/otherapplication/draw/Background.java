package de.dasmo90.otherapplication.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Background extends AbstractDrawObject {

    private final Color background;
    private final Paint backgroundPaint = new Paint();

    public Background(Color background) {
        this.background = background;
    }

    @Override
    public void initialize() {
        backgroundPaint.setColor(background.toArgb());
    }

    @Override
    public void draw(Canvas canvas, float radius) {
        canvas.drawRect(0, 0, 2 * radius, 2 * radius, backgroundPaint);
    }
}
