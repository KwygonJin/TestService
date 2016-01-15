package kwygonjin.com.testservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by KwygonJin on 15.01.2016.
 */
public class CustomReceiver extends BroadcastReceiver {
    private static final int NOTIFY_ID = 102;

    @Override
    public void onReceive(Context context, Intent intent) {
        sendNotif(context);
    }

    private void sendNotif(Context context) {
        Intent notificationIntent = new Intent(context.getApplicationContext(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context.getApplicationContext(),
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Notification.Builder builder = new Notification.Builder(context.getApplicationContext());

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Its your receiver")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("Its your receiver")
                .setContentText("Its your receiver");

        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) context.getApplicationContext()
                .getSystemService(context.getApplicationContext().NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFY_ID, notification);

    }
}
