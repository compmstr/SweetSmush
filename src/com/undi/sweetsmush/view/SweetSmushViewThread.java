package com.undi.sweetsmush.view;

public class SweetSmushViewThread extends Thread {
	private boolean running = false;
	private SweetSmushView view;

	public SweetSmushViewThread(SweetSmushView view) {
		this.view = view;
	}

	@Override
	public void run(){
		while(running){
			view.getDrawer().draw();
		}
	}
	
	public void setRunning(boolean b){
		running = b;
	}
}
