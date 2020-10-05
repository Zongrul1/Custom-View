# AIDL-Client
### 使用步骤
1.从server处复制AIDL文件   
2. 进行服务绑定
```java                			
	Intent intent = new Intent();
                ComponentName componentName = new ComponentName("com.example.serverdemo", "com.example.serverdemo.MyService");
                intent.setComponent(componentName);
                intent.setAction("MyService");
                bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
```