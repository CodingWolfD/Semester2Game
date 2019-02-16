package com.Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.Sprite.Sprite;

public class Portal extends GameObject
{
	private Sprite s;
	
	public Portal(String fileName)
	{
		super(tmm);
	
		s = new Sprite(1);
		s.addFrame("/images/" + fileName);

		x = 896;
		y = 512;
	}	
	
	public void update()
	{
		
	}
	
	public void draw(Graphics2D g)
	{
		super.draw(g);
		g.drawImage(s.getSprite(), (int) x, (int) y, cWidth, cHeight, null);
	}
	
	public Rectangle getBounds()
	{
		Rectangle r = new Rectangle((int) x, (int) y, cWidth, cHeight);
		return r;
	}
}