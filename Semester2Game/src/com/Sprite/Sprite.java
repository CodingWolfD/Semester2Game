package com.Sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Sprite 
{
	private int stepCount;
	private int animationSpeed;
	private int currentFrame;
	private int numberOfFrames;
	private int width;
	private int height;
	
	private boolean isAnimated;
	private ArrayList<BufferedImage> animation;
	
	public Sprite(int animSpeed)
	{
		stepCount = 0;
		animationSpeed = animSpeed;
		currentFrame = 0;
		numberOfFrames = 0;
		isAnimated = false;
		animation = new ArrayList();
	}
	
	public void addFrame(BufferedImage frame)
	{
		if(frame != null)
		{
			animation.add(frame);
			numberOfFrames = animation.size();
			
			if(numberOfFrames > 1)
			{
				isAnimated = true;
			}
		}
	}
	
	public void addFrame(String frameURL)
	{
		BufferedImage tempImage = null;
		
		try
		{
			tempImage = ImageIO.read(getClass().getResourceAsStream(frameURL));
		}
		catch(IOException ex)
		{
			System.err.println("ERROR ADDING FRAME TO ANIMATION");
			System.err.println("URL PROVIDED: " + frameURL);
			ex.getMessage();
		}
		
		animation.add(tempImage);
		numberOfFrames = animation.size();
		
		if(numberOfFrames > 1)
		{
			isAnimated = true;
		}
	}
	
	public BufferedImage getSprite()
	{
		return isAnimated ? getNextFrame() : animation.get(0);
	}
	
	private BufferedImage getNextFrame()
	{
		BufferedImage frame = animation.get(currentFrame);
		
		stepCount++;
		
		if(stepCount % animationSpeed == 0)
		{
			currentFrame++;
		}
		
		if(currentFrame == numberOfFrames)
		{
			currentFrame = 0;
		}
		
		return frame;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}

}