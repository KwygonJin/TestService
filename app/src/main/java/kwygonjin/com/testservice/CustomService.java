package kwygonjin.com.testservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by KwygonJin on 15.01.2016.
 */
public class CustomService extends Service {
    NotificationManager nm;
    private static final int NOTIFY_ID = 101;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sendNotif();

        Intent intentBroadcast = new Intent(MainActivity.BROADCAST_ACTION);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intentBroadcast);

        return super.onStartCommand(intent, flags, startId);
    }

    private void sendNotif() {
        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(),
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Notification.Builder builder = new Notification.Builder(getApplicationContext());

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Its your service")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Its your service");

        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) getApplicationContext()
                .getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFY_ID, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
