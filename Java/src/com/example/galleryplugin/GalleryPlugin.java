package com.example.galleryplugin;

import com.unity3d.player.UnityPlayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class GalleryPlugin 
{
	public static GalleryPlugin instance = new GalleryPlugin();

    public Context unityActivity;

    public String imagePath = "";

    //The name of the GameObject in Unity's Scene which is used to communicate with this Plugin
    public static final String UNITY_GAMEOBJECT = "UnityGameObject";

    // Open Gallery Options
    public void openGallery(Context context)
    {
        this.unityActivity = context;
        Intent myIntent = new Intent(context, GalleryActivity.class);
        context.startActivity(myIntent);
    }
    
    public void openCamera(Context context)
    {
        this.unityActivity = context;
        Intent myIntent = new Intent(context, CameraActivity.class);
        context.startActivity(myIntent);
    }
    
    public void showToast(Context context, String message)
    {
        this.unityActivity = context;
        Intent myIntent = new Intent(context, ToastActivity.class);
        myIntent.putExtra("msg", message);
        context.startActivity(myIntent);
    }

    public void backToUnity(Activity androiActivity)
    {
        Intent myIntent = new Intent(androiActivity, unityActivity.getClass());
        unityActivity.startActivity(myIntent);
    }

    public static void sendMessageToUnityObject(String method, String params)
    {
        UnityPlayer.UnitySendMessage(GalleryPlugin.UNITY_GAMEOBJECT, method, params);
    }

    public void setImagePath(String path)
    {
        this.imagePath = path;
    }

    public String getImagePath()
    {
        return imagePath;
    }

}
