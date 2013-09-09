package com.undi.sweetsmush.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameDraw {
	private SurfaceHolder holder;
	private Context context;
	private int screenW, screenH;
	
	public GameDraw(SurfaceHolder holder, Context context){
		this.context = context;
		this.holder = holder;
	}
	
	/**
	 * Internal function to actually do the drawing
	 * @param c
	 */
	private void doDraw(Canvas c){
		
	}

	/**
	 * Public function to lock canvas, draw, and unlock canvas
	 */
	public void draw(){
		Canvas c = null;
		try{
			c = holder.lockCanvas();
			synchronized(holder){
				doDraw(c);
			}
		}finally {
			if(c != null){
				holder.unlockCanvasAndPost(c);
			}
		}
	}
	
	public void onResize(int newW, int newH){
		this.screenW = newW;
		this.screenH = newH;
	}
}
