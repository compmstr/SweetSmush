package com.undi.sweetsmush.ui;

import com.undi.sweetsmush.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class TitleDraw {
	private Context context;
	
	private Bitmap backgroundRaw, background;
	
	public TitleDraw(Context context){
		this.context = context;
		this.backgroundRaw = BitmapFactory.decodeResource(context.getResources(), R.drawable.title);
	}
	
	public void draw(Canvas canvas){
		if(background != null){
			Paint bgPaint = new Paint();
			bgPaint.setColor(Color.GRAY);
			bgPaint.setStyle(Style.FILL);
			canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), bgPaint);
			canvas.drawBitmap(background, 0, 0, null);
		}
	}
	
	private void scaleBackground(int w, int h){
		double aspectRatio = (double)(backgroundRaw.getHeight()) / backgroundRaw.getWidth() ;
		this.background = Bitmap.createScaledBitmap(backgroundRaw, w, (int)(w * aspectRatio), true);
	}
	
	public void onSizeChanged(int w, int h){
		scaleBackground(w, h);
	}
}
