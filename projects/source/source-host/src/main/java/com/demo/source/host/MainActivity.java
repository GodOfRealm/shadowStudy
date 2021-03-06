/*
 * Tencent is pleased to support the open source community by making Tencent Shadow available.
 * Copyright (C) 2019 THL A29 Limited, a Tencent company.  All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *     https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.demo.source.host;


import static com.demo.source.constant.Constant.PART_KEY_PLUGIN_BASE;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.demo.source.constant.Constant;
import com.demo.source.host.plugin_view.HostAddPluginViewActivity;
import com.tencent.shadow.dynamic.host.EnterCallback;

import java.io.File;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.TestHostTheme);

        LinearLayout rootView = new LinearLayout(this);
        rootView.setOrientation(LinearLayout.VERTICAL);

        TextView infoTextView = new TextView(this);
        infoTextView.setText(R.string.main_activity_info);
        rootView.addView(infoTextView);

        final Spinner partKeySpinner = new Spinner(this);
        ArrayAdapter<String> partKeysAdapter = new ArrayAdapter<>(this, R.layout.part_key_adapter);
        partKeysAdapter.addAll(
                Constant.PART_KEY_PLUGIN_HELLO_APP,
                Constant.PART_KEY_PLUGIN_MAME
        );
        partKeySpinner.setAdapter(partKeysAdapter);

        EditText editText = new EditText(this);
        editText.setText("plugin-debug.zip");


        Button startPluginButton = new Button(this);
        startPluginButton.setText(R.string.start_plugin);


        Button uninstallPluginButton = new Button(this);
        uninstallPluginButton.setText("????????????");

        rootView.addView(editText);
        rootView.addView(partKeySpinner);
        rootView.addView(uninstallPluginButton);
        rootView.addView(startPluginButton);

        uninstallPluginButton.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            String zipPath = editText.getText().toString();
            String partKey = (String) partKeySpinner.getSelectedItem();

            String zipabsolutePath = new File(this.getFilesDir(), zipPath).getAbsolutePath();
            bundle.putString(Constant.KEY_PLUGIN_ZIP_PATH, zipabsolutePath);
            bundle.putString(Constant.KEY_PLUGIN_PART_KEY, partKey);


            HostApplication.getApp().loadPluginManager(PluginHelper.getInstance().pluginManagerFile);
            HostApplication.getApp().getPluginManager().enter(this, Constant.FROM_ID_UNINSTALL_PLUGIN, bundle, new EnterCallback() {
                @Override
                public void onShowLoadingView(View view) {

                }

                @Override
                public void onCloseLoadingView() {

                }

                @Override
                public void onEnterComplete() {

                }
            });
        });


        startPluginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String partKey = (String) partKeySpinner.getSelectedItem();
                String zipPath = editText.getText().toString();
                Intent intent = new Intent(MainActivity.this, PluginLoadActivity.class);
                intent.putExtra(Constant.KEY_PLUGIN_PART_KEY, partKey);
                intent.putExtra(Constant.KEY_PLUGIN_ZIP_PATH, zipPath);

                switch (partKey) {
                    //??????????????????????????????????????????????????????????????????????????????????????????
                    case Constant.PART_KEY_PLUGIN_MAIN_APP:
                        intent.putExtra(Constant.KEY_ACTIVITY_CLASSNAME, "com.demo.source.plugin.app.lib.gallery.splash.SplashActivity");
                        break;
                    case Constant.PART_KEY_PLUGIN_ANOTHER_APP:
                        intent.putExtra(Constant.KEY_ACTIVITY_CLASSNAME, "com.tencent.shadow.test.plugin.androidx_cases.lib.AppCompatTestActivity");
                        break;
                    case Constant.PART_KEY_PLUGIN_HELLO_APP:
                        intent.putExtra(Constant.KEY_ACTIVITY_CLASSNAME, "com.demo.test.plugin.hello_app.MainActivity");
                        break;
                    case Constant.PART_KEY_PLUGIN_MAME:
                        intent.putExtra(Constant.KEY_ACTIVITY_CLASSNAME, "com.mobile.emulatormodule.MamePlayingActivity");
                        break;

                }
                startActivity(intent);

            }
        });

        Button startHostAddPluginViewActivityButton = new Button(this);
        startHostAddPluginViewActivityButton.setText("??????????????????View");
        startHostAddPluginViewActivityButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, HostAddPluginViewActivity.class);
            startActivity(intent);
        });
        rootView.addView(startHostAddPluginViewActivityButton);

        setContentView(rootView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (permissions[i].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        throw new RuntimeException("??????????????????.");
                    }
                }
            }
        }
    }

}
