package com.Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.Tilemap.Tile;
import com.Tilemap.TileMapManager;

import com.Sprite.*;

public class GameObject 
{
	protected double x;
	protected double y;
	
	//protected BufferedImage sprite;
	protected Sprite sprite;
	
	protected static TileMapManager tmm;

	protected int cWidth;
	protected int cHeight;	
	
	protected boolean cTopLeft;
	protected boolean cBottomRight;
	protected boolean cTopRight;
	protected boolean cBottomLeft;
	
	private BufferedImage image1 = null;
	private BufferedImage image2 = null;
		
	public GameObject(TileMapManager tmm)
	{
		this.tmm = tmm;
	}
	
	public void update()
	{
		
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
		
		// BOTTOM RIGHT
		currXpos = x + cWidth;
		currYpos = y + cHeight;
		tile = tmm.getTileAt(currXpos, currYpos);
		
		cBottomRight = tile.getType() == Tile.TYPE_BLOCKED;
		
		// BOTTOM LEFT
		currXpos = x;
		currYpos = y + cHeight;
		tile = tmm.getTileAt(currXpos, currYpos);
		
		cBottomLeft = tile.getType() == Tile.TYPE_BLOCKED;
	}
	
	public boolean intersects(GameObject obj)
	{
		Rectangle r1 = getBounds();
		Rectangle r2 = obj.getBounds();
		
		return r1.intersects(r2);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int) x, (int) y, cWidth, cHeight);
	}
	
	public void draw(Graphics2D g)
	{
		if(sprite != null)
		{
			g.drawImage(sprite.getSprite(), (int)(x + tmm.getCameraX()), (int)(y + tmm.getCameraY()), null);
		}
	}
	
	public boolean collidesWith(GameObject g)
	{
		boolean collision = false;
		int x1, y1;
		int x2, y2;
		
		x1 = (int) getBounds().getX() + (int) (getBounds().getY() / 2);
		y1 = (int) getBounds().getY() + (int) (getBounds().height / 2);
		
		x2 = (int) g.getBounds().getX() + (int) (getBounds().getWidth() / 2);
		y2 = (int) g.getBounds().getY() + (int) (getBounds().getHeight() / 2);
		
		double x1x2 = Math.abs(x2 - x1);
		double y1y2 = Math.abs(y2 - y1);
		
		int distance = (int)Math.hypot(x1x2, y1y2);
		
		if(distance < 80)
		{
			collision = pixelCollisionCheck(g, g.getBounds(), getBounds());
		}
		
		return collision;
	}
	
	public boolean pixelCollisionCheck(GameObject other, Rectangle r1, Rectangle r2)
	{
		int topCornerX;
		int bottomCornerX;
		int topCornerY;
		int bottomCornerY;
		
		topCornerY = (r1.y > r2.y) ? r1.y : r2.y;
		bottomCornerY = ((r1.width + r1.x) > (r2.width + r2.x)) ? r1.x + r1.height : r2.x + r2.height;
		
		topCornerX = (r1.x > r2.x) ? r1.x : r2.x;
		bottomCornerX = ((r1.width + r1.x) > (r1.width + r2.x)) ? r1.x + r1.height : r2.x + r2.height;
		
		int height = bottomCornerY - topCornerY;
		int width = bottomCornerX - topCornerX;
		
		int[] pixels1 = new int[width * height];
		int[] pixels2 = new int[width * height];
		
		PixelGrabber pg1 = new PixelGrabber(other.getSprite().getSprite(), topCornerX - (int)other.getX(), (int) topCornerY - (int) other.getY(), width, height, pixels1, 0, width);
		PixelGrabber pg2 = new PixelGrabber(getSprite().getSprite(), topCornerX - (int) getX(), (int) topCornerY - (int) getY(), width, height, pixels2, 0, width);
		
		try
		{
			pg1.grabPixels();
			pg2.grabPixels();
		}
		catch(InterruptedException ex)
		{
			System.err.println("Error in pixel collision check");
			System.err.println("Cannot grab pixels");
			System.err.println(ex.getMessage());
		}
		
		image1 = new BufferedImage(pg1.getWidth(), pg1.getHeight(), BufferedImage.TYPE_INT_ARGB);
		image1.setRGB(0, 0, pg1.getWidth(), pg1.getHeight(), pixels1, 0, pg1.getWidth());
		
		image2 = new BufferedImage(pg1.getWidth(), pg1.getHeight(), BufferedImage.TYPE_INT_ARGB);
		image2.setRGB(0, 0, pg2.getWidth(), pg2.getHeight(), pixels2, 0, pg2.getWidth());
		
		for(int i = 0; i < pixels1.length; i++)
		{
			int t1 = (pixels1[i] >>> 24) & 0xFF;
			int t2 = (pixels2[i] >>> 24) & 0xFF;
			
			if(t1 > 0 && t2 > 0)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public Sprite getSprite()
	{
		return sprite;
	}
	
	public void setSprite(Sprite s)
	{
		sprite = s;
	}
}