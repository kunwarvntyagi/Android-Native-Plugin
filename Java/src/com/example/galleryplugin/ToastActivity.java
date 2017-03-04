package com.example.galleryplugin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class ToastActivity extends Activity
{
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        showToast(getIntent().getExtras().getString("msg"));
    }
	
	void showToast(String message)
	{
		Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_LONG).show();
		GalleryPlugin.instance.backToUnity(this);
	}
}
