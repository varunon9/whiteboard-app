package me.varunon9.whiteboard;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class WhiteboardService extends Service {
    private static final String LOG_TAG = "WhiteboardService";

    public WhiteboardService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();

        if (action.equals(Constants.Actions.STARTFOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "Received Start Foreground Intent ");
            startForegroundService();
            Toast.makeText(this, "Notification started!",
                    Toast.LENGTH_SHORT).show();
        } else if (action.equals(Constants.Actions.EDIT_TODO_ACTION)) {
            Log.i(LOG_TAG, "Received Edit TODO Intent ");
            Toast.makeText(this, "Write something on your Whiteboard!",
                    Toast.LENGTH_SHORT).show();
        } else if (action.equals(Constants.Actions.STOPFOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "Received Stop Foreground Intent ");
            stopForeground(true);
            stopSelf();
            Toast.makeText(this, "WhiteboardService Stopping!",
                    Toast.LENGTH_SHORT).show();
        }
        return START_STICKY;
    }

    private void startForegroundService() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setAction(Constants.Actions.MAIN_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle(Constants.NOTIFICATION_TITLE)
                .setTicker(Constants.NOTIFICATION_TITLE)
                .setContentText(Constants.NOTIFICATION_TEXT)
                .setSmallIcon(R.mipmap.ic_launcher)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .build();

        startForeground(Constants.NOTIFICATION_ID, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "In onDestroy");
        Toast.makeText(this, "Notification Stopped!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Used only in case if services are bound (Bound Services).
        return null;
    }
}
