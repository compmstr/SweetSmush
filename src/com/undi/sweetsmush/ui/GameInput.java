package com.undi.sweetsmush.ui;

import android.view.MotionEvent;

import com.undi.sweetsmush.game.Game;
import com.undi.sweetsmush.view.SweetSmushView;

public class GameInput extends Input {
	private Game game;

	public GameInput(SweetSmushView mainView, Game game) {
		super(mainView);
		this.game = game;
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
