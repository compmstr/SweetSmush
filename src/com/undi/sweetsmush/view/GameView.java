package com.undi.sweetsmush.view;

import java.lang.Thread.State;

import com.undi.sweetsmush.game.Game;
import com.undi.sweetsmush.ui.GameDraw;
import com.undi.sweetsmush.ui.GameInput;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Callback {
	private Context context;
	private GameInput input;
	private GameDraw drawer;
	private GameViewThread thread;
	private Game game;

	public GameView(Context context) {
		super(context);
		this.context = context;
		this.game = new Game();
		this.drawer = new GameDraw(getHolder(), context, game);
		this.input = new GameInput();
		this.thread = new GameViewThread(drawer, input);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		drawer.onResize(width, height);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//Recreate the thread if needed
		if(thread.getState() == State.TERMINATED){
			thread = new GameViewThread(drawer, input);
		}
		Log.d("GameView" , "=====Surface Created======");
		thread.setRunning(true);
		if(thread.getState() == State.NEW){
			thread.start();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		thread.setRunning(false);
	}

}
