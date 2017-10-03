package frederick.tsundere.evafast.listener;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnSwipeTouchListener implements View.OnTouchListener {
    private final GestureDetector gestureDetector;

    public OnSwipeTouchListener(Context context) {
        this.gestureDetector = new GestureDetector(context, new GestureListener());
    }

    public boolean onSwipeLeft() {
        return false;
    }

    public boolean onSwipeRight() {
        return false;
    }

    public boolean onSwipeUp() {
        return false;
    }

    public boolean onSwipeDown() {
        return false;
    }

    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_DISTANCE_THRESHOLD = 50;
        private static final int SWIPE_VELOCITY_THRESHOLD = 50;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            if (Math.abs(distanceX) > Math.abs(distanceY)
                    && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD
                    && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (distanceX > 0)
                    return onSwipeRight() || super.onFling(e1, e2, velocityX, velocityY);
                else
                    return onSwipeLeft() || super.onFling(e1, e2, velocityX, velocityY);

            } else if (Math.abs(distanceY) > Math.abs(distanceX)
                    && Math.abs(distanceY) > SWIPE_DISTANCE_THRESHOLD
                    && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (distanceY > 0)
                    return onSwipeDown() || super.onFling(e1, e2, velocityX, velocityY);
                else
                    return onSwipeUp() || super.onFling(e1, e2, velocityX, velocityY);

            }
            return false;
        }
    }
}