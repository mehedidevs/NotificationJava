package com.cit.j_notif_es2203;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.res.ResourcesCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyService extends FirebaseMessagingService {
    public MyService() {
    }

    NotificationManager manager;
    NotificationCompat.Builder notification;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        String title = message.getNotification().getTitle();
        String body = message.getNotification().getBody();
        createNotification(title, body);

        Log.i("TAG", "onMessageReceived: " + title + " body : " + body);

        super.onMessageReceived(message);
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }


    private void createNotification(String title, String body) {
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent ntfIntent = new Intent(getApplicationContext(), NotificationActivity.class);
        ntfIntent.putExtra("msg", title);
        ntfIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 101, ntfIntent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.BigPictureStyle bigPictureStyle = new
                NotificationCompat.BigPictureStyle()
                .bigLargeIcon(getMyBitmap(R.drawable.notificationicon))
                .bigPicture(getMyBitmap(R.drawable.image_lage))
                .setBigContentTitle(title)
                .setSummaryText(body);


//        NotificationCompat.InboxStyle inboxStyle = new
//                NotificationCompat.InboxStyle()
//                .addLine(msg)
//                .addLine(msg)
//                .addLine(msg)
//                .addLine(msg)
//                .addLine(msg)
//                .addLine(msg)
//                .addLine(msg)
//                .addLine(msg)
//                .addLine(msg)
//                .addLine(msg)
//                .addLine(msg)
//                .setBigContentTitle("Big Content Title")
//                .setSummaryText("big Content Summary");


        notification = new NotificationCompat.Builder(this, "MyNotification")
                .setContentTitle("just a title")
                .setContentText("just a body")
                .setLargeIcon(getMyBitmap(R.drawable.notificationicon))
                .setStyle(bigPictureStyle)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher_round);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(new NotificationChannel("MyNotification", "just name", NotificationManager.IMPORTANCE_HIGH));
        }

        showNotification();

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