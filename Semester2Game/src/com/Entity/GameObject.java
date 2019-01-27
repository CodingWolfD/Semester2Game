package com.Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.Tilemap.Tile;
import com.Tilemap.TileMapManager;

public class GameObject 
{
	protected double x;
	protected double y;
	
	protected BufferedImage sprite;
	
	protected static TileMapManager tmm;

	protected int cWidth;
	protected int cHeight;	
	
	protected boolean cTopLeft;
	protected boolean cBottomRight;
	protected boolean cTopRight;
	protected boolean cBottomLeft;
	
	public GameObject(String fileName, TileMapManager tmm)
	{
		this.tmm = tmm;
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
	
	public void checkTileMapCollision(double x, double y)
	{
		double currXpos;
		double currYpos;
		Tile tile;
		
		// TOP LEFT
		currXpos = x;
		currYpos = y;
		tile = tmm.getTileAt(currXpos, currYpos);
		cTopLeft = tile.getType() == Tile.TYPE_BLOCKED;
		
		// TOP RIGHT
		currYpos = y;
		currXpos = x + cWidth;
		tile = tmm.getTileAt(currXpos, currYpos);
		cTopRight = tile.getType() == Tile.TYPE_BLOCKED;
		
		// BOTTOM LEFT
		currXpos = x;
		currYpos = y + cHeight;
		tile = tmm.getTileAt(currXpos, currYpos);
		cBottomLeft = tile.getType() == Tile.TYPE_BLOCKED;
		
		// BOTTOM RIGHT
		currXpos = x + cWidth;
		currYpos = y + cHeight;
		tile = tmm.getTileAt(currXpos, currYpos);
		cBottomRight = tile.getType() == Tile.TYPE_BLOCKED;
	}
}