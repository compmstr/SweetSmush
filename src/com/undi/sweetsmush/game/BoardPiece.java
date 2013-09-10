package com.undi.sweetsmush.game;

import com.undi.sweetsmush.R;
import com.undi.sweetsmush.ui.BoardPieceGraphicMgr;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

public class BoardPiece {

	public static enum Category { CAKE, CUPCAKE, CHOCOLATE, DONUT, WILD, NONE };
	public static enum SubType { REGULAR, HORIZONTAL, VERTICAL, BOOM };
	
	protected Category category;
	protected SubType subType;
	protected static BoardPieceGraphicMgr.BoardPieceGraphic graphic;
	protected Rect spriteSrcRect = new Rect();
	protected Rect pos = new Rect();
	
	public static void initGraphic(){
		//TODO -- replace 0 with resource ID of piece graphic set
		graphic = BoardPieceGraphicMgr.registerGraphics(BoardPiece.class, R.drawable.candy_sprites, 4, 4);
	}
	
	private BoardPiece(Point loc){
		this.category = Category.NONE;
		pos.left = loc.x;
		pos.right = loc.x;
		pos.top = loc.y;
		pos.bottom = loc.y;
	}
	
	public BoardPiece(Category type, SubType sub){
		this.category = type;
		this.subType = sub;
	}
	
	public boolean matches(BoardPiece other){
		return this.category == other.category ||
				other.category == Category.WILD;
	}
	
	public Category getCategory() { return this.category; }
	
	/**
	 * Called on match, does the piece-defined behavior
	 * @param board - the board the match was done on
	 * @param x - coordinate of piece
	 * @param y - coordinate of piece
	 */
	public void onMatch(Board board, int x, int y){
		//By default, just remove yourself
		board.removePiece(x, y);
	}
	/**
	 * Called when a piece's neighbor is Removed, but this piece isn't
	 */
	public void onRemoveAdjacent(){
	}
	
	private void rescaleSpriteRects(){
		int spriteWidth = graphic.scaled.getWidth() / Category.values().length;
		int spriteHeight = graphic.scaled.getHeight() / SubType.values().length;
		
		spriteSrcRect.left = spriteWidth * category.ordinal();
		spriteSrcRect.right = spriteSrcRect.left + spriteWidth;
		spriteSrcRect.top = spriteWidth * subType.ordinal();
		spriteSrcRect.bottom = spriteSrcRect.top + spriteHeight;
		
		pos.bottom = spriteHeight + pos.top;
		pos.right = spriteWidth + pos.left;
	}
	
	public void draw(Canvas canvas, int offsetX, int offsetY){
		if(category.ordinal() < Category.WILD.ordinal()){
			pos.left += offsetX;
			pos.right += offsetX;
			pos.top += offsetY;
			pos.bottom += offsetY;
			canvas.drawBitmap(graphic.scaled, spriteSrcRect, pos, null);
			pos.left -= offsetX;
			pos.right -= offsetX;
			pos.top -= offsetY;
			pos.bottom -= offsetY;
		}
	}
}
