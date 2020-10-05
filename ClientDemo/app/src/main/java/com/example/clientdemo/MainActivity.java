package com.example.clientdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.serverdemo.DemoInterface;

public class MainActivity extends AppCompatActivity {
    private DemoInterface mService;
    private Button button;
    private Button button2;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
        }

        //因为有可能有多个应用同时进行RPC操作，所以同步该方法
        @Override
        public synchronized void onServiceConnected(ComponentName arg0, IBinder binder) {
            //获得IPerson接口
            mService = DemoInterface.Stub.asInterface(binder);
            if (mService != null) {
                //RPC方法调用
                Toast.makeText(MainActivity.this, "远程进程调用成功！值为 ： " + "1", Toast.LENGTH_LONG).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponentName componentName = new ComponentName("com.example.serverdemo", "com.example.serverdemo.MyService");
                intent.setComponent(componentName);
                intent.setAction("MyService");
                bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    try {
                        Log.d("Client",mService.getInfo());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
            }
        });
    }
}