package com.undi.sweetsmush.ui;

import com.undi.sweetsmush.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.SurfaceHolder;

public class TitleDraw extends Drawer{
	private Context context;
	
	private Bitmap backgroundRaw, background;
	
	public TitleDraw(SurfaceHolder holder, Context context){
		super(holder, context);
		this.context = context;
		this.backgroundRaw = BitmapFactory.decodeResource(context.getResources(), R.drawable.title);
	}
	
	private void scaleBackground(int w, int h){
		double aspectRatio = (double)(backgroundRaw.getHeight()) / backgroundRaw.getWidth() ;
		this.background = Bitmap.createScaledBitmap(backgroundRaw, w, (int)(w * aspectRatio), true);
	}
	
	@Override
	public void onResize(int w, int h){
		super.onResize(w, h);
		scaleBackground(w, h);
	}

	@Override
	protected void doDraw(Canvas c) {
		if(background != null){
			Paint bgPaint = new Paint();
			bgPaint.setColor(Color.GRAY);
			bgPaint.setStyle(Style.FILL);
			c.drawRect(0, 0, c.getWidth(), c.getHeight(), bgPaint);
			c.drawBitmap(background, 0, 0, null);
		}
	}
}
