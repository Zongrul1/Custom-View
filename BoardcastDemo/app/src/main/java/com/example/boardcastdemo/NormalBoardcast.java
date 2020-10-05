package com.example.boardcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class NormalBoardcast extends BroadcastReceiver {

    private static final String TAG = "NormalReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("Msg");
        Log.e(TAG, msg);
    }
}
