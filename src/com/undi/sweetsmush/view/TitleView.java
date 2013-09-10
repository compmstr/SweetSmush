package com.undi.sweetsmush.view;

import com.undi.sweetsmush.ui.TitleDraw;
import com.undi.sweetsmush.ui.TitleInput;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class TitleView extends View {
	private Context context;
	private TitleDraw drawer;
	private TitleInput input;

	public TitleView(Context context) {
		super(context);
		this.context = context;
		drawer = new TitleDraw(context);
		input = new TitleInput();
	}

	@Override
	public void onDraw(Canvas canvas){
		drawer.draw(canvas);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		drawer.onSizeChanged(w, h);
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		input.onTouchEvent(event);
		return super.onTouchEvent(event);
	}
	
}
