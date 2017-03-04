package com.example.galleryplugin;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

public class CameraActivity extends Activity
{

	public static final String CAMERA_CALLBACK = "Camera_Done";

    private static int RESULT_TAKE_NEW = 1337;

    private String imagePath = "";
    
    File file;
    Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        openCamera();
    }

    public void openCamera()
    {
    	Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(getExternalCacheDir(), String.valueOf(System.currentTimeMillis()) + ".jpg");
        fileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, RESULT_TAKE_NEW);
	}

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        try
        {
            if (resultCode == RESULT_OK)
            {
                if (requestCode == RESULT_TAKE_NEW)
                {
                    imagePath = fileUri.getPath();
                    Toast.makeText(getApplicationContext(),imagePath,Toast.LENGTH_LONG).show();
                }
            }
            GalleryPlugin.instance.setImagePath(imagePath);
            GalleryPlugin.sendMessageToUnityObject(CAMERA_CALLBACK,imagePath);
        }
        catch (Exception e)
        {

        }
        finally
        {
        	GalleryPlugin.instance.backToUnity(this);
        }

    }
}
