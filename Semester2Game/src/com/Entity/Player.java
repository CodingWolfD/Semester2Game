package com.Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends GameObject
{
	private int health;
	
	private double xSpeed = 0.8;
	private double gravity = 0.04;
	
	private double dx;
	private double dy;
	
	private boolean FALLING;
	private boolean MOVE_LEFT;
	private boolean MOVE_RIGHT;
	private boolean STANDING;
	
	public Player(String spriteFile)
	{
		super(spriteFile);
		
		x = 100;
		y = 100;
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
			gravity = 0.2;
		}
		else
		{
			gravity = 0;
		}
		
		x = checkX;
		y = checkY;
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.YELLOW);
		g.drawRect((int) x, (int) y, (int) 10, (int) 10);
	}
	
	public void moveLeft(boolean move)
	{
		if(move)
		{
			MOVE_LEFT = true;
			dx -= xSpeed;
		}
		else
		{
			STANDING = true;
			dx = 0;
		}
	}
	
	public void moveRight(boolean move)
	{
		if(move)
		{
			MOVE_RIGHT = true;
			dx += xSpeed;
		}
		else
		{
			STANDING = true;
			dx = 0;
		}
	}
}