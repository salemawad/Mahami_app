//package com.example.final_projects.Alarm;
//
//import android.annotation.TargetApi;
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.ContextWrapper;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Build;
//import android.os.Bundle;
//import android.text.NoCopySpan;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.Window;
//import android.widget.EditText;
//
//import androidx.annotation.Nullable;
//import androidx.annotation.RequiresApi;
//import androidx.core.app.NotificationCompat;
//
//import com.example.final_projects.List_Taksk;
//import com.example.final_projects.New_Task;
//import com.example.final_projects.R;
//
//public class NotificayionHelper extends ContextWrapper {
//    public static final String channelID = "channelID";
//    public static final String channelName = "Channel Name";
//
//    private NotificationManager mManager;
//
//    public NotificayionHelper(Context base) {
//        super(base);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            createChannel();
//        }
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @TargetApi(Build.VERSION_CODES.O)
//    private void createChannel() {
//        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
//
//        getManager().createNotificationChannel(channel);
//    }
//
//    public NotificationManager getManager() {
//        if (mManager == null) {
//            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        }
//        return mManager;
//    }
//
//    public NotificationCompat.Builder getChannelNotification() {
//
//        Intent onclickactivity = new Intent(this, List_Taksk.class);
//        PendingIntent content = PendingIntent.getActivity(this,0,onclickactivity,0);
//        return new NotificationCompat.Builder(getApplicationContext(), channelID)
//                .setContentTitle("صلي على النبي ")
//                .setContentText("اللهم صلي على سيدنا محمد وال سيدنا محمد  ")
//                .setSmallIcon(R.drawable.notfication)
//                .setColor(Color.BLUE)
//                .setContentIntent(content);
//
//    }
//}
