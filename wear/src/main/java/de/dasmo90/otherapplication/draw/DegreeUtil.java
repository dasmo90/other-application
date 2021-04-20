package de.dasmo90.otherapplication.draw;

public final class DegreeUtil {

    private DegreeUtil() {
    }

    public static float normalizeAngle(float angle) {
        if (angle < 0) {
            return 360 - Math.abs(angle) % 360;
        }
        return angle % 360;
    }
}
