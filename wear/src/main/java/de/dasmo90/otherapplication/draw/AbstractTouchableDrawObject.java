package de.dasmo90.otherapplication.draw;

import android.util.Log;
import android.view.MotionEvent;

public abstract class AbstractTouchableDrawObject extends AbstractDrawObject {

    protected boolean pressed = false;

    public abstract boolean isInside(float x, float y, float radius);

    public boolean onTouch(MotionEvent event, float radius) {
        final float x = event.getAxisValue(MotionEvent.AXIS_X);
        final float y = event.getAxisValue(MotionEvent.AXIS_Y);
        if (isInside(x, y, radius)) {
            if (MotionEvent.ACTION_DOWN == event.getAction()) {
                pressed = true;
                return true;
            }
            if (MotionEvent.ACTION_UP == event.getAction()
                    || MotionEvent.ACTION_CANCEL == event.getAction()) {
                pressed = false;
                return true;
            }
        } else {
            if (pressed) {
                pressed = false;
                return true;
            }
        }
        return false;
    }
}
