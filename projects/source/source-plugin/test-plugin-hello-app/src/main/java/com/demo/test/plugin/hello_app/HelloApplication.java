package com.demo.test.plugin.hello_app;

import android.app.Application;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * author : zhangws
 * date : 2022/3/7 10:42
 * description :
 */
public class HelloApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
        ARouter.openLog();
        ARouter.openDebug();
    }


}
