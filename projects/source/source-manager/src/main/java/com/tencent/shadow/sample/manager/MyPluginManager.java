package com.tencent.shadow.sample.manager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.demo.source.constant.Constant;
import com.tencent.shadow.dynamic.host.EnterCallback;
import com.tencent.shadow.dynamic.host.PluginManagerImpl;

/**
 * author : zhangws
 * date : 2022/3/15 14:03
 * description :
 */
public class MyPluginManager implements PluginManagerImpl {
    final private HelloPluginManager helloPluginManager;
    final private HiPluginManager hiPluginManager;
    final private UninstallPluginDynamicPluginManager uninstallPluginManager;

    public MyPluginManager(Context context) {
        this.helloPluginManager = new HelloPluginManager(context);
        this.hiPluginManager = new HiPluginManager(context);
        this.uninstallPluginManager = new UninstallPluginDynamicPluginManager(context);
    }

    @Override
    public void enter(Context context, long formId, Bundle bundle, EnterCallback callback) {

        if (formId == Constant.FROM_ID_START_ACTIVITY) {
            //activity 跳转
            String partKey = bundle.getString(Constant.KEY_PLUGIN_PART_KEY, "");

            if (partKey.equals(Constant.PART_KEY_PLUGIN_HELLO_APP)) {
                helloPluginManager.enter(context, formId, bundle, callback);
            } else if (partKey.equals(Constant.PART_KEY_PLUGIN_MAME)) {
                hiPluginManager.enter(context, formId, bundle, callback);

            } else {
                throw new IllegalArgumentException("unexpected plugin load request: " + partKey);

            }
        } else if (formId == Constant.FROM_ID_UNINSTALL_PLUGIN) {
            // 插件卸载
            uninstallPluginManager.enter(context, formId, bundle, callback);
        } else {
            throw new IllegalArgumentException("不认识的fromId==" + formId);
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        helloPluginManager.onCreate(bundle);
        hiPluginManager.onCreate(bundle);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        hiPluginManager.onSaveInstanceState(outState);
        helloPluginManager.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        hiPluginManager.onDestroy();
        helloPluginManager.onDestroy();
    }
}
