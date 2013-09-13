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
		public int categories, subTypes;
		public int scaledTileW, scaledTileH;
	}

	private static Map<Class<? extends BoardPiece>, BoardPieceGraphic> graphics;
	private static int screenW = -1;
	private static int screenH = -1;
	private static Context context = null;
	public static final float PIECE_SIZE_PERCENT = 0.12f;
	public static int tileW = -1;
	public static int tileH = -1;
	
	public static void init(Context ctx, int screenW, int screenH){
		BoardPieceGraphicMgr.screenW = screenW;
		BoardPieceGraphicMgr.screenH = screenH;
		BoardPieceGraphicMgr.context = ctx;
		graphics = new HashMap<Class<? extends BoardPiece>, BoardPieceGraphicMgr.BoardPieceGraphic>();
	}
	
	public static BoardPieceGraphic registerGraphics(Class<? extends BoardPiece> cls, int resourceId, int categories, int subTypes){
		BoardPieceGraphic newEntry = new BoardPieceGraphic();
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inScaled = false;
		newEntry.raw = BitmapFactory.decodeResource(context.getResources(), resourceId, opts);
		newEntry.categories = categories;
		newEntry.subTypes = subTypes;
		scaleEntry(newEntry);
		graphics.put(cls, newEntry);
		return newEntry;
	}
	
	public static BoardPieceGraphic getGraphic(Class<? extends BoardPiece> cls){
		return graphics.get(cls);
	}
	
	private static void scaleEntry(BoardPieceGraphic entry){
		if(screenW > 0 && screenH > 0){
			entry.scaled = Bitmap.createScaledBitmap(entry.raw, tileW * entry.categories, 
					tileH * entry.subTypes, true);
			entry.scaledTileW = tileW;
			entry.scaledTileH = tileH;
		}
	}

	public static void onResize(int screenW, int screenH){
		BoardPieceGraphicMgr.screenW = screenW;
		BoardPieceGraphicMgr.screenH = screenH;
		tileW = (int) (screenW * PIECE_SIZE_PERCENT);
		//All of the pieces are square
		tileH = (int) (tileW);
		for(BoardPieceGraphic entry : graphics.values()){
			scaleEntry(entry);
		}
	}
}
