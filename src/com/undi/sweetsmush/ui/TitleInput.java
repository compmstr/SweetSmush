package com.undi.sweetsmush.ui;

import android.view.MotionEvent;
import com.undi.sweetsmush.view.SweetSmushView;

public class TitleInput extends Input {

	public TitleInput(SweetSmushView mainView) {
		super(mainView);
	}

	@Override
	protected void onActionDown(MotionEvent e) {
		mainView.switchView(SweetSmushView.State.GAME);
	}

	@Override
	protected void onActionMove(MotionEvent e) {

	}

	@Override
	protected void onActionUp(MotionEvent e) {
	}

}
