package e.andressaldana.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static e.andressaldana.notifications.App.CHANNEL_1_ID;

public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    private static final String NOTIFICATION_DELETED_ACTION = "NOTIFICATION_DELETED";
    private static final String NOTIFICATION_ENTERED_ACTION = "NOTIFICATION_ENTERED";
    private TextView jtv;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jtv = findViewById(R.id.xtv);
        jtv.setText("Cuenta :"+i);
        notificationManager = NotificationManagerCompat.from(this);
        //sendOnChannel1();
    }

    public void sendOnChannel1(View v) {
        i++;
        jtv.setText("Cuenta :" + i);
        String title = "Alerta de Notificacion";
        String message = "Notificacion ";
        Intent intent = new Intent(NOTIFICATION_DELETED_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        Intent in = new Intent(NOTIFICATION_ENTERED_ACTION);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, in, 0);
        registerReceiver(receiver, new IntentFilter(NOTIFICATION_DELETED_ACTION));
        registerReceiver(receiver, new IntentFilter(NOTIFICATION_ENTERED_ACTION));

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle(title)
                .setContentText(message + i)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(pi)
                .setDeleteIntent(pendingIntent)
                .build();
        notificationManager.notify(1, notification);
    }

    //caso especial cuando se entra a la app
    public void sendOnChannel1Zero() {
        i=0;
        jtv.setText("Cuenta :" + i);
        String title = "Alerta de Notificacion";
        String message = "Notificacion ";
        Intent intent = new Intent(NOTIFICATION_DELETED_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        Intent in = new Intent(NOTIFICATION_ENTERED_ACTION);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, in, 0);
        registerReceiver(receiver, new IntentFilter(NOTIFICATION_DELETED_ACTION));
        registerReceiver(receiver, new IntentFilter(NOTIFICATION_ENTERED_ACTION));

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle(title)
                .setContentText(message + i)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(pi)
                .setDeleteIntent(pendingIntent)
                .build();
        notificationManager.notify(1, notification);
    }

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(NOTIFICATION_DELETED_ACTION)){
                i = 0;
                jtv.setText("Cuenta :"+i);
            }
            else{
                sendOnChannel1Zero();
                Intent in = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this,0,in,0);
                try {
                    pi.send();
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
            }

            unregisterReceiver(this);
        }
    };
}
