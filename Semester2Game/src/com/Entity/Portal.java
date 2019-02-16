package com.Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Portal extends GameObject
{
	public Portal(String fileName)
	{
		super(tmm);
		
		x = 896;
		y = 512;
	}	
	
	public void update()
	{
		
	}
	
	public void draw(Graphics2D g)
	{
		super.draw(g);
		g.drawImage(sprite, (int) x, (int) y, cWidth, cHeight, null);
	}
	
	public Rectangle getBounds()
	{
		Rectangle r = new Rectangle((int) x, (int) y, cWidth, cHeight);
		return r;
	}
}