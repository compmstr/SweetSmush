package com.undi.sweetsmush.ui;

import com.undi.sweetsmush.game.Game;
import com.undi.sweetsmush.game.GameHolder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class GameDraw extends Drawer {
	private GameHolder gameHolder;
	
	private Paint bgPaint;
	
	private final float boardDrawOffsetXPercent = 0.02f;
	private final float boardDrawOffsetYPercent = 0.25f;
	
	public int getTileWidth(){
		return BoardPieceGraphicMgr.tileW;
	}
	public int getTileHeight(){
		return BoardPieceGraphicMgr.tileH;
	}
	public int getBoardDrawOffsetX(){
		return (int) (screenW * boardDrawOffsetXPercent);
	}
	public int getBoardDrawOffsetY(){
		return (int) (screenH * boardDrawOffsetYPercent);
	}
	
	public GameDraw(SurfaceHolder holder, Context context, GameHolder gameHolder){
		super(holder, context);
		this.gameHolder = gameHolder;
		
		bgPaint = new Paint();
		bgPaint.setStyle(Paint.Style.FILL);
		bgPaint.setColor(Color.GRAY);
	}
	
	/**
	 * Internal function to actually do the drawing
	 * @param c
	 */
	@Override
	protected void doDraw(Canvas c){
		Game game = gameHolder.getGame();
		c.drawRect(c.getClipBounds(), bgPaint);
		if(game != null){
			game.getLevel().getBoard().draw(getBoardDrawOffsetX(), getBoardDrawOffsetY(), c);
		}
		c.drawText("test text", 10, whiteText.getTextSize() + 5, whiteText);
	}
	
	@Override
	public void onResize(int w, int h){
		super.onResize(w, h);
		BoardPieceGraphicMgr.onResize(w, h);
	}
}
