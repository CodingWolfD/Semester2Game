package com.Entity;

import java.awt.Color;
import java.awt.Graphics2D;

import com.Tilemap.TileMapManager;

public class Player extends GameObject
{
	private int health;
	private int ammoCount;
	private int points;
	
	private double xSpeed = 2;
	private double gravity = 0.04;
	
	private double dx;
	private double dy;
	
	private boolean FALLING;
	private boolean JUMPING;
	private boolean MOVE_LEFT;
	private boolean MOVE_RIGHT;
	private boolean STANDING;
	private boolean SHOOT;
	
	private Bullet bullet;
	
	private Bullet[] bullets;
	
	public Player(String spriteFile, TileMapManager tmm)
	{
		super(spriteFile, tmm);
					
		x = 100;
		y = 100;
		dx = 0;
		dy = 0;
		
		health = 100;
		
		ammoCount = 5;
		points = 0;
		
		bullets = new Bullet[ammoCount];
		
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
		
		if(JUMPING && !FALLING)
		{
			gravity -= 100;
		}
		else
		{
			FALLING = true;
		}
		
		x = checkX;
		y = checkY;
		
		y += gravity;
		
		if(SHOOT)
		{
			for(int i = 0; i < bullets.length; i++)
			{
				bullets[i].update();
			}
		}
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		super.draw(g);

		g.setColor(Color.YELLOW);
		
		g.drawImage(sprite, (int)(x + tmm.getCameraX()), (int) (y + tmm.getCameraY()), cWidth, cHeight, null);
		
		if(SHOOT)
		{
			for(int i = 0; i < bullets.length; i++)
			{
				bullets[i].draw(g);
			}
		}
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
		
		if(dx < -xSpeed)
		{
			dx = -xSpeed;
		}
	}
	
	public void moveRight(boolean move)
	{
		if(move)
		{
			MOVE_RIGHT = true;
			dx += xSpeed;
			
			if(dx > xSpeed)
			{
				dx = xSpeed;
			}
		}
		else
		{
			STANDING = true;
			dx = 0;
		}
	}
	
	public void shoot(boolean shoot)
	{		
		if(shoot)
		{
			if(ammoCount > 0)
			{
				for(int i = 0; i < ammoCount; i++)
				{
					Bullet newBullet = new Bullet("/images/bullet.png");
					bullets[i] = newBullet;
				}
				
				for(int i = 0; i < bullets.length; i++)
				{
					bullets[i].setX(x);
					bullets[i].setY(y);
				}

				ammoCount -= 1;
				SHOOT = true;
			}
		}
	}
	
	public void jump(boolean jump)
	{
		if(jump)
		{
			JUMPING = true;
   		}
		else
		{
			JUMPING = false;
		}
	}
	
	public void checkEnemyCollision(Enemy[] enemies)
	{
		for(Enemy current: enemies)
		{
			if(intersects(current))
			{
				
			}
		}
	}
	
	public void checkCollectableCollision(Collectable[] collectables)
	{
		for(Collectable current: collectables)
		{
			if(intersects(current))
			{
				current.cWidth = 0;
				current.cHeight = 0;
				current.sprite = null;
				points += 10;
			}
		}
	}
	
	public int getAmmoCount()
	{
		return ammoCount;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public int getPoints()
	{
		return points;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
}