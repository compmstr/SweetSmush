package com.undi.sweetsmush.ui;

import com.undi.sweetsmush.view.SweetSmushView;

import android.view.MotionEvent;

public abstract class Input {
	
	protected SweetSmushView mainView;
	
	public Input(SweetSmushView mainView) {
		this.mainView = mainView;
	}
	
	abstract protected void onActionDown(MotionEvent e);
	abstract protected void onActionMove(MotionEvent e);
	abstract protected void onActionUp(MotionEvent e);

	public void onTouchEvent(MotionEvent e){
		int action = e.getAction();
		
		switch(action){
		case MotionEvent.ACTION_DOWN:
			onActionDown(e);
			break;
		case MotionEvent.ACTION_MOVE:
			onActionMove(e);
			break;
		case MotionEvent.ACTION_UP:
			onActionUp(e);
			break;
		}
	}
}
