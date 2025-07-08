package com.example.images;

import java.net.URL;

public class ImageManager {
	public static URL getImageURL(String fileName)
	{
		return ImageManager.class.getResource(fileName);
	}
}
