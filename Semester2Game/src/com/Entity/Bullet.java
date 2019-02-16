package com.Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.Sprite.Sprite;

public class Bullet extends GameObject
{	
	private double x;
	private double y;
	
	private double dx;
	private double dy;
	
	private double speed;
			
	private	Sprite s;
	
	public Bullet(String fileName)
	{
		super(tmm);
		
		s = new Sprite(1);
		s.addFrame("/images/" +fileName);
		
		x = 0;
		y = 0;
		
		speed = 3;
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
		}
		
		if(cBottomRight && cTopRight)
		{
			checkX = x;
		}
		
		if(cTopRight || cTopLeft)
		{
			checkY = y;
		}
		
		if(cBottomLeft || cBottomRight)
		{
			checkY = y;
		}
		
		x = checkX;
		y = checkY;
		
		x += speed;
	}
	
	public void draw(Graphics2D g)
	{
		g.drawImage(s.getSprite(), (int) x, (int) y, cWidth, cHeight, null);
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