package com.example.greendaodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button Add,Delete,Update,Search;
    private DbController mDbController;
    private PersonInfor personInfor1,personInfor2,personInfor3,personInfor4;
    private long insertTipId;
    private TextView dataArea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbController = DbController.getInstance(MainActivity.this);
        initView();
        Envent();
        similateData();
    }

    private void similateData() {
        personInfor1 = new PersonInfor(null,"001","王大宝","男");
        personInfor2 = new PersonInfor(null,"002","李晓丽","女");
        personInfor3 = new PersonInfor(null,"003","王麻麻","男");
        personInfor4 = new PersonInfor(null,"004","王大锤","女");
    }

    private void Envent() {

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add
                mDbController.insertOrReplace(personInfor1);

                mDbController.insertOrReplace(personInfor2);

                mDbController.insertOrReplace(personInfor3);

                mDbController.insertOrReplace(personInfor4);

                showDataList();
            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Delete
                mDbController.delete("王麻麻");

                showDataList();
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Update
                mDbController.update(personInfor1);

                showDataList();
            }
        });

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Search
                showDataList();
            }
        });
    }

    private void showDataList() {
        StringBuilder sb = new StringBuilder();
        List<PersonInfor> personInfors = mDbController.searchAll();
        for(PersonInfor personInfor:personInfors){
            // dataArea.setText("id:"+p);
            sb.append("id:").append(personInfor.getId())
                    .append("perNo:").append(personInfor.getPerNo())
                    .append("name:").append(personInfor.getName())
                    .append("sex:").append(personInfor.getSex())
                    .append("\n");
        }
        dataArea.setText(sb.toString());
    }

    private void initView() {

        Add = findViewById(R.id.Add);

        Delete = findViewById(R.id.Delete);

        Update = findViewById(R.id.Update);

        Search = findViewById(R.id.Search);

        dataArea= findViewById(R.id.tips);

    }
}