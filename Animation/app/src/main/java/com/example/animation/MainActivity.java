package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation alphaAnimation = new AlphaAnimation(1, 0);
                // 步骤2：创建透明度动画的对象 & 设置动画效果

                alphaAnimation.setDuration(3000);
                Interpolator overshootInterpolator = new AccelerateDecelerateInterpolator();
                // 步骤3：创建对应的插值器类对象

                alphaAnimation.setInterpolator(overshootInterpolator);
                // 步骤4：给动画设置插值器

                textView.startAnimation(alphaAnimation);
                // 步骤5：播放动画
            }
        });
    }

}