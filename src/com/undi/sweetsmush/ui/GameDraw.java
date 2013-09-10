package com.undi.sweetsmush.ui;

import com.undi.sweetsmush.game.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameDraw {
	private SurfaceHolder holder;
	private Context context;
	private int screenW, screenH;
	private Game game;
	
	public GameDraw(SurfaceHolder holder, Context context, Game game){
		this.context = context;
		this.holder = holder;
		this.game = game;
	}
	
	/**
	 * Internal function to actually do the drawing
	 * @param c
	 */
	private void doDraw(Canvas c){
		int boardDrawX = (int) (screenW * 0.02);
		int boardDrawY = 10;
		game.getLevel().getBoard().draw(boardDrawX, boardDrawY, c);
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
