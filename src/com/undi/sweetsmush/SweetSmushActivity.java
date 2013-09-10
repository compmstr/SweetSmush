package com.undi.sweetsmush;

import com.undi.sweetsmush.view.TitleView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SweetSmushActivity extends Activity
{
	TitleView titleView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	titleView = new TitleView(this);
    	titleView.setKeepScreenOn(true);
        super.onCreate(savedInstanceState);
        setContentView(titleView);
    }
}
