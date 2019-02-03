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

public class Level1 extends LevelState
{
	private Player p;
	private boolean win;
	private Enemy[] enemies;
	private Collectable[] collectables;
	private TileMapManager tmm;
		
	public Level1(LevelManager lm)
	{
		super(lm);
		init();
	}
	
	private void init()
	{		
		tmm = new TileMapManager();
		p = new Player("/images/player.png", tmm);
		win = false;
		
		initEnemies();
		initCollectables();	
	}
	
	private void initEnemies()
	{
		enemies = new Enemy[5];
		
		for(int i = 0; i < enemies.length; i++)
		{
			enemies[i] = new Enemy("/images/enemy.png");
		}
	}
	
	private void initCollectables()
	{
		collectables = new Collectable[1];
		
		for(int i = 0; i < collectables.length; i++)
		{
			collectables[i] = new Collectable("/images/collectable.png");
		}
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
		
		if(keyCode == KeyEvent.VK_SPACE)
		{
			p.jump(true);
		}
		
		if(keyCode == KeyEvent.VK_R)
		{
			p.shoot(true);
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
		
		if(keyCode == KeyEvent.VK_SPACE)
		{
			p.jump(false);
		}
		
		if(keyCode == KeyEvent.VK_R)
		{
			p.shoot(false);
		}
	}
	
	public void update()
	{
		p.update();
				
		for(int i = 0; i < collectables.length; i++)
		{
			collectables[i].update();
		}
		
//		
//		for(int i = 0; i < enemies.length; i++)
//		{
//			enemies[i].update();
//		}
//				
		p.checkEnemyCollision(enemies);
		p.checkCollectableCollision(collectables);
		
		tmm.setCameraPosition((int) LevelPanel.PANEL_WIDTH / 2 - p.getX(), (int) LevelPanel.PANEL_WIDTH / 2 - p.getY());
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		System.out.println("Drawing Game Items");
		g.setColor(Color.BLUE);
	    g.fillRect(0, 0, LevelPanel.PANEL_WIDTH, LevelPanel.PANEL_HEIGHT);
	    
	    g.setFont(new Font("Arial", 0, 20));
	    g.setColor(Color.WHITE);
	    g.drawString("Health: " + p.getHealth(), 10, 100);
	    g.drawString("Ammo: " + p.getAmmoCount(), 10, 900);
	    g.drawString("Points: " + p.getPoints(), 10, 920);
	    
		tmm.draw(g);
		p.draw(g);
		
		//enemies[0].draw(g);
		collectables[0].draw(g);
	}
}