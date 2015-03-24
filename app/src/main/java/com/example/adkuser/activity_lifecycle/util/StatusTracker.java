package com.example.adkuser.activity_lifecycle.util;

import java.util.*;
/**
 * Created by adkuser on 3/3/15.
 */
public class StatusTracker {
    public static final String STATUS_SUFFIX = "ed";

    private Map<String, String> mStatusMap;
    private List<String> methodList;
    private static StatusTracker ourInstance = new StatusTracker();

    public static StatusTracker getInstance()
    {
        return ourInstance;
    }

    private StatusTracker()
    {
        mStatusMap = new LinkedHashMap<>();
        methodList = new ArrayList<>();
    }

    public List<String> getMethodList()
    {
        return methodList;
    }

    public void clear()
    {
        mStatusMap.clear();
        methodList.clear();
    }

    public void setStatus(String activityName, String status)
    {
        methodList.add(activityName + "." + status + "();");
        if(mStatusMap.containsKey(activityName)) mStatusMap.remove(activityName);
        mStatusMap.put(activityName, status);
    }

    public String getStatus(String activityName)
    {
        String status = mStatusMap.get(activityName);
        status = status.substring(2, status.length());
        if(status.endsWith("e"))
            status = status.substring(0, status.length() - 1);
        if(status.endsWith("p"))
            status = status + "p";
        status = status + StatusTracker.STATUS_SUFFIX;
        return status;
    }

    public Set<String> getKeySet()
    {
        return mStatusMap.keySet();
    }

}
