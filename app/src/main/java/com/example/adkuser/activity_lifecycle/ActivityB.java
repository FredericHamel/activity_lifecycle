package com.example.adkuser.activity_lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.adkuser.activity_lifecycle.util.StatusTracker;
import com.example.adkuser.activity_lifecycle.util.Utils;

/**
 * Created by adkuser on 3/3/15.
 */
public class ActivityB extends Activity {
    private android.widget.Button startingA;
    private android.widget.Button startC;
    private android.widget.Button finishB;
    private android.widget.Button startDialog;

    private String mActivityName;
    private TextView mStatusView;
    private TextView mStatusAllView;
    private StatusTracker tracker = StatusTracker.getInstance();

    private View.OnClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        initListener();
        startingA = (android.widget.Button)findViewById(R.id.btn_start_a);
        startC = (android.widget.Button)findViewById(R.id.btn_start_c);
        finishB = (android.widget.Button)findViewById(R.id.btn_finish_b);
        startDialog = (android.widget.Button)findViewById(R.id.btn_start_dialog);

        startingA.setOnClickListener(listener);
        startC.setOnClickListener(listener);
        finishB.setOnClickListener(listener);
        startDialog.setOnClickListener(listener);

        mActivityName = getString(R.string.activity_b_label);
        mStatusView = (TextView)findViewById(R.id.status_view_b);
        mStatusAllView = (TextView)findViewById(R.id.status_view_all_b);
        tracker.setStatus(mActivityName, getString(R.string.on_create));
        Utils.printStatus(mStatusView, mStatusAllView);
    }

    private void initListener()
    {
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId())
                {
                    case R.id.btn_start_a:
                        startActivity(new Intent(ActivityB.this, ActivityA.class));
                        break;
                    case R.id.btn_start_c:
                        startActivity(new Intent(ActivityB.this, ActivityC.class));
                        break;
                    case R.id.btn_finish_b:
                        finish();
                        break;
                    case R.id.btn_start_dialog:
                        startActivity(new Intent(ActivityB.this, DialogActivity.class));
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
