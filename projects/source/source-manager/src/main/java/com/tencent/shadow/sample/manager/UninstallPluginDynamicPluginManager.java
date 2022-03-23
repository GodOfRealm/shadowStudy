package com.tencent.shadow.sample.manager;

import static com.demo.source.constant.Constant.PART_KEY_PLUGIN_BASE;
import static com.demo.source.constant.Constant.PART_KEY_PLUGIN_MAIN_APP;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.demo.source.constant.Constant;
import com.tencent.shadow.core.manager.installplugin.InstalledPlugin;
import com.tencent.shadow.dynamic.host.EnterCallback;
import com.tencent.shadow.source.manager.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author : zhangws
 * date : 2022/3/15 13:58
 * description :
 */
public class UninstallPluginDynamicPluginManager extends FastPluginManager {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    private Context mCurrentContext;

    public UninstallPluginDynamicPluginManager(Context context) {
        super(context);
        mCurrentContext = context;
    }

    /**
     * @return PluginManager实现的别名，用于区分不同PluginManager实现的数据存储路径
     */
    @Override
    protected String getName() {
        return "uninstallPlugin-dynamic-manager";
    }

    /**
     * @return 宿主中注册的PluginProcessService实现的类名
     */
    @Override
    protected String getPluginProcessServiceName(String partKey) {
        return "com.demo.source.host.UninstallPluginProcessPPS";
    }

    @Override
    public void enter(final Context context, long fromId, Bundle bundle, final EnterCallback callback) {
        if (bundle == null) {
            return;
        }
        String uuid = bundle.getString(Constant.KEY_EXTRAS, "");
        if (!TextUtils.isEmpty(uuid)) {
            Log.e("hello", deleteInstalledPlugin(uuid) + "");
        }
    }


}
