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

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.WindowManager;

import com.mobile.emulatormodule.MamePlayingActivity;
import com.mobile.emulatormodule.mame.prefs.GameFilterPrefs;

public class PrefsHelper implements OnSharedPreferenceChangeListener {
    final static public String PREF_ROMsDIR = "PREF_ROMsDIR";

    final static public String PREF_GLOBAL_VIDEO_RENDER_MODE = "PREF_GLOBAL_VIDEO_RENDER_MODE_2";
    final static public String PREF_GLOBAL_RESOLUTION = "PREF_GLOBAL_RESOLUTION";
    final static public String PREF_GLOBAL_SPEED = "PREF_GLOBAL_SPEED";
    final static public String PREF_GLOBAL_SOUND_SYNC = "PREF_GLOBAL_SOUND_SYNC";
    final static public String PREF_GLOBAL_FRAMESKIP = "PREF_GLOBAL_FRAMESKIP";
    final static public String PREF_GLOBAL_THROTTLE = "PREF_GLOBAL_THROTTLE";
    final static public String PREF_GLOBAL_VSYNC = "PREF_GLOBAL_VSYNC";
    final static public String PREF_GLOBAL_SOUND = "PREF_GLOBAL_SOUND";
    final static public String PREF_GLOBAL_SHOW_FPS = "PREF_GLOBAL_SHOW_FPS";
    final static public String PREF_GLOBAL_SHOW_INFOWARNINGS = "PREF_GLOBAL_SHOW_INFOWARNINGS";
    final static public String PREF_GLOBAL_CHEAT = "PREF_GLOBAL_CHEAT";
    final static public String PREF_GLOBAL_AUTOSAVE = "PREF_GLOBAL_AUTOSAVE";
    final static public String PREF_GLOBAL_DEBUG = "PREF_GLOBAL_DEBUG";
    final static public String PREF_GLOBAL_IDLE_WAIT = "PREF_GLOBAL_IDLE_WAIT";
    final static public String PREF_GLOBAL_FORCE_PXASPECT = "PREF_GLOBAL_FORCE_PXASPECT_2";
    final static public String PREF_GLOBAL_HISCORE = "PREF_GLOBAL_HISCORE";
    final static public String PREF_GLOBAL_WARN_ON_EXIT = "PREF_GLOBAL_WARN_ON_EXIT";
    final static public String PREF_GLOBAL_SUSPEND_NOTIFICATION = "PREF_GLOBAL_SUSPEND_NOTIFICATION";

    final static public String PREF_PORTRAIT_SCALING_MODE = "PREF_PORTRAIT_SCALING_MODE_3";
    final static public String PREF_PORTRAIT_OVERLAY = "PREF_PORTRAIT_OVERLAY";
    final static public String PREF_PORTRAIT_TOUCH_CONTROLLER = "PREF_PORTRAIT_TOUCH_CONTROLLER";
    final static public String PREF_PORTRAIT_BITMAP_FILTERING = "PREF_PORTRAIT_BITMAP_FILTERING";
    final static public String PREF_PORTRAIT_FULLSCREEN = "PREF_PORTRAIT_FULLSCREEN";

    final static public String PREF_LANDSCAPE_SCALING_MODE = "PREF_LANDSCAPE_SCALING_MODE_3";
    final static public String PREF_LANDSCAPE_OVERLAY = "PREF_LANDSCAPE_OVERLAY";
    final static public String PREF_LANDSCAPE_TOUCH_CONTROLLER = "PREF_LANDSCAPE_TOUCH_CONTROLLER";
    final static public String PREF_LANDSCAPE_BITMAP_FILTERING = "PREF_LANDSCAPE_BITMAP_FILTERING";
    final static public String PREF_LANDSCAPE_CONTROLLER_TYPE = "PREF_LANDSCAPE_CONTROLLER_TYPE";

    final static public String PREF_DEFINED_KEYS = "PREF_DEFINED_KEYS";

    final static public String PREF_DEFINED_CONTROL_LAYOUT = "PREF_DEFINED_CONTROL_LAYOUT";
    final static public String PREF_DEFINED_CONTROL_LAYOUT_P = "PREF_DEFINED_CONTROL_LAYOUT_P";

