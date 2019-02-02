package com.Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bullet extends GameObject
{	
	private double x;
	private double y;
	
	private double dx;
	private double dy;
	
	private double speed;
			
	public Bullet(String fileName)
	{
		super(fileName, tmm);
		
		x = 0;
		y = 0;
		
		speed = 1;
	}
	
	public void update()
	{
		double checkX;
		double checkY;
		
		checkX = x + dx;
		checkY = y + dy;
		
		x = checkX;
		y = checkY;
	
		checkTileMapCollision(checkX, checkY);
		
		if(cTopLeft && cBottomLeft)
		{
			checkX = x;
			this.sprite = null;
		}
		
		if(cBottomRight && cTopRight)
		{
			checkX = x;
			this.sprite = null;
		}
		
		if(cTopRight || cTopLeft)
		{
			checkY = y;
			this.sprite = null;
		}
		
		if(cBottomLeft || cBottomRight)
		{
			checkY = y;
			this.sprite = null;
		}
		
		x = checkX;
		y = checkY;
		
		x += speed;
	}
	
	public void draw(Graphics2D g)
	{
		g.drawImage(sprite, (int) x, (int) y, cWidth, cHeight, null);
	}
	
	public void setX(double newX)
	{
		x = newX;
	}
	
	public double getX()
	{
		return x;
	}
	
	public void setY(double newY)
	{
		y = newY;
	}
	
	public double getY()
	{
		return y;
	}
}