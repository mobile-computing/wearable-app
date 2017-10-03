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
        mImageViewArrow.setImageDrawable(getDrawable(R.drawable.ic_arrow_noun_816167));

        // Enables Always-on
        setAmbientEnabled();

        mMainContainer.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            @Override
            public boolean onSwipeLeft() {
                Intent actionActivity = new Intent(MainActivity.this, ActionActivity.class);
                startActivity(actionActivity);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                return true;
            }

            @Override
            public boolean onSwipeUp() {
                Intent actionActivity = new Intent(MainActivity.this, ActionActivity.class);
                startActivity(actionActivity);
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                return true;
            }
        });
    }
}
