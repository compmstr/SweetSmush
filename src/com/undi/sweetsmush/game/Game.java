package com.undi.sweetsmush.game;

public class Game {
	private Level level;
	
	public Game(){
		this.level = new Level(12345);
	}
	
	public Level getLevel() { return level; }
}
