package com.example.antenatal;
import android.app.Notification;
import android.app.NotificationChannel ;
import android.app.NotificationManager ;
import android.app.PendingIntent ;
import android.app.Service ;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent ;
import android.database.Cursor;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder ;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class ReminderService  extends BroadcastReceiver {
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    private final static String GROUP_KEY_WORK_EMAIL = "com.example.antenatal";

    public ReminderService () {

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String dateSchedule = "",doctorSchedule = "",purposeSchedule="", scheduleid="";

        dbHelper  dbHelper = new dbHelper(context);
        Cursor cursor = dbHelper.getAllPatientPendingSchedule();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            String purpose = cursor.getString(cursor.getColumnIndex(dbColumnList.userSchedule.COLUMN_PURPOSE));
            purpose = (purpose.length() > 300) ? TextUtils.substring(purpose, 0, 300).concat("...") : purpose;

            dateSchedule = cursor.getString(cursor.getColumnIndex(dbColumnList.userSchedule.COLUMN_SCHEDULEDATE)) +
                    " - "+ cursor.getString(cursor.getColumnIndex(dbColumnList.userSchedule.COLUMN_SCHEDULETIME));
            doctorSchedule = cursor.getString(cursor.getColumnIndex(dbColumnList.userSchedule.COLUMN_DOCTYPE)) +  " " +
                    cursor.getString(cursor.getColumnIndex(dbColumnList.userSchedule.COLUMN_SCHEDULEDOCTOR));
            purposeSchedule = "Purpose : "+ purpose;

            scheduleid =  cursor.getString(cursor.getColumnIndex(dbColumnList.userSchedule.COLUMN_HID));

            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Intent notificationIntent = new Intent(context ,  readAppointment.class ) ;
            notificationIntent.putExtra( "NEWSID" , scheduleid ) ;
//        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP) ;
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity ( context, 0 , notificationIntent , 0 ) ;

            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE ) ;

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context , default_notification_channel_id ) ;
            mBuilder.setContentTitle( "Antenatal Schedule" ) ;
            mBuilder.setContentIntent(pendingIntent) ;
            mBuilder.setContentText( dateSchedule + System.getProperty("line.separator") + doctorSchedule) ;
            mBuilder.setSmallIcon(R.mipmap.ic_launcher_round ) ;
            mBuilder.setLights(Color.RED, 3000, 3000);
            mBuilder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000 });
            mBuilder.setTicker("Reminder For Hospital Appointment");
            mBuilder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            mBuilder.setSound(sound);
            mBuilder.setGroup(GROUP_KEY_WORK_EMAIL);
            mBuilder.setStyle(new NotificationCompat.BigTextStyle()
                    .bigText(purposeSchedule));
            mBuilder.setAutoCancel( true ) ;
            mBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
            mBuilder.setCategory(NotificationCompat.CATEGORY_CALL);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O ) {
                int importance = NotificationManager.IMPORTANCE_HIGH ;
                NotificationChannel notificationChannel = new
                        NotificationChannel(NOTIFICATION_CHANNEL_ID , "ANTENATAL" , importance) ;
                mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID) ;
                mNotificationManager.createNotificationChannel(notificationChannel) ;
            }
            mNotificationManager.notify(( int ) System.currentTimeMillis (),mBuilder.build());
        }
    }

}
