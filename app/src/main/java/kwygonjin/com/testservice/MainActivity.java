package kwygonjin.com.testservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    public final static String BROADCAST_ACTION = "custom-event-name";
    private static final int NOTIFY_ID = 103;
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            sendNotif(getApplicationContext());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter(BROADCAST_ACTION));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // дерегистрируем (выключаем) BroadcastReceiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
    }

    public void onClickStart(View view) {
        Intent intent = new Intent(this, CustomService.class);
        startService(intent);
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
