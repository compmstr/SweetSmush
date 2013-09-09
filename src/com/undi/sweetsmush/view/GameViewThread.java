package com.undi.sweetsmush.view;

import android.graphics.Canvas;

import com.undi.sweetsmush.ui.GameDraw;
import com.undi.sweetsmush.ui.GameInput;

public class GameViewThread extends Thread {
	private boolean running = false;
	private GameDraw drawer;
	private GameInput input;

	public GameViewThread(GameDraw draw, GameInput input) {
		this.drawer = draw;
		this.input = input;
	}

	@Override
	public void run(){
		while(running){
			drawer.draw();
		}
	}
	
	public void setRunning(boolean b){
		running = b;
	}
}
