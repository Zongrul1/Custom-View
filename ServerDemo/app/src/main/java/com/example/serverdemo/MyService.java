package com.example.serverdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //return MyBinder通过ServiceConnection在activity中拿到MyBinder
//        return new MyBinder();
        return mBinder;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private final DemoInterface.Stub mBinder = new DemoInterface.Stub() {


        @Override
        public String getInfo() throws RemoteException {
            return "info";
        }
    };
}
