package de.dasmo90.otherapplication.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import static de.dasmo90.otherapplication.draw.DegreeUtil.normalizeAngle;

public class RingButton extends AbstractTouchableDrawObject {

    public static final float TWELFTH = (float) 360 / 12;

    private final String text;
    private final int index;
    private final float sizeInDegree;
    private final float offsetInDegree;
    private final float thickness;
    private final Color background;
    private final Color foreground;

    private final Paint backgroundPaint = new Paint();
    private final Paint foregroundPaint = new Paint();

    public RingButton(String text, int index, float sizeInDegree, float thickness, Color background, Color foreground) {
        this(text, index, sizeInDegree, 0, thickness, background, foreground);
    }

    public RingButton(String text, int index, float sizeInDegree, float offsetInDegree, float thickness, Color background, Color foreground) {
        this.text = text;
        this.index = index;
        this.sizeInDegree = sizeInDegree;
        this.offsetInDegree = normalizeAngle(offsetInDegree);
        this.thickness = thickness;
        this.background = background;
        this.foreground = foreground;
    }

    @Override
    public void initialize() {
        initColor(foregroundPaint, foreground.toArgb());
        initColor(backgroundPaint, background.toArgb());
    }

    private void initColor(Paint paint, int color) {
        paint.setColor(color);
        paint.setTextSize(24);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
    }

    @Override
    public boolean isInside(float x, float y, float radius) {
        final double distance = Math.sqrt(Math.pow(x - radius, 2) + Math.pow(y - radius, 2));
        final double rawAngle = Math.atan((y - radius) / (x - radius)) / Math.PI * 180;
        final double normalizedAngle = rawAngle > 0 ? rawAngle : 180 + rawAngle;
        final double angle = (y - radius) > 0 ? normalizedAngle : 180 + normalizedAngle;
        final float startAngle = normalizeAngle(offsetInDegree + index * sizeInDegree);
        final float endAngle = startAngle + sizeInDegree;
        final float transformedAngle = (float) (endAngle - 360 > angle ? angle + 360 : angle);
        return distance < radius
                && distance > radius * thickness
                && transformedAngle > startAngle
                && transformedAngle < endAngle;
    }

    @Override
    public void draw(Canvas canvas, float radius) {
        Paint back = pressed ? foregroundPaint : backgroundPaint;
        Paint fore = pressed ? backgroundPaint : foregroundPaint;
        back.setStrokeWidth(2 * radius * thickness);
        back.setStyle(Paint.Style.STROKE);
        fore.setStrokeWidth(1);
        fore.setStyle(Paint.Style.FILL_AND_STROKE);
        final float startAngle = offsetInDegree + index * sizeInDegree;
        canvas.drawArc(0, 0, 2 * radius, 2 * radius,
                startAngle,
                sizeInDegree,
                false,
                back);
        final float angle = startAngle + sizeInDegree / 2;
        final float textDistance = (radius * thickness + radius) / 2;
        final float x = (float) Math.cos(angle / 180 * Math.PI) * textDistance;
        final float y = (float) Math.sin(angle / 180 * Math.PI) * textDistance;
        final float textYAdjustment = (fore.descent() + fore.ascent()) / 2;
        canvas.drawText(text, x + radius, y + radius - textYAdjustment, fore);
    }
}
