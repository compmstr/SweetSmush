package com.undi.sweetsmush.ui;

import com.undi.sweetsmush.view.SweetSmushView;

import android.view.MotionEvent;

public class TitleInput {
	
	SweetSmushView mainView;
	
	public TitleInput(SweetSmushView mainView) {
		this.mainView = mainView;
	}

	public void onTouchEvent(MotionEvent e){
		int action = e.getAction();
		
		switch(action){
		case MotionEvent.ACTION_DOWN:
			mainView.switchView(SweetSmushView.State.GAME);
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			break;
		}
	}
}
