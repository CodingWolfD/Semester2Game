package com.State;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.Main.LevelPanel;
import com.Sprite.Sprite;

public class Menu extends LevelState
{		
	private BufferedImage titleImage;
	private BufferedImage backgroundImage;

	public Menu(LevelManager lm)
	{
		super(lm);
		init();
	}
	
	private void init()
	{
		initializeImages();
	}
	
	private void initializeImages()
	{
		try
		{
			titleImage = ImageIO.read(getClass().getResourceAsStream("/images/Bob's_Journey_Title.png"));
			backgroundImage = ImageIO.read(getClass().getResourceAsStream("/images/Menu_Screen.png"));
		}
		catch(IOException e)
		{
			System.err.println("Error: Cannot find title image");
		}
	}
	
	public void keyPressed(int keyCode)
	{
		if(keyCode == KeyEvent.VK_ENTER)
		{
			lm.goToState(1);
		}
		
		if(keyCode == KeyEvent.VK_ESCAPE)
		{
			System.exit(0);
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
		
	    g.drawImage(backgroundImage, 0, 0, 1050, 790, null);
	    g.drawImage(titleImage, 150, 100, 800, 100, null);

	    g.setColor(Color.WHITE);
	    g.setFont(new Font("Arial", 0, 20));
	    g.drawString("Press [ENTER] to play", 420, 500);
	    
	    g.setColor(Color.WHITE);
	    g.setFont(new Font("Arial", 0, 20));
	    g.drawString("Original Framework By: James Oliver", 650, 690);
	    g.drawString("Adapted By Daniel Martin", 650, 710);
	}
}