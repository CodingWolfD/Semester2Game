package com.State;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.Entity.Collectable;
import com.Entity.Enemy;
import com.Entity.Player;
import com.Main.LevelPanel;
import com.Tilemap.TileMapManager;

public class Menu extends LevelState
{		
	public Menu(LevelManager lm)
	{
		super(lm);
		init();
	}
	
	private void init()
	{
		
	}
	
	public void keyPressed(int keyCode)
	{
		if(keyCode == KeyEvent.VK_ENTER)
		{
			lm.goToState(1);
		}
	}
	
	public void keyReleased(int keyCode)
	{
		
	}
	
	public void update()
	{

	}
	
	@Override
	public void draw(Graphics2D g)
	{
		System.out.println("Drawing Game Items");
		g.setColor(Color.BLUE);
	    g.fillRect(0, 0, LevelPanel.PANEL_WIDTH, LevelPanel.PANEL_HEIGHT);
	    
	    g.setColor(Color.WHITE);
	    g.setFont(new Font("Arial", 0, 100));
	    g.drawString("Bob's Journey", 200, 100);
	    
	    g.setFont(new Font("Arial", 0, 20));
	    g.drawString("Press [ENTER] to play", 450, 900);
	}
}