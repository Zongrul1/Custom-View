# Animation

## View Animation(视图动画）

### 类型
- Translate
- Scale
- Rotate
- Alpha

### path
res/anim

### code
```java
Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.viewanimation);
textView.startAnimation(animation);
```
### 插值器与估值器
参考：[https://www.jianshu.com/p/915471529d3c](https://www.jianshu.com/p/915471529d3c)

```java
Interpolator overshootInterpolator = new OvershootInterpolator();
alphaAnimation.setInterpolator(overshootInterpolator);
```

## Drawable Animation
顺序播放一组预先定义好的图片
```java
view = findViewById(R.id.view);
        view.setBackgroundResource(R.drawable.drawable_anim);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationDrawable animationDrawable = (AnimationDrawable) view.getBackground();//用于指定背景
                animationDrawable.start();
            }
        });
```

## Value Animation
