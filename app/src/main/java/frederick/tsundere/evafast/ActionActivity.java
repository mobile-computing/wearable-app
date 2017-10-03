package frederick.tsundere.evafast;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.widget.Toast;

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
            public boolean onSwipeDown() {
                Toast.makeText(ActionActivity.this, "Triggered", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            }
        });

        // Enables Always-on
        setAmbientEnabled();
    }
}
