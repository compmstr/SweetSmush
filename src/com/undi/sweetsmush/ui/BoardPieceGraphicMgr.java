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
		int categories, subTypes;
		int scaledTileW, scaledTileH;
	}

	private static Map<Class<? extends BoardPiece>, BoardPieceGraphic> graphics;
	private static int screenW = -1;
	private static int screenH = -1;
	private static Context context = null;
	public static final float PIECE_SIZE_PERCENT = 0.12f;
	
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
	
	private static void scaleEntry(BoardPieceGraphic entry){
		if(screenW > 0 && screenH > 0){
			int origW = entry.raw.getWidth() / entry.categories;
			int origH = entry.raw.getHeight() / entry.subTypes;
			float aspectRatio = (float)origH / origW;
			float scaledW = screenW * PIECE_SIZE_PERCENT;
			float scaledH = scaledW * aspectRatio;
			entry.scaled = Bitmap.createScaledBitmap(entry.raw, (int)(scaledW * entry.categories), 
					(int)(scaledH * entry.subTypes), true);
			entry.scaledTileW = (int) scaledW;
			entry.scaledTileH = (int) scaledH;
		}
	}

	public static void onResize(int screenW, int screenH){
		BoardPieceGraphicMgr.screenW = screenW;
		BoardPieceGraphicMgr.screenH = screenH;
		for(BoardPieceGraphic entry : graphics.values()){
			scaleEntry(entry);
		}
	}
}
