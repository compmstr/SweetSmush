package com.undi.sweetsmush.ui;

import java.util.HashMap;
import java.util.Map;

import com.undi.sweetsmush.game.BoardPiece;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BoardPieceGraphicMgr {
	public static class BoardPieceGraphic{
		public Bitmap raw;
		public Bitmap scaled;
	}

	private static Map<Class<? extends BoardPiece>, BoardPieceGraphic> graphics;
	private static int screenW = -1;
	private static int screenH = -1;
	private static Context context = null;
	
	public static void init(Context ctx){
		BoardPieceGraphicMgr.context = ctx;
		graphics = new HashMap<Class<? extends BoardPiece>, BoardPieceGraphicMgr.BoardPieceGraphic>();
	}
	
	public static BoardPieceGraphic registerGraphics(Class<? extends BoardPiece> cls, int resourceId){
		BoardPieceGraphic newEntry = new BoardPieceGraphic();
		newEntry.raw = BitmapFactory.decodeResource(context.getResources(), resourceId);
		scaleEntry(newEntry);
		graphics.put(cls, newEntry);
		return newEntry;
	}
	
	private static void scaleEntry(BoardPieceGraphic entry){
		
	}

	public static void onResize(int screenW, int screenH){
		BoardPieceGraphicMgr.screenW = screenW;
		BoardPieceGraphicMgr.screenH = screenH;
	}
}
