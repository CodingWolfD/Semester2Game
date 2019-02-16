package com.Entity;

import java.awt.Graphics2D;

import com.Sprite.Sprite;
import com.Sprite.SpriteLoader;
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
	
	private int countDown = 0;
	
	private boolean FALLING;
	private boolean JUMPING;
	private boolean MOVE_LEFT;
	private boolean MOVE_RIGHT;
	private boolean STANDING;
	private boolean SHOOT;
	private boolean ATTACK;
	private boolean BLOCKED_X;
	private boolean BLOCKED_Y;
	
	private Bullet bullet;
	
	private Bullet[] bullets;
	
	public Player(String spriteFile, TileMapManager tm)
	{
		super(tm);	
		
		tmm = tm;
		
		x = 100;
		y = 100;
		dx = 0;
		dy = 0;
		
		health = 100;
		
		ammoCount = 10;
		points = 0;
		
		bullets = new Bullet[ammoCount];
		
		FALLING = true;
	}
	
	public void update()
	{
		double checkX;
		double checkY;
		double tempX;
		double tempY;
		
		checkX = x + dx;
		checkY = y + dy;
		
		tempX = x;
		tempY = y;
		
		checkTileMapCollision(checkX, checkY);
		
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
	
		if(dx < 0)
		{
			if(cTopLeft || cBottomLeft)
			{
				tempX = x;
			}
			else
			{
				tempX += dx;
			}
		}
		
		if(dx > 0)
		{
			if(cTopRight || cBottomRight)
			{
				tempX = x;
			}
			else
			{
				tempX += dx;
			}
		}
		
		if(dy < 0)
		{
			if(cTopRight || cTopLeft)
			{
				tempY = y;
			}
			else
			{
				tempY += dy;
			}
		}
		
		if(cBottomLeft || cBottomRight)
		{
			tempY = y;
			FALLING = false;
		}
		else
		{
			FALLING = true;
		}
		
		if(JUMPING)
		{
			tempY -= 25;
			JUMPING = false;
		}
		else if(countDown > 0)
		{
			tempY -= 25;
			countDown--;
		}
		
		if(FALLING)
		{
			gravity = 1.5f;
		}
		else
		{
			gravity = 0;
		}
		
		if(SHOOT)
		{
			for(int i = 0; i < bullets.length; i++)
			{
				bullets[i].update();
			}
		}
		
		x = tempX;
		y = tempY;
		
		y += gravity;
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		super.draw(g);
		
		if(SHOOT)
		{
			for(int i = 0; i < bullets.length; i++)
			{
				bullets[i].draw(g);
			}
		}
		
		g.drawImage(sprite, (int)(x + tmm.getCameraX()), (int) (y + tmm.getCameraY()), cWidth, cHeight, null);
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
			MOVE_LEFT = false;
			dx = 0;
		}
	}
	
	public void moveRight(boolean move)
	{
		if(move)
		{
			MOVE_RIGHT = true;
			dx = xSpeed;
		}
		else
		{
			STANDING = true;
			MOVE_RIGHT = false;
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
					bullets[i] = new Bullet("/images/bullet.png");
					bullets[i].setX(x);
					bullets[i].setY(y + cHeight / 2);
				}

				ammoCount -= 1;
				SHOOT = true;
			}
		}
	}
	
	public void jump()
	{
		if(FALLING == false)
		{
			JUMPING = true;
			countDown = 5;
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
	
	public void setHealth(int health)
	{
		this.health = health;
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