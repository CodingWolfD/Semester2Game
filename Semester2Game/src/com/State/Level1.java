package com.State;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.Entity.Player;
import com.Main.LevelPanel;

public class Level1 
{
	private Player p;
	
	public Level1(LevelManager m)
	{
		super(lm);
		p = new Player();
	}
	
	private void init()
	{
		
	}
	
	public void keyPressed(int keyCode)
	{
		if(keyCode == KeyEvent.VK_A)
		{
			p.moveLeft(true);
		}
		
		if(keyCode == KeyEvent.VK_D)
		{
			p.moveRight(true);
		}
	}
	
	public void keyReleased(int keyCode)
	{
		if(keyCode == KeyEvent.VK_A)
		{
			p.moveLeft(false);
		}
		
		if(keyCode == KeyEvent.VK_D)
		{
			p.moveRight(false);
		}
	}
	
	public void update()
	{
		p.update();
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LevelPanel.PANEL_WIDTH, LevelPanel.PANEL_HEIGHT);
	}
}