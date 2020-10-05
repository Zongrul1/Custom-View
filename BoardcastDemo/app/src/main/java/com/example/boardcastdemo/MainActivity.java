package com.example.boardcastdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button boardcastButton;

    private final String NORMAL_ACTION = "com.example.boardcastdemo.NormalBoardcast";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boardcastButton = findViewById(R.id.boardcastButton);
        boardcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.normal.receiver");
                intent.putExtra("Msg", "Hi");
                intent.setComponent(new ComponentName(getPackageName(),NORMAL_ACTION));
                sendBroadcast(intent);
            }
        });
    }
}