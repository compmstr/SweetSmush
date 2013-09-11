package com.undi.sweetsmush.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.view.SurfaceHolder;

public abstract class Drawer {

	protected SurfaceHolder holder;
	protected Context context;
	protected int screenW, screenH;
	protected Paint whiteText;
	
	public Drawer(SurfaceHolder holder, Context context){
		this.context = context;
		this.holder = holder;

		whiteText = new Paint();
		whiteText.setColor(Color.WHITE);
		whiteText.setTextAlign(Align.LEFT);
		whiteText.setStyle(Style.STROKE);
		whiteText.setAntiAlias(true);
	}
	
	protected abstract void doDraw(Canvas c);

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
