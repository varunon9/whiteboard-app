package me.varunon9.whiteboard;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by varunkumar on 11/6/18.
 */

public class Utils {
    Context context;

    Utils(Context context) {
        super();
        this.context = context;
    }

    public boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service
                : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
