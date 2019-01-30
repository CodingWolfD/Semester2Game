package com.Main;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.State.LevelManager;

public class LevelPanel extends JPanel implements KeyListener
{
	public static final int PANEL_WIDTH = Game.WINDOW_WIDTH - 10;
	public static final int PANEL_HEIGHT = Game.WINDOW_HEIGHT - 10;
	
	private final double FPS = 60;
	private final double TARGET_UPDATE_TIME = 1000/FPS;
	
	private BufferedImage screenBuffer;
	private Graphics2D graphics;
	
	private Thread gameLoop = null;
	
	private boolean isRunning;
	private boolean isPaused;
	
	private LevelManager lm;
	
	public LevelPanel()
	{		
		super();
			
		isRunning = true;
		isPaused = false;
		
		lm = new LevelManager();
		
		try
		{
			screenBuffer = ImageIO.read(getClass().getResourceAsStream("/images/screenBuffer.png"));
		}
		catch(IOException ex)
		{
			System.err.print("Error: Couldnt find screen buffer");
		}
	}
	
	public void startGame()
	{
		initGraphics();
		
		gameLoop = new Thread()
		{
			@Override
			public void run()
			{
				gameLoop();
			}
		};
		
		gameLoop.start();
	}
	
	private void gameLoop()
	{
		double startTime;
		double finishTime;
		double deltaT;
		double waitT;
		double fps;
		
		while(isRunning)
		{
			startTime = System.nanoTime();
			
			if(!isPaused)
			{
				lm.update();
				lm.updateScreenBuffer(graphics);
				
				repaint();
				
				finishTime = System.nanoTime();
				
				deltaT = finishTime - startTime;
				
				waitT = TARGET_UPDATE_TIME - deltaT / 100000000;
				
				if(waitT < 5)
				{
					waitT = 5;
				}
				
				try
				{
					Thread.sleep((long) waitT);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	private void initGraphics()
	{
		lm.setCurrentState(1);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}