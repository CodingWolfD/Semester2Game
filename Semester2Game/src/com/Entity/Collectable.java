package com.Entity;

import java.awt.Graphics2D;

public class Collectable extends GameObject
{
	private double gravity;
	
	private double dy;
	private double dx;
	
	private boolean FALLING;
		
	public Collectable(String fileName)
	{
		super(tmm);
		
		x = 200;
		y = 10;
		dx = 0;
		dy = 0;
		
		FALLING = true;
	}	
	
	public void update()
	{
		double checkX;
		double checkY;
		
		checkX = x + dx;
		checkY = y + dy;
		
		if(FALLING)
		{
			gravity = 2;
		}
		else
		{
			gravity = 0;
		}
		
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
			FALLING = false;
		}
		else
		{
			FALLING = true;
		}
		
		if(FALLING)
		{   
			gravity = 2f;
		}
		else
		{
			gravity = 0;
		}
		
		x = checkX;
		y = checkY;
		
		y += gravity;
	}
	
	public void draw(Graphics2D g)
	{
		super.draw(g);
		g.drawImage(sprite, (int) x, (int) y, cWidth, cHeight, null);
	}
}