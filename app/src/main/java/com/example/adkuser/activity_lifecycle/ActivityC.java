package com.example.adkuser.activity_lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adkuser.activity_lifecycle.util.StatusTracker;
import com.example.adkuser.activity_lifecycle.util.Utils;

/**
 * Created by adkuser on 3/3/15.
 */
public class ActivityC extends Activity {
    private Button startA;
    private Button startB;
    private Button finishC;
    private Button startDialog;

    private String mActivityName;
    private TextView mStatusView;
    private TextView mStatusAllView;
    private StatusTracker tracker = StatusTracker.getInstance();

    private View.OnClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        initListener();

        startA = (Button)findViewById(R.id.btn_start_a);
        startB = (Button)findViewById(R.id.btn_start_b);
        finishC = (Button)findViewById(R.id.btn_finish_c);
        startDialog = (Button)findViewById(R.id.btn_start_dialog);

        startA.setOnClickListener(listener);
        startB.setOnClickListener(listener);
        finishC.setOnClickListener(listener);
        startDialog.setOnClickListener(listener);

        mActivityName = getString(R.string.activity_c_label);
        mStatusView = (TextView)findViewById(R.id.status_view_c);
        mStatusAllView = (TextView)findViewById(R.id.status_view_all_c);

        tracker.setStatus(mActivityName, getString(R.string.on_create));
        Utils.printStatus(mStatusView, mStatusAllView);
    }

    private void initListener() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId())
                {
                    case R.id.btn_start_a:
                        startActivity(new Intent(ActivityC.this, ActivityA.class));
                        break;
                    case R.id.btn_start_b:
                        startActivity(new Intent(ActivityC.this, ActivityB.class));
                        break;
                    case R.id.btn_finish_c:
                        finish();
                        break;
                    case R.id.btn_start_dialog:
                        startActivity(new Intent(ActivityC.this, DialogActivity.class));
                        break;
                }
            }
        };
    }

    @Override
    public void onStart()
    {
        super.onStart();
        tracker.setStatus(mActivityName, getString(R.string.on_start));
        Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        tracker.setStatus(mActivityName, getString(R.string.on_stop));
        Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        tracker.setStatus(mActivityName, getString(R.string.on_pause));
        Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        tracker.setStatus(mActivityName, getString(R.string.on_destroy));
        tracker.clear();
    }
}
