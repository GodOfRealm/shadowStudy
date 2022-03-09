package com.mobile.emulatormodule.mame.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;

import com.mobile.emulatormodule.MamePlayingActivity;

@SuppressLint("NewApi")
public class EmulatorViewGLExt extends EmulatorViewGL implements
		android.view.View.OnSystemUiVisibilityChangeListener {

	protected int mLastSystemUiVis;

	@SuppressLint("NewApi")
	public void setMAME4droid(MamePlayingActivity mm) {

		if (mm == null) {
			setOnSystemUiVisibilityChangeListener(null);
			return;
		}
		super.setMAME4droid(mm);
		setNavVisibility(true);
		setOnSystemUiVisibilityChangeListener(this);
	}

	public EmulatorViewGLExt(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	Runnable mNavHider = new Runnable() {
		@Override
		public void run() {
			setNavVisibility(false);
		}
	};

	@Override
	protected void onWindowVisibilityChanged(int visibility) {
		super.onWindowVisibilityChanged(visibility);

		// When we become visible, we show our navigation elements briefly
		// before hiding them.
		/**/
		setNavVisibility(true);
		getHandler().postDelayed(mNavHider, 2000);
	}

	@Override
	public void onSystemUiVisibilityChange(int visibility) {
		// Detect when we go out of low-profile mode, to also go out
		// of full screen. We only do this when the low profile mode
		// is changing from its last state, and turning off.

		int diff = mLastSystemUiVis ^ visibility;
		mLastSystemUiVis = visibility;

		if ((diff & SYSTEM_UI_FLAG_HIDE_NAVIGATION) != 0
				&& (visibility & SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0) {
			setNavVisibility(true);
		} else if ((diff & SYSTEM_UI_FLAG_LOW_PROFILE) != 0
				&& (visibility & SYSTEM_UI_FLAG_LOW_PROFILE) == 0) {
			setNavVisibility(true);
		}
	}

	void setNavVisibility(boolean visible) {
		if (mm == null)
			return;
		int newVis = 0;

		// If we are now visible, schedule a timer for us to go invisible.
		if (visible) {
			Handler h = getHandler();
			if (h != null) {
				h.removeCallbacks(mNavHider);
				h.postDelayed(mNavHider, 3000);
			}
		}

		// Set the new desired visibility.
		setSystemUiVisibility(newVis);
	}

}
