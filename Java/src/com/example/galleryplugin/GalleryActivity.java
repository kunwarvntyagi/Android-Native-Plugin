package com.example.galleryplugin;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class GalleryActivity  extends Activity
{

    public static final String GALLERY_CALLBACK = "Gallery_Done";

    private static final int SELECT_PICTURE = 1;

    private String imagePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        openGallery();
    }

    public void openGallery()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Choose a Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        try
        {
            if (resultCode == RESULT_OK)
            {
                if (requestCode == SELECT_PICTURE)
                {
                    Uri selectedImageUri = data.getData();
                    imagePath = selectedImageUri.getPath();
                    Toast.makeText(getApplicationContext(),imagePath,Toast.LENGTH_LONG).show();
                }
            }
            GalleryPlugin.instance.setImagePath(imagePath);
            GalleryPlugin.sendMessageToUnityObject(GALLERY_CALLBACK,imagePath);
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
