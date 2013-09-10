package com.undi.sweetsmush.game;

public class Level {
	private Board board;
	private long seed;
	
	public Level(long seed){
		this.seed = seed;
		this.board = new Board(8, 8, seed);
	}
	
	public Board getBoard() { return board; }
}
