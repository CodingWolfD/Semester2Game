package com.Sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteLoader
{
	private String spritePath = "/images";
	
	public SpriteLoader(String spritePath)
	{
		this.spritePath = spritePath;
	}
	
	public Sprite loadSprites(String url, int tileSize)
	{
		Sprite s = new Sprite(10);
		return s;
	}
	
	public Sprite loadFileSequence(String fileName, int numberOfFrames, String type)
	{
		Sprite s = new Sprite(10);
		
		BufferedImage bi;
		
		String tempFName;
		
		try
		{
			for(int i = 1; i < numberOfFrames; i++)
			{
				tempFName = spritePath + "/" + fileName + "_" + i + type;
				bi = ImageIO.read(getClass().getResourceAsStream(tempFName));
				s.addFrame(bi);
			}
		}
		catch(IOException ex)
		{
			System.err.println("LoadFileSequence");
			System.err.println("Error Loading Sprite Image");
			ex.getMessage();
		}
		
		return s;
	}
}