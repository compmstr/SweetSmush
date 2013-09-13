package com.undi.sweetsmush.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Point;

import com.undi.sweetsmush.game.BoardPiece.Category;
import com.undi.sweetsmush.game.BoardPiece.SubType;
import com.undi.sweetsmush.ui.AnimationMgr;

public class Board {
	private List<BoardPiece> pieces;
	private int cols, rows;
	private Random rand;
	
	public static enum Move {UP, LEFT, DOWN, RIGHT};
	
	public Board(int rows, int cols, long seed){
		this.cols = cols;
		this.rows = rows;
		pieces = new ArrayList<BoardPiece>(rows * cols);
		rand = new Random(seed);
		
		startBoard();
	}
	
	private boolean isWinnable(){
		//TODO
		return true;
	}
	
	/**
	 * Fill in the board with basic pieces
	 */
	private void startBoard(){
		//TODO
		int size = rows * cols;
		Category[] categories = Category.values();
		SubType[] subTypes = SubType.values();
		int maxCat = Category.WILD.ordinal();
		int maxSubType = 1; //just basic pieces
		for(int i = 0; i < size; i++){
			Category pickedCategory = categories[rand.nextInt(maxCat)];
			SubType pickedSubType = subTypes[rand.nextInt(maxSubType)];
			Point boardPos = new Point(i % cols, i / cols);
			BoardPiece curPiece = new BoardPiece(boardPos, pickedCategory, pickedSubType);
			pieces.add(curPiece);
		}
	}
	
	/**
	 * Do falling animations, generate new pieces if needed, etc.
	 */
	public void update(){
		
	}
	
	public List<? extends BoardPiece> getPieces(){
		return pieces;
	}
	
	public void draw(int offsetX, int offsetY, Canvas c){
		for(BoardPiece piece : pieces){
			piece.draw(c, offsetX, offsetY);
		}
	}
	
	/**
	 * Check for a horizontal match including the provided point
	 * @param p - location to check for match at
	 * @return
	 */
	private boolean isHorizMatch(Point p){
		return isHorizMatch(p, pieces.get(getIdx(p)));
	}
	/**
	 * Check for a horizontal match with the piece at <p> set to <piece>
	 * @param p - location to check for match at
	 * @param piece - piece at location
	 * @return
	 */
	private boolean isHorizMatch(Point p, BoardPiece piece){
		int startIdx = getIdx(p);
		int numPieces = 1;
		//Search left
		for(int i = 1; i < (p.x - 1); i--){
			if(pieces.get(startIdx - i).matches(piece)){
				numPieces++;
			}else{
				break;
			}
		}
		//Search right
		for(int i = 0; i < (cols - (p.x - 1)); i++){
			if(pieces.get(startIdx + i).matches(piece)){
				numPieces++;
			}else{
				break;
			}
		}
		
		if(numPieces >= 3){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean isVertMatch(Point p){
		return isVertMatch(p, pieces.get(getIdx(p)));
	}
	private boolean isVertMatch(Point p, BoardPiece piece){
		int numPieces = 1;
		
		int startIdx = getIdx(p);
		//Search up
		for(int i = 1; i < (p.y - 1); i++){
			if(pieces.get(startIdx - (i * cols)).matches(piece)){
				numPieces++;
			}else{
				break;
			}
		}
		//Search down
		for(int i = 0; i < (rows - (p.y - 1)); i++){
			if(pieces.get(startIdx + (i * cols)).matches(piece)){
				numPieces++;
			}else{
				break;
			}
		}

		if(numPieces >= 3){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isValidMove(Point from, Move move){
		return isValidMove(getIdx(from.x, from.y), getIdx(from.x, from.y, move));
	}
	public boolean isValidMove(int fromIdx, int toIdx){
		//make sure they're neighbors
		if(Math.abs(toIdx - fromIdx) != 1 &&
				Math.abs(toIdx - fromIdx) != cols){
			return false;
		}
		BoardPiece fromPiece = pieces.get(fromIdx);
		BoardPiece toPiece = pieces.get(toIdx);
		
		return fromPiece.matches(toPiece) || toPiece.matches(fromPiece);
	}
	
	/**
	 * Returns the index into the pieces array for the given
	 *   column and row
	 * @param col
	 * @param row
	 * @return
	 */
	public int getIdx(int col, int row){
		if((col < 0 || col >= cols) ||
				(row < 0 || row >= rows)){
			return -1;
		}
		return (row * cols) + col;
	}
	public int getIdx(Point p){
		return getIdx(p.x, p.y);
	}
	/**
	 * Returns the index for the piece <Move> from the provided col and row
	 *   ex: if move = RIGHT, then returns the index at col/row + 1, or -1 if
	 *       col is at the end of a row
	 * @param col
	 * @param row
	 * @param move
	 * @return index of piece <Move> away from col, row, or -1 if invalid spot
	 */
	public int getIdx(int col, int row, Move move){
		int ret = -1;
		switch(move){
		case UP:
			if(row > 0) ret = getIdx(col, row - 1);
			break;
		case DOWN:
			if(row < rows - 1) ret = getIdx(col, row + 1);
			break;
		case LEFT:
			if(col > 0) ret = getIdx(col - 1, row);
			break;
		case RIGHT:
			if(col < cols - 1) ret = getIdx(col + 1, row);
			break;
		}
		return ret;
	}
	public int getIdx(Point p, Move move){
		return getIdx(p.x, p.y, move);
	}
	
	public void removePiece(int x, int y){
		int curIdx = getIdx(x - 1, y - 1);
		int pieceIdx = getIdx(x, y);
		for(int j = 0; j < 3; j++){
			for(int i = 0; i < 3; i++){
				if(curIdx == pieceIdx){
					pieces.remove(curIdx);
				}else{
					pieces.get(curIdx).onRemoveAdjacent();
				}
				curIdx++;
			}
			curIdx += (cols - 3);
		}
		pieces.remove(getIdx(x, y));
	}
	public void removeRow(int y){
		final int start = getIdx(0, y);
		for(int i = 0; i < cols; i++){
			pieces.remove(start + i);
		}
	}
	
	public void removeCol(int x){
		final int start = getIdx(x, 0);
		
		for(int i = 0; i < rows; i++){
			pieces.remove(start + (i * cols));
		}
	}
	
	public BoardPiece getPieceAt(Point p){
		return getPieceAt(p.x, p.y);
	}
	public BoardPiece getPieceAt(int x, int y){
		return pieces.get(getIdx(x, y));
	}
	
	public int getCols() { return cols; }
	public int getRows() { return rows; }
}