    final static public String PREF_TRACKBALL_SENSITIVITY = "PREF_TRACKBALL_SENSITIVITY";
    final static public String PREF_TRACKBALL_NOMOVE = "PREF_TRACKBALL_NOMOVE";
    final static public String PREF_DISABLE_RIGHT_STICK = "PREF_DISABLE_RIGHT_STICK";
    final static public String PREF_ANIMATED_INPUT = "PREF_ANIMATED_INPUT";
    final static public String PREF_LIGHTGUN = "PREF_LIGHTGUN";
    final static public String PREF_TOUCH_DZ = "PREF_TOUCH_DZ";
    final static public String PREF_CONTROLLER_TYPE = "PREF_CONTROLLER_TYPE_2";
    final static public String PREF_STICK_TYPE = "PREF_STICK_TYPE_2";
    final static public String PREF_NUMBUTTONS = "PREF_NUMBUTTONS_2";
    final static public String PREF_INPUT_EXTERNAL = "PREF_INPUT_EXTERNAL_2";
    final static public String PREF_AUTOMAP_OPTIONS = "PREF_AUTOMAP_OPTIONS";
    final static public String PREF_ANALOG_DZ = "PREF_ANALOG_DZ";
    final static public String PREF_GAMEPAD_DZ = "PREF_GAMEPAD_DZ";
    final static public String PREF_VIBRATE = "PREF_VIBRATE";
    final static public String PREF_AUTOFIRE = "PREF_AUTOFIRE";

    final static public String PREF_TILT_SENSOR = "PREF_TILT_SENSOR";
    final static public String PREF_TILT_DZ = "PREF_TILT_DZ";
    final static public String PREF_TILT_SENSITIVITY = "PREF_TILT_SENSITIVITY";
    final static public String PREF_TILT_NEUTRAL = "PREF_TILT_NEUTRAL";
    final static public String PREF_TILT_ANALOG = "PREF_TILT_ANALOG";
    final static public String PREF_TILT_TOUCH = "PREF_TILT_TOUCH";
    final static public String PREF_TILT_SWAP_YZ = "PREF_TILT_SWAP_YZ";

    final static public String PREF_HIDE_STICK = "PREF_HIDE_STICK";
    final static public String PREF_BUTTONS_SIZE = "PREF_BUTTONS_SIZE";
    final static public String PREF_STICK_SIZE = "PREF_STICK_SIZE";
    final static public String PREF_VIDEO_THREAD_PRIORITY = "PREF_VIDEO_THREAD_PRIORITY";
    final static public String PREF_MAIN_THREAD_PRIORITY = "PREF_MAIN_THREAD_PRIORITY";
    final static public String PREF_SOUND_ENGINE = "PREF_SOUND_ENGINE";

    final static public String PREF_THREADED_VIDEO = "PREF_THREADED_VIDEO";
    final static public String PREF_DOUBLE_BUFFER = "PREF_DOUBLE_BUFFER";

    final static public String PREF_FORCE_GLES10 = "PREF_FORCE_GLES10";
    final static public String PREF_PXASP1 = "PREF_PXASP1";

    final static public String PREF_BEAM2X = "PREF_BEAM2X";
    final static public String PREF_ANTIALIAS = "PREF_ANTIALIAS";
    final static public String PREF_FLICKER = "PREF_FLICKER";

    final static public String PREF_FILTER_FAVORITES = "PREF_FILTER_FAVORITES";
    final static public String PREF_FILTER_CLONES = "PREF_FILTER_CLONES";
    final static public String PREF_FILTER_NOTWORKING = "PREF_FILTER_NOTWORKING";
    final static public String PREF_FILTER_YGTE = "PREF_FILTER_YGTE";
    final static public String PREF_FILTER_YLTE = "PREF_FILTER_YLTE";
    final static public String PREF_FILTER_MANUF = "PREF_FILTER_MANUF";
    final static public String PREF_FILTER_DRVSRC = "PREF_FILTER_DRVSRC";
    final static public String PREF_FILTER_CATEGORY = "PREF_FILTER_CATEGORY";
    final static public String PREF_FILTER_KEYWORD = "PREF_FILTER_KEYWORD";

    final static public String PREF_OVERLAY_INTENSITY = "PREF_OVERLAY_INTENSITY";

    final static public int LOW = 1;
    final static public int NORMAL = 2;
    final static public int HIGHT = 2;

