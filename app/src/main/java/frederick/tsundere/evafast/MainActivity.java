package frederick.tsundere.evafast;

import android.content.Intent;
import android.os.Bundle;
import android.support.wear.widget.BoxInsetLayout;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import frederick.tsundere.evafast.listener.OnSwipeTouchListener;

public class MainActivity extends WearableActivity {
    private static final SimpleDateFormat AMBIENT_DATE_FORMAT =
            new SimpleDateFormat("HH:mm", Locale.US);

    private BoxInsetLayout mContainerView;
    private TextView mTextView;
    private TextView mClockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        mContainerView = (BoxInsetLayout) findViewById(R.id.container);
        mTextView = (TextView) findViewById(R.id.text);
        mClockView = (TextView) findViewById(R.id.clock);

        mContainerView.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            @Override
            public boolean onSwipeLeft() {
                Intent actionActivityIntent = new Intent(MainActivity.this, ActionActivity.class);
                startActivity(actionActivityIntent);
                return true;
            }

            @Override
            public boolean onSwipeUp() {
                Intent actionActivityIntent = new Intent(MainActivity.this, ActionActivity.class);
                startActivity(actionActivityIntent);
                return true;
            }
        });
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    private void updateDisplay() {
        if (isAmbient()) {
            mContainerView.setBackgroundColor(getColor(R.color.black));
            mTextView.setTextColor(getColor(R.color.white));
            mClockView.setVisibility(View.VISIBLE);

            mClockView.setText(AMBIENT_DATE_FORMAT.format(new Date()));
        } else {
            mContainerView.setBackground(null);
            mTextView.setTextColor(getColor(R.color.black));
            mClockView.setVisibility(View.GONE);
        }
    }
}
