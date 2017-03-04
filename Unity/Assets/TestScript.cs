using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using com.example.galleryplugin;
using UnityEngine.UI;
using System.IO;

public class TestScript : MonoBehaviour 
{

	public Image image;

	void Start()
	{
		Plugin.getInstance().imageDelegate = imageHandle;
	}

	public void openGallery()
	{
		Plugin.getInstance().openGallery();
	}

	public void openCamera()
	{
		Plugin.getInstance().openCamera();
	}

	public void showToast(string message)
	{
		Plugin.getInstance ().showToast (message);
	}

	void imageHandle(string message, string path)
	{
		StartCoroutine (loadImage(path));
	}

	IEnumerator loadImage(string path)
	{
		WWW www = new WWW("file://" + path);
		yield return www;
		Texture2D xx = new Texture2D(400, 800, TextureFormat.BGRA32, false);
		xx.LoadImage(www.bytes);
		Sprite newSprite = Sprite.Create(xx as Texture2D, new Rect(0f, 0f, xx.width, xx.height), Vector2.zero);
		image.sprite = newSprite;
	}
}
