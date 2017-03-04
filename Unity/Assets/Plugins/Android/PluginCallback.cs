using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using com.example.galleryplugin;

public class PluginCallback : MonoBehaviour 
{
	void Gallery_Done(string param)
	{
		Plugin.getInstance().handleImage(param);
	}

	void Camera_Done(string param)
	{
		Plugin.getInstance().handleImage(param);
	}

}
