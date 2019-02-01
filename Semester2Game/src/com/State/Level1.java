package com.State;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import com.Entity.Bullet;
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
		tmm = new TileMapManager();
		p = new Player("/images/player.png", tmm);
				
		win = false;
		
		init();
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
		
		for(int i = 0; i < enemies.length; i++)
		{
			enemies[i].update();
		}
				
		//p.checkEnemyCollision(enemies);
		//p.checkCollectableCollision(collectables);
		
		tmm.setCameraPosition((int) LevelPanel.PANEL_WIDTH / 2 - p.getX(), (int) LevelPanel.PANEL_WIDTH / 2 - p.getY());
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LevelPanel.PANEL_WIDTH, LevelPanel.PANEL_HEIGHT);
		
		tmm.draw(g);
		p.draw(g);
		
		collectables[0].draw(g);
	}
}