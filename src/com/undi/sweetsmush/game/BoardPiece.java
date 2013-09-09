package com.undi.sweetsmush.game;

import com.undi.sweetsmush.ui.BoardPieceGraphicMgr;

import android.graphics.Canvas;

public class BoardPiece {
	public static final BoardPiece NOTHING = new BoardPiece();
	
	public static enum Category { CAKE, CUPCAKE, CANDY, DONUT, WILD, NONE };
	
	protected Category category;
	protected static BoardPieceGraphicMgr.BoardPieceGraphic graphic;
	
	public static void initGraphic(){
		//TODO -- replace 0 with resource ID of piece graphic set
		graphic = BoardPieceGraphicMgr.registerGraphics(BoardPiece.class, 0);
	}
	
	private BoardPiece(){
		this.category = Category.NONE;
	}
	
	public BoardPiece(Category type){
		this.category = type;
	}
	
	public boolean matches(BoardPiece other){
		return this.category == other.getCategory();
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
	
	public void draw(Canvas canvas){
		
	}
}
