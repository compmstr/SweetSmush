package com.undi.sweetsmush.ui;

import android.view.MotionEvent;

import com.undi.sweetsmush.game.GameHolder;
import com.undi.sweetsmush.view.SweetSmushView;

public class GameInput extends Input {
	private GameHolder gameHolder;

	public GameInput(SweetSmushView mainView, GameHolder gameHolder) {
		super(mainView);
		this.gameHolder = gameHolder;
	}

	@Override
	protected void onActionDown(MotionEvent e) {
	}

	@Override
	protected void onActionMove(MotionEvent e) {
	}

	@Override
	protected void onActionUp(MotionEvent e) {
	}

}
