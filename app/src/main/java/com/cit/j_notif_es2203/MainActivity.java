package com.cit.j_notif_es2203;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.res.ResourcesCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    NotificationManager manager;
    NotificationCompat.Builder notification;

    String msg = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotification();


        findViewById(R.id.showNotification).setOnClickListener(v -> {
            showNotification();

        });

    }

    private void createNotification() {
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent ntfIntent = new Intent(getApplicationContext(), NotificationActivity.class);
        ntfIntent.putExtra("msg", msg);
        ntfIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 101, ntfIntent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.BigPictureStyle bigPictureStyle = new
                NotificationCompat.BigPictureStyle()
                .bigLargeIcon(getMyBitmap(R.drawable.notificationicon))
                .bigPicture(getMyBitmap(R.drawable.image_lage))
                .setBigContentTitle("Big Content Title")
                .setSummaryText("big Content Summary");


        NotificationCompat.InboxStyle inboxStyle = new
                NotificationCompat.InboxStyle()
                .addLine(msg)
                .addLine(msg)
                .addLine(msg)
                .addLine(msg)
                .addLine(msg)
                .addLine(msg)
                .addLine(msg)
                .addLine(msg)
                .addLine(msg)
                .addLine(msg)
                .addLine(msg)
                .setBigContentTitle("Big Content Title")
                .setSummaryText("big Content Summary");


        notification = new NotificationCompat.Builder(this, "MyNotification")
                .setContentTitle("just a title")
                .setContentText("just a body")
                .setLargeIcon(getMyBitmap(R.drawable.notificationicon))
                .setStyle(inboxStyle)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher_round);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(new NotificationChannel("MyNotification", "just name", NotificationManager.IMPORTANCE_HIGH));
        }

    }

    private Bitmap getMyBitmap(int imgres) {
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), imgres, null);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        return bitmapDrawable.getBitmap();
    }

    private void showNotification() {

        int id = (int) System.currentTimeMillis();
        manager.notify(id, notification.build());


    }
}