    final static public int PREF_RENDER_SW = 1;
    final static public int PREF_RENDER_GL = 2;

    final static public int PREF_DIGITAL_DPAD = 1;
    final static public int PREF_DIGITAL_STICK = 2;
    final static public int PREF_ANALOG_FAST = 3;
    final static public int PREF_ANALOG_PRETTY = 4;

    final static public int PREF_INPUT_DEFAULT = 1;
    final static public int PREF_INPUT_USB_AUTO = 2;
    final static public int PREF_INPUT_ICADE = 3;
    final static public int PREF_INPUT_ICP = 4;

    final public static int PREF_ORIGINAL = 3;
    final public static int PREF_15X = 4;
    final public static int PREF_20X = 5;
    final public static int PREF_25X = 6;
    final public static int PREF_3X = 7;
    final public static int PREF_35X = 8;
    final public static int PREF_4X = 9;
    final public static int PREF_45X = 10;
    final public static int PREF_5X = 11;
    final public static int PREF_55X = 12;
    final public static int PREF_6X = 13;
    final public static int PREF_SCALE = 1;
    final public static int PREF_STRETCH = 2;

    final public static String PREF_OVERLAY_NONE = "none";

    final public static int PREF_AUTOMAP_DEFAULT = 1;
    final public static int PREF_AUTOMAP_THUMBS_AS_L2R2 = 2;
    final public static int PREF_AUTOMAP_THUMBS_AS_SELECTSTART = 3;
    final public static int PREF_AUTOMAP_L1L2_AS_L2R2 = 4;
    final public static int PREF_AUTOMAP_L1L2_AS_SELECTSTART = 5;

    final public static int PREF_SNDENG_AUDIOTRACK = 1;
    final public static int PREF_SNDENG_AUDIOTRACK_HIGH = 2;
    final public static int PREF_SNDENG_OPENSL = 3;
    final public static int PREF_SNDENG_OPENSL_LOW = 4;

    protected GameFilterPrefs gameFilterPrefs = null;

    public GameFilterPrefs getGameFilterPrefs() {
        return gameFilterPrefs;
    }

    protected MamePlayingActivity mm = null;

    public PrefsHelper(MamePlayingActivity value) {
        mm = value;
        gameFilterPrefs = new GameFilterPrefs(mm);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                          String key) {
    }

    public void resume() {
        Context context = mm.getApplicationContext();
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    public void pause() {

        Context context = mm.getApplicationContext();
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    protected SharedPreferences getSharedPreferences() {
        Context context = mm.getApplicationContext();
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public int getPortraitScaleMode() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_PORTRAIT_SCALING_MODE,
                        PrefsHelper.PREF_STRETCH + "")).intValue();
    }

