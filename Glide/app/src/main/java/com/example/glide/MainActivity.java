package com.example.glide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
//https://www.cnblogs.com/wjtaigwh/p/7206596.html
public class MainActivity extends AppCompatActivity {
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.image);
        String HttpsUrl = "https://www.hai456.com/wordpress/project/program/static.runoob.com/images/demo/demo2.jpg";
        String HttpUrl = "http://guolin.tech/book.png";
        Glide.with(this)
                .load(HttpsUrl)
                .crossFade() //淡出动画
//                .placeholder(R.drawable.place_image)//图片加载出来前，显示的图片
//                .error(R.drawable.error_image)//图片加载失败后，显示的图片
//                .override(400,400) //裁剪
                .into(image);
    }
}