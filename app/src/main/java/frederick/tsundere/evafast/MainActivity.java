package frederick.tsundere.evafast;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import frederick.tsundere.evafast.listener.OnSwipeTouchListener;

public class MainActivity extends WearableActivity implements SensorEventListener {
    private static SensorManager sensorManager;
    private static Sensor heartRateSensor;

    private BoxInsetLayout mMainContainer;
    private FrameLayout mFrameLayoutMain;
    private ImageView mImageViewHeart;
    private ImageView mImageViewArrow;
    private ImageView mImageViewExtinguisher;
    private ImageView mImageViewHose;
    private TextView mTextViewRemain;
    private TextView mTextViewHeart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainContainer = (BoxInsetLayout) findViewById(R.id.container_main);
        mFrameLayoutMain = (FrameLayout) findViewById(R.id.framelayout_main);
        mImageViewHeart = (ImageView) findViewById(R.id.imageview_heart);
        mImageViewArrow = (ImageView) findViewById(R.id.imageview_arrow);
        mImageViewExtinguisher = (ImageView) findViewById(R.id.imageview_extinguisher);
        mImageViewHose = (ImageView) findViewById(R.id.imageview_hose);
        mTextViewRemain = (TextView) findViewById(R.id.textview_remain);
        mTextViewHeart = (TextView) findViewById(R.id.textview_heart);

        // Enables Always-on
        setAmbientEnabled();

        mImageViewHeart.setImageDrawable(getDrawable(R.drawable.ic_heart_red_noun_625027_ec5d57));
        mTextViewHeart.setText("95");
        mImageViewArrow.setImageDrawable(getDrawable(R.drawable.ic_arrow_noun_816167));
        mImageViewExtinguisher.setImageDrawable(getDrawable(R.drawable.ic_fire_extinguisher_green_noun_656279_70c041));
        mImageViewHose.setImageDrawable(getDrawable(R.drawable.ic_fire_hose_black_noun_656280));
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

        if (sensorManager == null)
            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (heartRateSensor == null)
            heartRateSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        if (heartRateSensor != null) {
            sensorManager.registerListener(MainActivity.this, heartRateSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(MainActivity.this, "Heart rate sensor not found", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        sensorManager.unregisterListener(MainActivity.this);
        super.onDestroy();
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


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == heartRateSensor.getType()) {
            mTextViewHeart.setText(String.format("%d", (int) event.values[0]));
            if (event.values[0] > 50) {
                mImageViewHeart.setImageDrawable(getDrawable(R.drawable.ic_heart_red_noun_625027_ec5d57));
            } else {
                mImageViewHeart.setImageDrawable(getDrawable(R.drawable.ic_heart_black_noun_625028));
            }
        }
    }
}
