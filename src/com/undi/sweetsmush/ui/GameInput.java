package com.undi.sweetsmush.ui;

import android.graphics.Point;
import android.view.MotionEvent;
import android.widget.Toast;

import com.undi.sweetsmush.game.Board;
import com.undi.sweetsmush.game.Board.Move;
import com.undi.sweetsmush.game.BoardPiece;
import com.undi.sweetsmush.game.GameHolder;
import com.undi.sweetsmush.view.SweetSmushView;

public class GameInput extends Input {
	private GameHolder gameHolder;
	private GameDraw draw;
	
	private static final Point NONE = new Point(-1, -1);
	
	private BoardPiece curPiece = null;
	private Point curPieceLoc = null;
	private Point curPieceTouchStart = null;

	public GameInput(SweetSmushView mainView, GameHolder gameHolder, GameDraw draw) {
		super(mainView);
		this.gameHolder = gameHolder;
		this.draw = draw;
	}

	private Point findTilePicked(int x, int y){
		int startX = draw.getBoardDrawOffsetX();
		int startY = draw.getBoardDrawOffsetY();
		int tileW = draw.getTileWidth();
		int tileH = draw.getTileHeight();
		
		int pickedCol = (x - startX) / tileW;
		int pickedRow = (y - startY) / tileH;
		Board board = gameHolder.getGame().getLevel().getBoard();
		if(pickedCol >= board.getCols() ||
				pickedRow >= board.getRows()){
			return null;
		}
		
		return new Point(pickedCol , pickedRow);
	}
	
	private void clearCurPiece(){
		curPiece = null;
		curPieceTouchStart = null;
		curPieceLoc = null;
	}

	@Override
	protected void onActionDown(MotionEvent e) {
		int x = (int) e.getX();
		int y = (int) e.getY();
		
		Point picked = findTilePicked(x, y);
		if(picked != null){
			if(curPiece == null){
				BoardPiece piece = gameHolder.getGame().getLevel().getBoard().getPieceAt(picked);
				curPiece = piece;
				curPieceLoc = picked;
				curPieceTouchStart = new Point(x, y);
				Toast.makeText(mainView.getContext(), "Picked a piece: " + piece.getCategory().name(), Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	private String validMoveString(Point picked, Move move){
		if(gameHolder.getGame().getLevel().getBoard().isValidMove(picked, move)){
			return "Valid move";
		}else{
			return "Invalid move";
		}
	}
	
	@Override
	protected void onActionMove(MotionEvent e) {
		int x = (int) e.getX();
		int y = (int) e.getY();
		
		if(curPiece != null){
			int dx = x - curPieceTouchStart.x;
			int dy = y - curPieceTouchStart.y;
			int dxAbs = Math.abs(dx);
			int dyAbs = Math.abs(dy);
			if(dxAbs > 20 && dxAbs >= (2 * dyAbs)){
				if(dx > 0){
					Toast.makeText(mainView.getContext(),
							"Moved RIGHT -- " + validMoveString(curPieceLoc, Move.RIGHT),
							Toast.LENGTH_SHORT).show();
					clearCurPiece();
				}else{
					Toast.makeText(mainView.getContext(),
							"Moved LEFT -- " + validMoveString(curPieceLoc, Move.LEFT),
							Toast.LENGTH_SHORT).show();
					clearCurPiece();
				}
			}
			if(dyAbs > 20 && dyAbs >= (2 * dxAbs)){
				if(dy > 0){
					Toast.makeText(mainView.getContext(),
							"Moved DOWN -- " + validMoveString(curPieceLoc, Move.DOWN),
							Toast.LENGTH_SHORT).show();
					clearCurPiece();
				}else{
					Toast.makeText(mainView.getContext(),
							"Moved UP -- " + validMoveString(curPieceLoc, Move.UP),
							Toast.LENGTH_SHORT).show();
					clearCurPiece();
				}
			}
		}
	}

	@Override
	protected void onActionUp(MotionEvent e) {
		clearCurPiece();
	}

}
