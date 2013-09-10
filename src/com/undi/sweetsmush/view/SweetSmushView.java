package com.undi.sweetsmush.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class SweetSmushView extends ViewGroup {
	
	public static enum State { TITLE, GAME };
	private State curView = null;
	private TitleView titleView;
	private GameView gameView;
	
	public SweetSmushView(Context context) {
		super(context);
		
		titleView = new TitleView(context, this);
		gameView = new GameView(context);
		addView(titleView);
		addView(gameView);
		switchView(State.TITLE);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int childCount = getChildCount();
		for(int i = 0; i < childCount; i++){
			View child = getChildAt(i);
			child.layout(l, t, r, b);
		}
	}

	public void switchView(State newView){
		if(curView != newView){
			curView = newView;
			switch(curView){
			case GAME:
				titleView.setVisibility(GONE);
				gameView.setVisibility(VISIBLE);
				break;
			case TITLE:
				titleView.setVisibility(VISIBLE);
				gameView.setVisibility(GONE);
				break;
			}
		}
	}
}
