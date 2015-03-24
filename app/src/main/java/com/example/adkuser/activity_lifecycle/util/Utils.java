package com.example.adkuser.activity_lifecycle.util;

import android.os.Handler;
import android.widget.TextView;

import java.util.List;

/**
 * Created by adkuser on 3/3/15.
 */
public class Utils {
    private static StatusTracker tracker = StatusTracker.getInstance();

    public static void printStatus(final TextView viewMethods, final TextView viewStatus)
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                StringBuilder builder = new StringBuilder();
                List<String> listMethods = tracker.getMethodList();
                for (String method : listMethods)
                    builder.insert(0, method + "\r\n");

                if (viewMethods != null)
                    viewMethods.setText(builder.toString());

                builder = new StringBuilder();
                for (String key : tracker.getKeySet())
                    builder.insert(0, key + ": " +  tracker.getStatus(key)+"\n");
                if(viewStatus != null)
                    viewStatus.setText(builder.toString());
            }
        }, 750);
    }
}
