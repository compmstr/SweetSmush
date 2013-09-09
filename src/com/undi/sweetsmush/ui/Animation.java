package com.undi.sweetsmush.ui;

public abstract class Animation {
	protected Runnable callback;
	
	protected int durationMs;
	protected long endTime;
	protected boolean done = false;
	protected boolean gameStopping = false;

	public Animation(Runnable callback, int durationMs, boolean gameStopping){
		this(callback, durationMs);
		this.gameStopping = gameStopping;
	}
	public Animation(Runnable callback, int durationMs){
		this.durationMs = durationMs;
		this.endTime = System.currentTimeMillis() + endTime;
		this.callback = callback;
	}
	
	public void doCallback(){
		if(callback != null){
			callback.run();
		}
	}
	
	public boolean isDone(){ return done; }
	public boolean isGameStopping(){ return gameStopping; }
	
	protected float getInterpolation(long curTime){
		double remTime = endTime - curTime;
		double remPercent = remTime / durationMs;
		return (float) (1.0f - remPercent);
	}
	
	public abstract void update(long curTime);
}
