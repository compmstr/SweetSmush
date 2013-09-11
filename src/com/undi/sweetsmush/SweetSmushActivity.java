package com.undi.sweetsmush;

import com.undi.sweetsmush.view.SweetSmushView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class SweetSmushActivity extends Activity
{
	SweetSmushView mainView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sweet_smush);
		mainView = (SweetSmushView) findViewById(R.id.sweet_smush);
    	mainView.setKeepScreenOn(true);
    }
}
