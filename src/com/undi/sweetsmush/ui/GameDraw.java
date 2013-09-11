package com.undi.sweetsmush.ui;

import com.undi.sweetsmush.game.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.view.SurfaceHolder;

public class GameDraw extends Drawer {
	private Game game;
	
	public GameDraw(SurfaceHolder holder, Context context, Game game){
		super(holder, context);
		this.game = game;
	}
	
	/**
	 * Internal function to actually do the drawing
	 * @param c
	 */
	@Override
	protected void doDraw(Canvas c){
		int boardDrawX = (int) (screenW * 0.02);
		int boardDrawY = 10;
		game.getLevel().getBoard().draw(boardDrawX, boardDrawY, c);
		c.drawText("test text", 0, 0, whiteText);
	}
}
