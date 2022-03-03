package com.demo.source.testapp.lib;


import static com.demo.source.plugin.app.lib.gallery.cases.UseCaseManager.useCases;

import android.app.Application;

import com.demo.source.plugin.app.lib.gallery.cases.UseCaseManager;
import com.demo.source.plugin.app.lib.gallery.cases.entity.UseCase;
import com.demo.source.plugin.app.lib.gallery.cases.entity.UseCaseCategory;
import com.demo.source.testapp.lib.usecases.activity.TestActivityOnCreate;
import com.demo.source.testapp.lib.usecases.activity.TestActivityOptionMenu;
import com.demo.source.testapp.lib.usecases.activity.TestActivityOrientation;
import com.demo.source.testapp.lib.usecases.activity.TestActivityReCreate;
import com.demo.source.testapp.lib.usecases.activity.TestActivityReCreateBySystem;
import com.demo.source.testapp.lib.usecases.activity.TestActivitySetTheme;
import com.demo.source.testapp.lib.usecases.activity.TestActivityWindowSoftMode;
import com.demo.source.testapp.lib.usecases.context.ActivityContextSubDirTestActivity;
import com.demo.source.testapp.lib.usecases.context.ApplicationContextSubDirTestActivity;
import com.demo.source.testapp.lib.usecases.dialog.TestDialogActivity;
import com.demo.source.testapp.lib.usecases.fragment.TestDialogFragmentActivity;
import com.demo.source.testapp.lib.usecases.fragment.TestDynamicFragmentActivity;
import com.demo.source.testapp.lib.usecases.fragment.TestXmlFragmentActivity;
import com.demo.source.testapp.lib.usecases.host_communication.PluginUseHostClassActivity;
import com.demo.source.testapp.lib.usecases.packagemanager.TestPackageManagerActivity;
import com.demo.source.testapp.lib.usecases.provider.TestDBContentProviderActivity;
import com.demo.source.testapp.lib.usecases.provider.TestFileProviderActivity;
import com.demo.source.testapp.lib.usecases.receiver.TestDynamicReceiverActivity;
import com.demo.source.testapp.lib.usecases.receiver.TestReceiverActivity;
import com.demo.source.testapp.lib.usecases.webview.WebViewActivity;


public class UseCaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initCase();
    }

    private static void initCase() {

        if (UseCaseManager.sInit) {
            throw new RuntimeException("不能重复调用init");
        }

        UseCaseManager.sInit = true;

        UseCaseCategory activityCategory = new UseCaseCategory("Activity测试用例", new UseCase[]{
                new TestActivityOnCreate.Case(),
                new TestActivityReCreate.Case(),
                new TestActivityReCreateBySystem.Case(),
                new TestActivityOrientation.Case(),
                new TestActivityWindowSoftMode.Case(),
                new TestActivitySetTheme.Case(),
                new TestActivityOptionMenu.Case(),
                new WebViewActivity.Case()
        });
        useCases.add(activityCategory);

        UseCaseCategory broadcastReceiverCategory = new UseCaseCategory("广播测试用例", new UseCase[]{
                new TestReceiverActivity.Case(),
                new TestDynamicReceiverActivity.Case()
        });
        useCases.add(broadcastReceiverCategory);


        UseCaseCategory providerCategory = new UseCaseCategory("ContentProvider测试用例", new UseCase[]{
                new TestDBContentProviderActivity.Case(),
                new TestFileProviderActivity.Case()
        });
        useCases.add(providerCategory);


        UseCaseCategory fragmentCategory = new UseCaseCategory("fragment测试用例", new UseCase[]{
                new TestDynamicFragmentActivity.Case(),
                new TestXmlFragmentActivity.Case(),
                new TestDialogFragmentActivity.Case()
        });
        useCases.add(fragmentCategory);

        UseCaseCategory dialogCategory = new UseCaseCategory("Dialog测试用例", new UseCase[]{
                new TestDialogActivity.Case(),
        });
        useCases.add(dialogCategory);

        UseCaseCategory packageManagerCategory = new UseCaseCategory("PackageManager测试用例", new UseCase[]{
                new TestPackageManagerActivity.Case(),
        });
        useCases.add(packageManagerCategory);


        UseCaseCategory contextCategory = new UseCaseCategory("Context相关测试用例", new UseCase[]{
                new ActivityContextSubDirTestActivity.Case(),
                new ApplicationContextSubDirTestActivity.Case(),
        });
        useCases.add(contextCategory);

        UseCaseCategory communicationCategory = new UseCaseCategory("插件和宿主通信相关测试用例", new UseCase[]{
                new PluginUseHostClassActivity.Case(),
        });
        useCases.add(communicationCategory);
    }
}
