package com.undi.sweetsmush;

import com.undi.sweetsmush.view.TitleView;

import android.app.Activity;
import android.os.Bundle;

public class SweetSmushActivity extends Activity
{
	TitleView titleView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	titleView = new TitleView(this);
        super.onCreate(savedInstanceState);
        setContentView(titleView);
    }
}
