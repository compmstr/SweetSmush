package com.undi.sweetsmush.ui;

public abstract class Animation {
	protected Runnable callback;
	
	protected int durationMs;
	protected long endTime;
	protected boolean done = false;

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
	
	protected float getInterpolation(long curTime){
		double remTime = endTime - curTime;
		double remPercent = remTime / durationMs;
		return (float) (1.0f - remPercent);
	}
	
	public abstract void update(long curTime);
}