    public int getLandscapeScaleMode() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_LANDSCAPE_SCALING_MODE,
                        PrefsHelper.PREF_STRETCH + "")).intValue();
    }

    public String getPortraitOverlayFilterValue() {
        return getSharedPreferences().getString(PREF_PORTRAIT_OVERLAY,
                PrefsHelper.PREF_OVERLAY_NONE);
    }

    public String getLandscapeOverlayFilterValue() {
        return getSharedPreferences().getString(PREF_LANDSCAPE_OVERLAY,
                PrefsHelper.PREF_OVERLAY_NONE);
    }

    public boolean isPortraitTouchController() {
        return getSharedPreferences().getBoolean(
                PREF_PORTRAIT_TOUCH_CONTROLLER, true);
    }

    public boolean isPortraitBitmapFiltering() {
        return getSharedPreferences().getBoolean(
                PREF_PORTRAIT_BITMAP_FILTERING, false);
    }

    public boolean isPortraitFullscreen() {
        return getSharedPreferences().getBoolean(PREF_PORTRAIT_FULLSCREEN,
                false);
    }

    public boolean isLandscapeTouchController() {
        return getSharedPreferences().getBoolean(
                PREF_LANDSCAPE_TOUCH_CONTROLLER, true);
    }

    public boolean isLandscapeBitmapFiltering() {
        return getSharedPreferences().getBoolean(
                PREF_LANDSCAPE_BITMAP_FILTERING, false);
    }

    public String getDefinedKeys() {

        SharedPreferences p = getSharedPreferences();

        StringBuffer defaultKeys = new StringBuffer();



        return p.getString(PREF_DEFINED_KEYS, defaultKeys.toString());

    }

    public int getTrackballSensitivity() {
        // return
        // Integer.valueOf(getSharedPreferences().getString(PREF_TRACKBALL_SENSITIVITY,"3")).intValue();
        return getSharedPreferences().getInt(PREF_TRACKBALL_SENSITIVITY, 3);
    }

    public boolean isTrackballNoMove() {
        return getSharedPreferences().getBoolean(PREF_TRACKBALL_NOMOVE, false);
    }

    public int getVideoRenderMode() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_GLOBAL_VIDEO_RENDER_MODE,
                        "2")).intValue();
    }

    public int getEmulatedResolution() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_GLOBAL_RESOLUTION, "1"))
                .intValue();
    }

    public int getEmulatedSpeed() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_GLOBAL_SPEED, "-1"))
                .intValue();
    }

    public boolean isSoundSync() {
        return getSharedPreferences().getBoolean(PREF_GLOBAL_SOUND_SYNC, false);
    }

    public int getForcedPixelAspect() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_GLOBAL_FORCE_PXASPECT,
                        "0")).intValue();
    }

    public boolean isHiscore() {
        return getSharedPreferences().getBoolean(PREF_GLOBAL_HISCORE, false);
    }

    public boolean isWarnOnExit() {
        return getSharedPreferences()
                .getBoolean(PREF_GLOBAL_WARN_ON_EXIT, true);
    }

    public boolean isNotifyWhenSuspend() {
        return getSharedPreferences().getBoolean(
                PREF_GLOBAL_SUSPEND_NOTIFICATION, true);
    }

    public int getFrameSkipValue() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_GLOBAL_FRAMESKIP, "-1"))
                .intValue();
    }

    public boolean isThrottle() {
        return getSharedPreferences().getBoolean(PREF_GLOBAL_THROTTLE, true);
    }

    public int getVSync() {
        int r = Integer.valueOf(
                getSharedPreferences().getString(PREF_GLOBAL_VSYNC, "-1"))
                .intValue();
        if (r == 3) {
            WindowManager wm = (WindowManager) mm
                    .getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            r = (int) (display.getRefreshRate() * 100);
        }
        return r;
    }

    public int getSoundValue() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_GLOBAL_SOUND, "44100"))
                .intValue();
    }

    public boolean isFPSShowed() {
        return getSharedPreferences().getBoolean(PREF_GLOBAL_SHOW_FPS, false);
    }

    public boolean isCheat() {
        return getSharedPreferences().getBoolean(PREF_GLOBAL_CHEAT, false);
    }

    public boolean isAutosave() {
        return getSharedPreferences().getBoolean(PREF_GLOBAL_AUTOSAVE, false);
    }

    public boolean isDebugEnabled() {
        return getSharedPreferences().getBoolean(PREF_GLOBAL_DEBUG, false);
    }

    public boolean isIdleWait() {
        return getSharedPreferences().getBoolean(PREF_GLOBAL_IDLE_WAIT, true);
    }

    public boolean isHideStick() {
        return getSharedPreferences().getBoolean(PREF_HIDE_STICK, false);
    }

    public boolean isDisabledRightStick() {
        return getSharedPreferences().getBoolean(PREF_DISABLE_RIGHT_STICK,
                false);
    }

    public boolean isAnimatedInput() {
        return getSharedPreferences().getBoolean(PREF_ANIMATED_INPUT, true);
    }

    public boolean isTouchDZ() {
        return getSharedPreferences().getBoolean(PREF_TOUCH_DZ, true);
    }

    public boolean isShowInfoWarnings() {
        return false;
    }

    public int getControllerType() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_CONTROLLER_TYPE, "3"))
                .intValue();
    }

    public boolean isLightgun() {

        if (getSharedPreferences().getBoolean(PREF_TILT_TOUCH, false)
                && this.isTiltSensor())
            return true;

        if (getSharedPreferences().getBoolean(PREF_LIGHTGUN, false)
                && !this.isTiltSensor())
            return true;

        return false;
    }

    public int getStickWays() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_STICK_TYPE, "-1"))
                .intValue();
    }

    public int getNumButtons() {
        int n = Integer.valueOf(
                getSharedPreferences().getString(PREF_NUMBUTTONS, "-1"))
                .intValue();
        if (n == 33)
            n = 3;
        return n;
    }

    public boolean isBplusX() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_NUMBUTTONS, "-1"))
                .intValue() == 33;
    }

    public int getInputExternal() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_INPUT_EXTERNAL, "2"))
                .intValue();
    }

    public int getAutomapOptions() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_AUTOMAP_OPTIONS, "2"))
                .intValue();
    }

    public int getAnalogDZ() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_ANALOG_DZ, "2"))
                .intValue();
    }

    public int getGamepadDZ() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_GAMEPAD_DZ, "3"))
                .intValue();
    }

    public boolean isVibrate() {
        return getSharedPreferences().getBoolean(PREF_VIBRATE, false);
    }

    public int getAutofireValue() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_AUTOFIRE, "0"))
                .intValue();
    }

    public String getROMsDIR() {
        return getSharedPreferences().getString(PREF_ROMsDIR, null);
    }

    public void setROMsDIR(String value) {
        // PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(PREF_ROMsDIR, value);
        editor.commit();
    }

    public String getDefinedControlLayoutLand() {
        return getSharedPreferences().getString(PREF_DEFINED_CONTROL_LAYOUT,
                null);
    }

    public void setDefinedControlLayoutLand(String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(PREF_DEFINED_CONTROL_LAYOUT, value);
        editor.commit();
    }

    public String getDefinedControlLayoutPortrait() {
        return getSharedPreferences().getString(PREF_DEFINED_CONTROL_LAYOUT_P,
                null);
    }

    public void setDefinedControlLayoutPortrait(String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(PREF_DEFINED_CONTROL_LAYOUT_P, value);
        editor.commit();
    }

    public boolean isTiltSensor() {
        return getSharedPreferences().getBoolean(PREF_TILT_SENSOR, false);
    }

    public int getTiltSensitivity() {
        return getSharedPreferences().getInt(PREF_TILT_SENSITIVITY, 6);
    }

    public int getTiltVerticalNeutralPos() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_TILT_NEUTRAL, "5"))
                .intValue();
    }

    public int getTiltDZ() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_TILT_DZ, "3")).intValue();
    }

    public boolean isTiltAnalog() {
        return getSharedPreferences().getBoolean(PREF_TILT_ANALOG, true);
    }

    public boolean isTiltTouch() {
        return getSharedPreferences().getBoolean(PREF_TILT_TOUCH, false);
    }

    public boolean isSwappedYZ() {
        return getSharedPreferences().getBoolean(PREF_TILT_SWAP_YZ, false);
    }

    public int getButtonsSize() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_BUTTONS_SIZE, "3"))
                .intValue();
    }

    public int getStickSize() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_STICK_SIZE, "3"))
                .intValue();
    }

    public int getVideoThreadPriority() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_VIDEO_THREAD_PRIORITY,
                        "2")).intValue();
    }

    public int getMainThreadPriority() {
        return Integer.valueOf(
                getSharedPreferences()
                        .getString(PREF_MAIN_THREAD_PRIORITY, "2")).intValue();
    }

    public int getSoundEngine() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_SOUND_ENGINE, "1"))
                .intValue();
    }

    public boolean isThreadedVideo() {
        return getSharedPreferences().getBoolean(PREF_THREADED_VIDEO, true);
    }

    public boolean isDoubleBuffer() {
        return getSharedPreferences().getBoolean(PREF_DOUBLE_BUFFER, true);
    }

    public boolean isForcedGLES10() {
        return getSharedPreferences().getBoolean(PREF_FORCE_GLES10, false);
    }

    public boolean isPlayerXasPlayer1() {
        return getSharedPreferences().getBoolean(PREF_PXASP1, false);
    }

    public boolean isVectorBeam2x() {
        return getSharedPreferences().getBoolean(PREF_BEAM2X, true);
    }

    public boolean isVectorAntialias() {
        return getSharedPreferences().getBoolean(PREF_ANTIALIAS, true);
    }

    public boolean isVectorFlicker() {
        return getSharedPreferences().getBoolean(PREF_FLICKER, false);
    }

    public int getEffectOverlayIntensity() {
        return Integer.valueOf(
                getSharedPreferences().getString(PREF_OVERLAY_INTENSITY, "3"))
                .intValue();
    }

}
