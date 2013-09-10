package com.undi.sweetsmush;

import com.undi.sweetsmush.view.SweetSmushView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SweetSmushActivity extends Activity
{
	SweetSmushView mainView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mainView = new SweetSmushView(this);
    	mainView.setKeepScreenOn(true);
        super.onCreate(savedInstanceState);
        setContentView(mainView);
    }
}
