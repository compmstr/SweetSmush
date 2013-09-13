package com.undi.sweetsmush.view;


import com.undi.sweetsmush.game.Game;
import com.undi.sweetsmush.game.GameHolder;
import com.undi.sweetsmush.ui.BoardPieceGraphicMgr;
import com.undi.sweetsmush.ui.Drawer;
import com.undi.sweetsmush.ui.GameDraw;
import com.undi.sweetsmush.ui.GameInput;
import com.undi.sweetsmush.ui.Input;
import com.undi.sweetsmush.ui.TitleDraw;
import com.undi.sweetsmush.ui.TitleInput;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class SweetSmushView extends SurfaceView implements Callback, GameHolder {
	
	public static enum State { TITLE, GAME };
	private State curView = null;
	private Input input;
	private Drawer drawer;
	private SweetSmushViewThread thread;
	private Game game;
	private int screenW, screenH;
	
	public SweetSmushView(Context context, AttributeSet attrs) {
		super(context, attrs);
		getHolder().addCallback(this);
		setFocusable(true);
		
		thread = new SweetSmushViewThread(this);
		switchView(State.TITLE);
	}

	public void switchView(State newView){
		if(curView != newView){
			switch(newView){
			case GAME:
				BoardPieceGraphicMgr.init(getContext(), screenW, screenH);
				this.game = new Game();
				drawer = new GameDraw(getHolder(), getContext(), this);
				input = new GameInput(this, this, (GameDraw) drawer);
				break;
			case TITLE:
				input = new TitleInput(this);
				drawer = new TitleDraw(getHolder(), getContext());
				break;
			}
			if(screenW > 0 && screenH > 0){
				drawer.onResize(screenW, screenH);
			}
			curView = newView;
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e){
		if(input != null){
			input.onTouchEvent(e);
		}
		return true;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		if(drawer != null){
			this.screenW = width;
			this.screenH = height;
			drawer.onResize(width, height);
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//Recreate the thread if needed
		if(thread.getState() == Thread.State.TERMINATED){
			thread = new SweetSmushViewThread(this);
		}
		Log.d("GameView" , "=====Surface Created======");
		thread.setRunning(true);
		if(thread.getState() == Thread.State.NEW){
			thread.start();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		thread.setRunning(false);
	}
	
	public Drawer getDrawer(){ return drawer; }
	@Override
	public Game getGame() { return game; }
}
