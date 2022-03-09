/*
 * This file is part of MAME4droid.
 *
 * Copyright (C) 2013 David Valdeita (Seleuco)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses>.
 *
 * Linking MAME4droid statically or dynamically with other modules is
 * making a combined work based on MAME4droid. Thus, the terms and
 * conditions of the GNU General Public License cover the whole
 * combination.
 *
 * In addition, as a special exception, the copyright holders of MAME4droid
 * give you permission to combine MAME4droid with free software programs
 * or libraries that are released under the GNU LGPL and with code included
 * in the standard release of MAME under the MAME License (or modified
 * versions of such code, with unchanged license). You may copy and
 * distribute such a system following the terms of the GNU GPL for MAME4droid
 * and the licenses of the other code concerned, provided that you include
 * the source code of that other code when and as the GNU GPL requires
 * distribution of source code.
 *
 * Note that people who make modified versions of MAME4idroid are not
 * obligated to grant this special exception for their modified versions; it
 * is their choice whether to do so. The GNU General Public License
 * gives permission to release a modified version without this exception;
 * this exception also makes it possible to release a modified version
 * which carries forward this exception.
 *
 * MAME4droid is dual-licensed: Alternatively, you can license MAME4droid
 * under a MAME license, as set out in http://mamedev.org/
 */

package com.mobile.emulatormodule.mame.helpers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout.LayoutParams;

import com.mobile.emulatormodule.MamePlayingActivity;
import com.mobile.emulatormodule.R;
import com.mobile.emulatormodule.mame.Emulator;
import com.mobile.emulatormodule.mame.prefs.GameFilterPrefs;
import com.mobile.emulatormodule.mame.views.IEmuView;

