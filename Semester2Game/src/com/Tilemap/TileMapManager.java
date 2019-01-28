package com.Tilemap;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import com.Main.LevelPanel;

public class TileMapManager
{
	private final int TILE_SIZE = 64;
	private Tile[] tiles;
	
	private final int[][] map =
	{
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0, 1, 1, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
			{2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 2},
			{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 2, 2},
			{2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 2}
	};

	private double cameraX;
	private double cameraY;
	
	private int xMax;
	private int yMax;
	private int xMin;
	private int yMin;
	
	private int numberOfColums;
	private int numberOfRows;
	
	private int rowOffSet;
	private int colOffSet;
	
	private int numOfColumsToDraw;
	private int numOfRowsToDraw;
	
	public TileMapManager()
	{
		int width;
		int height;
		
		numberOfRows = map.length;
		numberOfColums = map[0].length;
		
		numOfColumsToDraw = LevelPanel.PANEL_WIDTH / TILE_SIZE + 2;
		numOfRowsToDraw = LevelPanel.PANEL_HEIGHT / TILE_SIZE + 2;
		
		width = numberOfColums * TILE_SIZE;
		height = numberOfRows * TILE_SIZE;
		
		xMin = LevelPanel.PANEL_WIDTH - width;
		xMax = 0;
		
		yMin = LevelPanel.PANEL_HEIGHT - height;
		yMax = 0;
		
		cameraX = 0;
		cameraY = 0;
		
		//loadMap();
		loadTiles();
		
	}
	
	private void loadTiles()
	{
		tiles = new Tile[3];
		
		try
		{
			tiles[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/images/col1.png")), Tile.TYPE_NORMAL);
			tiles[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/images/col2.png")), Tile.TYPE_BLOCKED);
			tiles[2] = new Tile(ImageIO.read(getClass().getResourceAsStream("/images/col3.png")), Tile.TYPE_BLOCKED);
		}
		catch(java.io.IOException ex)
		{
			System.err.println("Error: Loading Tiles");
		}
	}
	
	public Tile getTileAt(double x, double y)
	{
		x = x - cameraX;
		y = y - cameraY;
		
		int row = (int) y / TILE_SIZE;
		int colum = (int) x / TILE_SIZE;
		
		int tileID = map[row][colum];
		
		tileID = matchTile(tileID);
		System.out.println("X: " + x + "Y: " + y);
		System.out.println("In Tile: " + row + ", " + colum);
		return tiles[tileID];
	}
	
	public void setCameraPosition(double x, double y)
	{
		cameraX += (x - cameraX);
		cameraY += (y - cameraY);
		
		fixCameraBounds();
		
		colOffSet = (int) -cameraX / TILE_SIZE;
		rowOffSet = (int) -cameraY / TILE_SIZE;
	}
	
	private void fixCameraBounds()
	{
		if(cameraX < xMin)
		{
			cameraX = xMin;
		}
		
		if(cameraX > xMax)
		{
			cameraX = xMax;
		}
		
		if(cameraY < yMin)
		{
			cameraY = yMin;
		}
		
		if(cameraY > yMax)
		{
			cameraY = yMax;
		}
	}
	
	private int matchTile(int tileMapID)
	{
		int mappedTile = 0;
		
		switch(tileMapID) 
		{
			case 1:
			{
				mappedTile = 0;
			}
				break;
			case 2:
			{
				mappedTile = 1;
			}
				break;
			case 3:
			{
				mappedTile = 2;
			}
				break;
		}
		
		return mappedTile;
	}
	
	public void draw(Graphics2D g)
	{
		int tileImage;
		double tempX;
		double tempY;
		
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		g.setColor(Color.WHITE);
		g.drawString("Camera X: " + cameraX + "Camera Y: " + cameraY, 20, 20);
		
		g.setColor(Color.BLACK);
		
		
		for(int row = rowOffSet; row < rowOffSet + numOfRowsToDraw; row++)
		{
			if(row >= numberOfRows)
			{
				break;
			}
			
			tempY = cameraY + row * TILE_SIZE;
			
			for(int col = colOffSet; col < colOffSet + numOfColumsToDraw; col++)
			{
				if(col >= numberOfColums)
				{
					break;
				}
				
				tempX = cameraX + col * TILE_SIZE;
				
				if(map[row][col] != 0)
				{
					tileImage = matchTile(map[row][col]);
					
					g.drawImage(tiles[tileImage].getImage(), (int) tempX, (int) tempY, null);
					g.drawRect((int) tempX, (int) tempY, TILE_SIZE, TILE_SIZE);
				}
			}
		}	
	}
	
	public double getCameraX()
	{
		return cameraX;
	}
	
	public double getCameraY()
	{
		return cameraY;
	}
}