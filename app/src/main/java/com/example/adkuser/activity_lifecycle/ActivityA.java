package com.example.adkuser.activity_lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adkuser.activity_lifecycle.util.StatusTracker;
import com.example.adkuser.activity_lifecycle.util.Utils;

/**
 * Created by adkuser on 3/3/15.
 */
public class ActivityA extends Activity {
    private Button startB;
    private Button startC;
    dfdfdprivate Button finishA;
    private Button startDialog;

    private View.OnClickListener listener;

    private String mActivityName;
    private TextView mStatusView;
    private TextView mStatusAllView;
    private StatusTracker tracker = StatusTracker.getInstance();

    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_a);
        this.initListener();
        this.startB = (Button)findViewById(R.id.btn_start_b);
        this.startC = (Button)findViewById(R.id.btn_start_c);
        this.finishA = (Button)findViewById(R.id.btn_finish_a);
        this.startDialog = (Button)findViewById(R.id.btn_start_dialog);

        this.startB.setOnClickListener(listener);
        this.startC.setOnClickListener(listener);
        this.finishA.setOnClickListener(listener);
        this.startDialog.setOnClickListener(listener);

        this.mActivityName = getString(R.string.activity_a_label);
        this.mStatusView = (TextView)findViewById(R.id.status_view_a);
        this.mStatusAllView = (TextView)findViewById(R.id.status_view_all_a);

        this.tracker.setStatus(mActivityName, getString(R.string.on_create));
        Utils.printStatus(mStatusView, mStatusAllView);
    }

    private void initListener()
    {
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId())
                {
                    case R.id.btn_start_b:
                        startActivity(new Intent(ActivityA.this, ActivityB.class));
                        break;
                    case R.id.btn_start_c:
                        startActivity(new Intent(ActivityA.this, ActivityC.class));
                        break;
                    case R.id.btn_finish_a:
                        ActivityA.this.finish();
                        break;
                    case R.id.btn_start_dialog:
                        startActivity(new Intent(ActivityA.this, DialogActivity.class));
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