import org.apache.commons.io.FileUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class MainHelper {

    final static public int SUBACTIVITY_USER_PREFS = 1;
    final static public int SUBACTIVITY_HELP = 2;
    final static public int BUFFER_SIZE = 1024 * 48;

    final static public String MAGIC_FILE = "game.bin";

    protected MamePlayingActivity mm = null;

    public MainHelper(MamePlayingActivity value) {
        mm = value;
    }

    //这里放MAME SO
    public String getLibDir(Context context) {
        String lib_dir;

        lib_dir = context.getFilesDir().getAbsolutePath() + "/android_mame_lib/";
        Log.e("fuck--SO>", lib_dir);

        return lib_dir;
    }

    //这里放游戏
    public String getDefaultROMsDIR(Context context) {
        String res_dir = null;

        res_dir = context.getFilesDir().getAbsolutePath() + "/";
        Log.e("fuck--游戏>", res_dir);

        return res_dir;
    }

    public void preparePlugin(Context myContext, String ASSETS_NAME, String path) {
        Log.e("fuck pp", ASSETS_NAME + "--" + path);
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            }
            InputStream is = myContext.getAssets().open(ASSETS_NAME);
            FileUtils.copyInputStreamToFile(is, file);

        } catch (IOException e) {
            throw new RuntimeException("从assets中复制apk出错", e);
        }
    }


    public void copyFiles(Context context) {

        try {

            String roms_dir = getDefaultROMsDIR(context);

            File dir = new File(roms_dir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File dir2 = new File(roms_dir + File.separator + "saves/");
            if (!dir2.exists()) {
                dir2.mkdirs();
            }
            File fm = new File(roms_dir + File.separator + "saves/"
                    + MAGIC_FILE);
            if (fm.exists())
                return;

            fm.createNewFile();

            // Create a ZipInputStream to read the zip file
            BufferedOutputStream dest = null;
            InputStream fis = mm.getResources().openRawResource(R.raw.files);
            ZipInputStream zis = new ZipInputStream(

                    new BufferedInputStream(fis));
            // Loop over all of the entries in the zip file
            int count;
            byte data[] = new byte[BUFFER_SIZE];
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (!entry.isDirectory()) {

                    String destination = roms_dir;
                    String destFN = destination + File.separator
                            + entry.getName();
                    // Write the file to the file system
                    FileOutputStream fos = new FileOutputStream(destFN);
                    dest = new BufferedOutputStream(fos, BUFFER_SIZE);
                    while ((count = zis.read(data, 0, BUFFER_SIZE)) != -1) {
                        dest.write(data, 0, count);
                    }
                    dest.flush();
                    dest.close();
                } else {
                    File f = new File(roms_dir + File.separator
                            + entry.getName());
                    f.mkdirs();
                }

            }
            zis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getscrOrientation() {
        Display getOrient = mm.getWindowManager().getDefaultDisplay();
        // int orientation = getOrient.getOrientation();

        int orientation = mm.getResources().getConfiguration().orientation;

        // Sometimes you may get undefined orientation Value is 0
        // simple logic solves the problem compare the screen
        // X,Y Co-ordinates and determine the Orientation in such cases
        if (orientation == Configuration.ORIENTATION_UNDEFINED) {

            Configuration config = mm.getResources().getConfiguration();
            orientation = config.orientation;

            if (orientation == Configuration.ORIENTATION_UNDEFINED) {
                // if emu_height and widht of screen are equal then
                // it is square orientation
                if (getOrient.getWidth() == getOrient.getHeight()) {
                    orientation = Configuration.ORIENTATION_SQUARE;
                } else { // if widht is less than emu_height than it is portrait
                    if (getOrient.getWidth() < getOrient.getHeight()) {
                        orientation = Configuration.ORIENTATION_PORTRAIT;
                    } else { // if it is not any of the above it will defineitly
                        // be landscape
                        orientation = Configuration.ORIENTATION_LANDSCAPE;
                    }
                }
            }
        }
        return orientation; // return values 1 is portrait and 2 is Landscape
        // Mode
    }

    public void reload() {

        System.out.println("RELOAD!!!!!");

        Intent intent = mm.getIntent();

        mm.overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        mm.finish();

        mm.overridePendingTransition(0, 0);
        mm.startActivity(intent);
        mm.overridePendingTransition(0, 0);
    }

    public boolean updateOverlayFilter() {

        String value = PrefsHelper.PREF_OVERLAY_NONE;

        if (getscrOrientation() == Configuration.ORIENTATION_PORTRAIT)
            value = mm.getPrefsHelper().getPortraitOverlayFilterValue();
        else
            value = mm.getPrefsHelper().getLandscapeOverlayFilterValue();

        if (Emulator.getOverlayFilterValue() != value) {
            Emulator.setOverlayFilterValue(value);
            reload();
            return true;
        } else {
            Emulator.setOverlayFilterValue(value);
            return false;
        }
    }

    public boolean updateVideoRender() {

        if (Emulator.getVideoRenderMode() != mm.getPrefsHelper()
                .getVideoRenderMode()) {
            Emulator.setVideoRenderMode(mm.getPrefsHelper()
                    .getVideoRenderMode());
            reload();
            return true;
        } else {
            Emulator.setVideoRenderMode(mm.getPrefsHelper()
                    .getVideoRenderMode());
            return false;
        }
    }

    public void setBorder() {

        if (true)
            return;
//
//        int size = mm.getResources().getConfiguration().screenLayout
//                & Configuration.SCREENLAYOUT_SIZE_MASK;
//
//        if ((size == Configuration.SCREENLAYOUT_SIZE_LARGE || size == Configuration.SCREENLAYOUT_SIZE_XLARGE)
//                && mm.getMainHelper().getscrOrientation() == Configuration.ORIENTATION_PORTRAIT) {
//            LayoutParams lp = (LayoutParams) mm.getEmuView().getLayoutParams();
//            View v = mm.findViewById(R.id.EmulatorFrame);
//            LayoutParams lp2 = null;
//
//            if (mm.getPrefsHelper().isPortraitTouchController()) {
//                v.setBackgroundDrawable(mm.getResources().getDrawable(
//                        R.mipmap.border));
//                lp.setMargins(15, 15, 15, 15);
//                if (lp2 != null)
//                    lp2.setMargins(15, 15, 15, 15);
//            } else {
//                v.setBackgroundDrawable(null);
//                v.setBackgroundColor(Color.parseColor("#000000"));
//                lp.setMargins(0, 0, 0, 0);
//                if (lp2 != null)
//                    lp2.setMargins(0, 0, 0, 0);
//            }
//        }
    }

    @SuppressLint({"NewApi"})
    public void updateEmuValues() {

        PrefsHelper prefsHelper = mm.getPrefsHelper();

        Emulator.setValue(Emulator.FPS_SHOWED_KEY,
                prefsHelper.isFPSShowed() ? 1 : 0);
        Emulator.setValue(Emulator.INFOWARN_KEY,
                prefsHelper.isShowInfoWarnings() ? 1 : 0);

        Emulator.setValue(Emulator.IDLE_WAIT, prefsHelper.isIdleWait() ? 1 : 0);
        Emulator.setValue(Emulator.THROTTLE, prefsHelper.isThrottle() ? 1 : 0);
        Emulator.setValue(Emulator.AUTOSAVE, prefsHelper.isAutosave() ? 1 : 0);
        Emulator.setValue(Emulator.CHEAT, prefsHelper.isCheat() ? 1 : 0);
        Emulator.setValue(Emulator.SOUND_VALUE, prefsHelper.getSoundValue());
        Emulator.setValue(Emulator.FRAME_SKIP_VALUE,
                prefsHelper.getFrameSkipValue());

        Emulator.setValue(Emulator.EMU_RESOLUTION,
                prefsHelper.getEmulatedResolution());
        Emulator.setValue(Emulator.FORCE_PXASPECT,
                prefsHelper.getForcedPixelAspect());

        Emulator.setValue(Emulator.DOUBLE_BUFFER, mm.getPrefsHelper()
                .isDoubleBuffer() ? 1 : 0);
        Emulator.setValue(Emulator.PXASP1, mm.getPrefsHelper()
                .isPlayerXasPlayer1() ? 1 : 0);

        Emulator.setValue(Emulator.AUTOFIRE, mm.getPrefsHelper()
                .getAutofireValue());

        Emulator.setValue(Emulator.HISCORE, mm.getPrefsHelper().isHiscore() ? 1
                : 0);

        Emulator.setValue(Emulator.VBEAN2X, mm.getPrefsHelper()
                .isVectorBeam2x() ? 1 : 0);
        Emulator.setValue(Emulator.VANTIALIAS, mm.getPrefsHelper()
                .isVectorAntialias() ? 1 : 0);
        Emulator.setValue(Emulator.VFLICKER, mm.getPrefsHelper()
                .isVectorFlicker() ? 1 : 0);

        GameFilterPrefs gfp = mm.getPrefsHelper().getGameFilterPrefs();
        boolean dirty = gfp.readValues();
        gfp.sendValues();
        if (dirty) {
            if (!Emulator.isInMAME())
                Emulator.setValue(Emulator.RESET_FILTER, 1);
            Emulator.setValue(Emulator.LAST_GAME_SELECTED, 0);
        }

        Emulator.setValue(Emulator.EMU_SPEED, mm.getPrefsHelper()
                .getEmulatedSpeed());
        Emulator.setValue(Emulator.VSYNC, mm.getPrefsHelper().getVSync());

        Emulator.setValue(Emulator.SOUND_ENGINE, mm.getPrefsHelper()
                .getSoundEngine() > 2 ? 2 : 1);

        AudioManager am = (AudioManager) mm
                .getSystemService(Context.AUDIO_SERVICE);
        int sfr = 2048;
        try {
            sfr = Integer
                    .valueOf(
                            am.getProperty(AudioManager.PROPERTY_OUTPUT_FRAMES_PER_BUFFER))
                    .intValue();
            System.out.println("PROPERTY_OUTPUT_FRAMES_PER_BUFFER:" + sfr);
        } catch (Error e) {
        }

        if (mm.getPrefsHelper().getSoundEngine() == PrefsHelper.PREF_SNDENG_OPENSL)
            sfr *= 2;
        Emulator.setValue(Emulator.SOUND_DEVICE_FRAMES, sfr);

        int sr = 44100;
        try {
            sr = Integer.valueOf(
                    am.getProperty(AudioManager.PROPERTY_OUTPUT_SAMPLE_RATE))
                    .intValue();
            System.out.println("PROPERTY_OUTPUT_SAMPLE_RATE:" + sr);
        } catch (Error e) {
        }
        Emulator.setValue(Emulator.SOUND_DEVICE_SR, sr);
    }

    @SuppressLint("NewApi")
    public void updateMAME4droid() {

        if (updateVideoRender())
            return;
        if (updateOverlayFilter())
            return;

        if (Emulator.isPortraitFull() != mm.getPrefsHelper()
                .isPortraitFullscreen()) {
            Emulator.setPortraitFull(true);
            reload();
            return;
        }

        View emuView = mm.getEmuView();

        PrefsHelper prefsHelper = mm.getPrefsHelper();

        String definedKeys = prefsHelper.getDefinedKeys();
        final String[] keys = definedKeys.split(":");


        Emulator.setDebug(prefsHelper.isDebugEnabled());
        Emulator.setThreadedSound(!prefsHelper.isSoundSync());

        updateEmuValues();

        setBorder();


        if (this.getscrOrientation() == Configuration.ORIENTATION_PORTRAIT) {

            ((IEmuView) emuView).setScaleType(prefsHelper
                    .getPortraitScaleMode());


            Emulator.setFrameFiltering(prefsHelper.isPortraitBitmapFiltering());


        } else {
            ((IEmuView) emuView).setScaleType(mm.getPrefsHelper()
                    .getLandscapeScaleMode());


            Emulator.setFrameFiltering(mm.getPrefsHelper()
                    .isLandscapeBitmapFiltering());


        }


        emuView.requestLayout();

    }

    public ArrayList<Integer> measureWindow(int widthMeasureSpec,
                                            int heightMeasureSpec, int scaleType) {

        int widthSize = 1;
        int heightSize = 1;

        if (scaleType == PrefsHelper.PREF_STRETCH)// FILL ALL
        {
            widthSize = MeasureSpec.getSize(widthMeasureSpec);
            heightSize = MeasureSpec.getSize(heightMeasureSpec);
        } else {

            // int emu_w = Emulator.getEmulatedWidth();
            // int emu_h = Emulator.getEmulatedHeight();
            int emu_w = Emulator.getEmulatedVisWidth();
            int emu_h = Emulator.getEmulatedVisHeight();

            if (scaleType == PrefsHelper.PREF_15X) {
                emu_w = (int) (emu_w * 1.5f);
                emu_h = (int) (emu_h * 1.5f);
            } else if (scaleType == PrefsHelper.PREF_20X) {
                emu_w = emu_w * 2;
                emu_h = emu_h * 2;
            } else if (scaleType == PrefsHelper.PREF_25X) {
                emu_w = (int) (emu_w * 2.5f);
                emu_h = (int) (emu_h * 2.5f);
            } else if (scaleType == PrefsHelper.PREF_3X) {
                emu_w = (int) (emu_w * 3.0f);
                emu_h = (int) (emu_h * 3.0f);
            } else if (scaleType == PrefsHelper.PREF_35X) {
                emu_w = (int) (emu_w * 3.5f);
                emu_h = (int) (emu_h * 3.5f);
            } else if (scaleType == PrefsHelper.PREF_4X) {
                emu_w = (int) (emu_w * 4.0f);
                emu_h = (int) (emu_h * 4.0f);
            } else if (scaleType == PrefsHelper.PREF_45X) {
                emu_w = (int) (emu_w * 4.5f);
                emu_h = (int) (emu_h * 4.5f);
            } else if (scaleType == PrefsHelper.PREF_5X) {
                emu_w = (int) (emu_w * 5.0f);
                emu_h = (int) (emu_h * 5.0f);
            }

            if (scaleType == PrefsHelper.PREF_55X) {
                emu_w = (int) (emu_w * 5.5f);
                emu_h = (int) (emu_h * 5.5f);
            }

            if (scaleType == PrefsHelper.PREF_6X) {
                emu_w = (int) (emu_w * 6.0f);
                emu_h = (int) (emu_h * 6.0f);
            }

            int w = emu_w;
            int h = emu_h;

            if (scaleType == PrefsHelper.PREF_SCALE
                    || scaleType == PrefsHelper.PREF_STRETCH
                    || !Emulator.isInMAME()) {
                widthSize = MeasureSpec.getSize(widthMeasureSpec);
                heightSize = MeasureSpec.getSize(heightMeasureSpec);
            } else {
                widthSize = emu_w;
                heightSize = emu_h;
            }

            // widthSize *= 1.1;
            // heightSize *= 1.1;

            if (heightSize == 0)
                heightSize = 1;
            if (widthSize == 0)
                widthSize = 1;

            float scale = 1.0f;

            if (scaleType == PrefsHelper.PREF_SCALE)
                scale = Math.min((float) widthSize / (float) w,
                        (float) heightSize / (float) h);

            w = (int) (w * scale);
            h = (int) (h * scale);

            float desiredAspect = (float) emu_w / (float) emu_h;

            widthSize = Math.min(w, widthSize);
            heightSize = Math.min(h, heightSize);

            if (heightSize == 0)
                heightSize = 1;
            if (widthSize == 0)
                widthSize = 1;

            float actualAspect = (float) (widthSize / heightSize);

            if (Math.abs(actualAspect - desiredAspect) > 0.0000001) {

                boolean done = false;

                // Try adjusting emu_width to be proportional to emu_height
                int newWidth = (int) (desiredAspect * heightSize);

                if (newWidth <= widthSize) {
                    widthSize = newWidth;
                    done = true;
                }

                // Try adjusting emu_height to be proportional to emu_width
                if (!done) {
                    int newHeight = (int) (widthSize / desiredAspect);
                    if (newHeight <= heightSize) {
                        heightSize = newHeight;
                    }
                }
            }
        }

        ArrayList<Integer> l = new ArrayList<Integer>();
        l.add(Integer.valueOf(widthSize));
        l.add(Integer.valueOf(heightSize));
        return l;
    }

    public void detectOUYA() {

        boolean ouya = Build.MODEL.equals("OUYA Console");

        if (ouya) {
            Context context = mm.getApplicationContext();
            SharedPreferences prefs = PreferenceManager
                    .getDefaultSharedPreferences(context);
            if (!prefs.getBoolean("ouya", false)) {
                SharedPreferences.Editor edit = prefs.edit();
                edit.putBoolean("ouya", true);
                edit.putBoolean(PrefsHelper.PREF_LANDSCAPE_TOUCH_CONTROLLER,
                        false);
                edit.putBoolean(PrefsHelper.PREF_LANDSCAPE_BITMAP_FILTERING,
                        true);
                // edit.putString("", "");
                edit.commit();
            }
        }
    }
}
