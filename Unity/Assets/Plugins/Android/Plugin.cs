using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;

namespace com.example.galleryplugin
{
	public class Plugin
	{

		public delegate void OnDataReceived(string message, string imagePath);
		public OnDataReceived imageDelegate;

		#if (UNITY_ANDROID && !UNITY_EDITOR)
		AndroidJavaObject androidObject;
		AndroidJavaObject unityObject;
		#endif

		private static Plugin instance;

		public void handleImage(string param)
		{
			if (imageDelegate == null)
			{
				Debug.Log("You must assign imageDelegate first");
			}
			else
			{
				#if (UNITY_ANDROID && !UNITY_EDITOR)
				imageDelegate(param, androidObject.Call<string>("getImagePath"));
				#endif
			}

		}

		public static Plugin getInstance()
		{
			if (instance == null)
			{ 
				instance = new Plugin();

			}

			return instance;
		}

		private Plugin()
		{
			#if (UNITY_ANDROID && !UNITY_EDITOR)

			AndroidJavaClass unityClass = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
			unityObject = unityClass.GetStatic<AndroidJavaObject>("currentActivity");

			AndroidJavaClass androidClass = new AndroidJavaClass("com.example.galleryplugin.GalleryPlugin");
			androidObject = androidClass.GetStatic<AndroidJavaObject>("instance");
			#endif

		}

		public void openGallery()
		{
			#if (UNITY_ANDROID && !UNITY_EDITOR)
			androidObject.Call("openGallery", unityObject);
			#endif
		}

		public void openCamera()
		{
			#if (UNITY_ANDROID && !UNITY_EDITOR)
			androidObject.Call("openCamera", unityObject);
			#endif
		}

		public void showToast(string message)
		{
			#if (UNITY_ANDROID && !UNITY_EDITOR)
			androidObject.Call("showToast", unityObject,message);
			#endif
		}

	}
}
