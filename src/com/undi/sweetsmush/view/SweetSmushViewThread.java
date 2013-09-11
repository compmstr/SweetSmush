package com.undi.sweetsmush.view;

import com.undi.sweetsmush.ui.Drawer;
import com.undi.sweetsmush.ui.GameDraw;
import com.undi.sweetsmush.ui.GameInput;

public class SweetSmushViewThread extends Thread {
	private boolean running = false;
	private Drawer drawer;

	public SweetSmushViewThread(Drawer draw) {
		this.drawer = draw;
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
