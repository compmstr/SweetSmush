package com.undi.sweetsmush.game;

import com.undi.sweetsmush.R;
import com.undi.sweetsmush.ui.BoardPieceGraphicMgr;
import com.undi.sweetsmush.ui.BoardPieceGraphicMgr.BoardPieceGraphic;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

public class BoardPiece {

	public static enum Category { CAKE, CUPCAKE, CHOCOLATE, DONUT, WILD, NONE };
	public static enum SubType { REGULAR, HORIZONTAL, VERTICAL, BOOM };
	
	protected Category category;
	protected SubType subType;
	protected static BoardPieceGraphic graphic = null;
	protected Rect spriteSrcRect = new Rect();
	protected Rect dest = new Rect();
	protected Point boardPos;
	
	private static BoardPieceGraphic getGraphic(){
		if(graphic == null){
			graphic = BoardPieceGraphicMgr.registerGraphics(BoardPiece.class, R.drawable.candy_sprites, 4, 4);
		}
		return graphic;
	}
	
	private BoardPiece(Point boardLoc){
		this.category = Category.NONE;
		int spriteWidth = getSpriteWidth();
		int spriteHeight = getSpriteHeight();
		dest.left = boardLoc.x * spriteWidth;
		dest.right = boardLoc.x * spriteWidth;
		dest.top = boardLoc.y * spriteHeight;
		dest.bottom = boardLoc.y * spriteHeight;
		boardPos = boardLoc;
	}
	
	public BoardPiece(Point boardLoc, Category type, SubType sub){
		this(boardLoc);
		this.category = type;
		this.subType = sub;
		rescaleSpriteRects();
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
	
	protected int getSpriteWidth(){
		return getGraphic().scaledTileW;
	}
	protected int getSpriteHeight(){
		return getGraphic().scaledTileH;
	}
	
	public void rescaleSpriteRects(){
		int spriteWidth = getSpriteWidth();
		int spriteHeight = getSpriteHeight();
		
		spriteSrcRect.left = spriteWidth * category.ordinal();
		spriteSrcRect.right = spriteSrcRect.left + spriteWidth;
		spriteSrcRect.top = spriteWidth * subType.ordinal();
		spriteSrcRect.bottom = spriteSrcRect.top + spriteHeight;
		
		dest.top = boardPos.y * spriteHeight;
		dest.left = boardPos.x * spriteWidth;
		dest.bottom = spriteHeight + dest.top;
		dest.right = spriteWidth + dest.left;
	}
	
	public void draw(Canvas canvas, int offsetX, int offsetY){
		if(category.ordinal() < Category.WILD.ordinal()){
			rescaleSpriteRects();
			dest.left += offsetX;
			dest.right += offsetX;
			dest.top += offsetY;
			dest.bottom += offsetY;
			canvas.drawBitmap(getGraphic().scaled, spriteSrcRect, dest, null);
			dest.left -= offsetX;
			dest.right -= offsetX;
			dest.top -= offsetY;
			dest.bottom -= offsetY;
		}
	}
}
