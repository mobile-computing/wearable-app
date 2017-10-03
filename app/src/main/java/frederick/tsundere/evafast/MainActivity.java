package frederick.tsundere.evafast;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import frederick.tsundere.evafast.listener.OnSwipeTouchListener;

public class MainActivity extends WearableActivity {
    private BoxInsetLayout mMainContainer;
    private FrameLayout mFrameLayoutMain;
    private ImageView mImageViewHeart;
    private ImageView mImageViewArrow;
    private TextView mTextViewRemain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainContainer = (BoxInsetLayout) findViewById(R.id.container_main);
        mFrameLayoutMain = (FrameLayout) findViewById(R.id.framelayout_main);
        mImageViewHeart = (ImageView) findViewById(R.id.imageview_heart);
        mImageViewArrow = (ImageView) findViewById(R.id.imageview_arrow);
        mTextViewRemain = (TextView) findViewById(R.id.textview_remain);

        // Enables Always-on
        setAmbientEnabled();

        mImageViewHeart.setImageDrawable(getDrawable(R.drawable.ic_heart_noun_625027_ec5d57));
        mImageViewArrow.setImageDrawable(getDrawable(R.drawable.ic_arrow_noun_816167));
        mTextViewRemain.setText("320 metres remaining\nETA: 7 minutes");
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_NAVIGATE_NEXT:
                Intent actionActivity = new Intent(MainActivity.this, ActionActivity.class);
                startActivity(actionActivity);
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                return true;
            default:
                return super.onKeyDown(keyCode, event);
        }
    }
}
