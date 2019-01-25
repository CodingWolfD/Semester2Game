package com.Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameObject 
{
	protected double x;
	protected double y;
	
	protected BufferedImage sprite;
	
	protected int cWidth;
	protected int cHeight;
	
	public GameObject(String fileName)
	{
		loadSprite(fileName);
	}
	
	private void loadSprite(String fileName)
	{
		try
		{
			sprite = ImageIO.read(getClass().getResourceAsStream(fileName));
		}
		catch(IOException ex)
		{
			System.err.println("Error: Unable to load Game Object sprite");
		}
		
		cWidth = sprite.getWidth();
		cHeight = sprite.getHeight();
	}
	
	public void update()
	{
		
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int) x, (int) y, cWidth, cHeight);
	}
	
	public void draw(Graphics2D g)
	{
		g.drawImage(sprite, (int) x, (int) y, null);
	}
	
	public boolean intersects(GameObject obj)
	{
		Rectangle r1 = getBounds();
		Rectangle r2 = obj.getBounds();
		
		return r1.intersects(r2);
	}
}