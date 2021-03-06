package com.mobile.emulatormodule.mame.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mobile.emulatormodule.mame.Emulator;
import com.mobile.emulatormodule.MamePlayingActivity;
import com.mobile.emulatormodule.mame.helpers.PrefsHelper;

public class GameFilterPrefs {

	protected int favorites = 0;
	protected int clones = 0;
	protected int not_working = 0;
	protected int gte_year = -1;
	protected int lte_year = -1;
	protected int manufacturer = -1;
	protected int category = -1;
	protected int drvsrc = -1;
	protected String keyword = "";

	protected MamePlayingActivity mm = null;

	public GameFilterPrefs(MamePlayingActivity value) {
		mm = value;
	}

	protected SharedPreferences getSharedPreferences() {
		Context context = mm.getApplicationContext();
		return PreferenceManager.getDefaultSharedPreferences(context);
	}

	public boolean readValues() {
		boolean b = false;
		int value = 0;
		String str = null;

		value = getSharedPreferences().getBoolean(
				PrefsHelper.PREF_FILTER_FAVORITES, false) ? 1 : 0;
		b = value != favorites || b;
		favorites = value;
		value = getSharedPreferences().getBoolean(
				PrefsHelper.PREF_FILTER_CLONES, false) ? 1 : 0;
		b = value != clones || b;
		clones = value;
		value = getSharedPreferences().getBoolean(
				PrefsHelper.PREF_FILTER_NOTWORKING, false) ? 1 : 0;
		b = value != not_working || b;
		not_working = value;
		value = Integer.valueOf(
				getSharedPreferences().getString(PrefsHelper.PREF_FILTER_YGTE,
						"-1")).intValue();
		b = value != gte_year || b;
		gte_year = value;
		value = Integer.valueOf(
				getSharedPreferences().getString(PrefsHelper.PREF_FILTER_YLTE,
						"-1")).intValue();
		b = value != lte_year || b;
		lte_year = value;
		value = Integer.valueOf(
				getSharedPreferences().getString(PrefsHelper.PREF_FILTER_MANUF,
						"-1")).intValue();
		b = value != manufacturer || b;
		manufacturer = value;
		value = Integer.valueOf(
				getSharedPreferences().getString(
						PrefsHelper.PREF_FILTER_CATEGORY, "-1")).intValue();
		b = value != category || b;
		category = value;
		value = Integer.valueOf(
				getSharedPreferences().getString(
						PrefsHelper.PREF_FILTER_DRVSRC, "-1")).intValue();
		b = value != drvsrc || b;
		drvsrc = value;
		str = getSharedPreferences().getString(PrefsHelper.PREF_FILTER_KEYWORD,
				"");
		b = !str.equals(keyword) || b;
		keyword = str;
		return b;
	}

	public void sendValues() {
		Emulator.setValue(Emulator.FILTER_FAVORITES, favorites);
		Emulator.setValue(Emulator.FILTER_CLONES, clones);
		Emulator.setValue(Emulator.FILTER_NOTWORKING, not_working);
		Emulator.setValue(Emulator.FILTER_GTE_YEAR, gte_year);
		Emulator.setValue(Emulator.FILTER_LTE_YEAR, lte_year);
		Emulator.setValue(Emulator.FILTER_MANUFACTURER, manufacturer);
		Emulator.setValue(Emulator.FILTER_CATEGORY, category);
		Emulator.setValue(Emulator.FILTER_DRVSRC, drvsrc);
		Emulator.setValueStr(Emulator.FILTER_KEYWORD, keyword);
	}

}
