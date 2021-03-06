package frederick.tsundere.evafast;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.view.KeyEvent;

import frederick.tsundere.evafast.listener.OnSwipeTouchListener;

public class ActionActivity extends WearableActivity {
    private BoxInsetLayout mActionContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        mActionContainer = (BoxInsetLayout) findViewById(R.id.container_action);

        mActionContainer.setOnTouchListener(new OnSwipeTouchListener(ActionActivity.this) {
            @Override
            public boolean onSwipeRight() {
                finish();
                return true;
            }

            @Override
            public boolean onSwipeDown() {
                finish();
                return true;
            }
        });

        // Enables Always-on
        setAmbientEnabled();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_NAVIGATE_PREVIOUS:
                finish();
                return true;
            default:
                return super.onKeyDown(keyCode, event);
        }
    }
}
