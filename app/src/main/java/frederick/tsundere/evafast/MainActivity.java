package frederick.tsundere.evafast;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.widget.ImageView;

import frederick.tsundere.evafast.listener.OnSwipeTouchListener;

public class MainActivity extends WearableActivity {
    private BoxInsetLayout mMainContainer;
    private ImageView mImageViewArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainContainer = (BoxInsetLayout) findViewById(R.id.container_main);
        mImageViewArrow = (ImageView) findViewById(R.id.imageview_arrow);

        // Enables Always-on
        setAmbientEnabled();

        mMainContainer.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            private void startActionActivity() {
                Intent actionActivity = new Intent(MainActivity.this, ActionActivity.class);
                startActivity(actionActivity);
            }

            @Override
            public boolean onSwipeLeft() {
                startActionActivity();
                return true;
            }

            @Override
            public boolean onSwipeUp() {
                startActionActivity();
                return true;
            }
        });
    }
}
