package com.undi.sweetsmush.ui;

import com.undi.sweetsmush.view.GameViewThread;

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
	
	private void doDraw(Canvas c){
		
	}

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
