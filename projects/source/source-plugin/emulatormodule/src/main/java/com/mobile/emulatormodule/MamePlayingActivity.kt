package com.mobile.emulatormodule

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.view.View
import com.mobile.emulatormodule.mame.Emulator
import com.mobile.emulatormodule.mame.helpers.MainHelper
import com.mobile.emulatormodule.mame.helpers.PrefsHelper
import com.mobile.emulatormodule.mame.views.IEmuView

class MamePlayingActivity : AppCompatActivity() {
    val prefsHelper by lazy { PrefsHelper(this) }
    val mainHelper by lazy { MainHelper(this) }
    var emuView: View? = null
        protected set
    val MAME_ROMS_PATH by lazy { this.filesDir.absolutePath+ "/roms/" }
    val MAME_SO_PATH by lazy { this.filesDir.absolutePath + "/android_mame_lib/" }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) !== PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
            }
        }
        initPrepare()

    }

    //相关准备
    private fun initPrepare() {
        emuView = findViewById(R.id.EmulatorViewGL)
        (emuView as IEmuView?)!!.setMAME4droid(this)
        Emulator.setMAME4droid(this)
        mainHelper.preparePlugin(this, "dinou.zip", MAME_ROMS_PATH + "dinou.zip")
        mainHelper.preparePlugin(this, "libMAME4droid.so", MAME_SO_PATH + "libMAME4droid.so")
        Handler().postDelayed(Runnable {
            //显示dialog
            mainHelper.updateMAME4droid()
            mainHelper.copyFiles(this)
        }, 5000) //5秒

        Handler().postDelayed(Runnable {
            Emulator.emulate(mainHelper.getLibDir(this), mainHelper.getDefaultROMsDIR(this), "dinou.zip")
        }, 5000) //5秒

    }
}