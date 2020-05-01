package com.example.antenatal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.concurrent.TimeUnit;

/**
 * Created by sherif146 on 27/01/2018.
 */

public class myAlarmReceiver extends BroadcastReceiver {
    Context context;
    private PendingIntent pendingIntent;
    private AlarmManager manager;
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent alarmIntent = new Intent(context, ReminderService.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        manager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        int reminderIntervalMin = 60;
        int reminderIntervalSec = (int)(TimeUnit.MINUTES.toMillis(reminderIntervalMin));
        int syncTime = reminderIntervalSec;
       // manager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime()+300, 300*1000, pendingIntent);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, reminderIntervalSec, reminderIntervalSec + syncTime, pendingIntent);
    }
}
