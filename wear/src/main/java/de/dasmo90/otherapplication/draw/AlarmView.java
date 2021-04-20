package de.dasmo90.otherapplication.draw;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import java.util.LinkedList;
import java.util.stream.IntStream;

import androidx.annotation.Nullable;

public class AlarmView extends DrawView {

    public static final Color BLACK = Color.valueOf(Color.BLACK);
    public static final Color WHITE = Color.valueOf(Color.WHITE);

    public AlarmView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        final LinkedList<AbstractDrawObject> objects = new LinkedList<>();
        objects.add(new Background(BLACK));
        IntStream.range(0, 12).boxed()
                .map(i -> new RingButton(
                        Integer.toString(i * 5),
                        i,
                        RingButton.TWELFTH,
                        - 7 * RingButton.TWELFTH / 2,
                        0.5f,
                        BLACK,
                        WHITE))
                .forEach(objects::add);
        initialize(objects);
    }
}
