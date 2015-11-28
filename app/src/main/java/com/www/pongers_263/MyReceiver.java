package com.www.pongers_263;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by winniewu on 11/27/15.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("www", "receiver received broadcast");
        Toast.makeText(context, "Airplane mode changed",
                Toast.LENGTH_LONG).show();

        Intent i = new Intent(context, MaliciousActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}

