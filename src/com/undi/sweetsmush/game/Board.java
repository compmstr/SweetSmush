package com.undi.sweetsmush.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.undi.sweetsmush.ui.AnimationMgr;

public class Board {
	private List<? extends BoardPiece> pieces;
	private int cols, rows;
	private AnimationMgr anims;
	private Random rand;
	
	public Board(int rows, int cols, long seed){
		this.cols = cols;
		this.rows = rows;
		pieces = new ArrayList<BoardPiece>(rows * cols);
		rand = new Random(seed);
	}
	
	/**
	 * Fill in the board with basic pieces
	 */
	private void startBoard(){
		//TODO
	}
	
	/**
	 * Do falling animations, generate new pieces if needed, etc.
	 */
	public void update(){
		
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
}
