package com.demo.test.plugin.hello_app;

import android.app.Application;
import android.util.Log;

/**
 * author : zhangws
 * date : 2022/3/7 10:42
 * description :
 */
public class HelloApplication extends Application {
    private static HelloApplication sInstence;

    public boolean isOnCreate;

    @Override
    public void onCreate() {
        sInstence = this;
        isOnCreate = true;
        super.onCreate();
        Log.e("hello", "app oncreate");

    }

    public static HelloApplication getInstance() {
        return sInstence;
    }

}
