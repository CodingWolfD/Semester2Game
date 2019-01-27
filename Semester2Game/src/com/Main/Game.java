package com.Main;

import javax.swing.JFrame;

public class Game
{
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 384;
	
	private static final String TITLE = "New and Improved Game";
	
	private JFrame mainWindow;
	private LevelPanel lp;
	
	public Game()
	{
		initGame();
		initComponents();
		initWindow();
	}
	
	private void initGame()
	{
		lp = new LevelPanel();
		mainWindow = new JFrame();
	}
	
	private void initComponents()
	{
		mainWindow.add(lp);
	}
	
	private void initWindow()
	{
		mainWindow.setTitle(TITLE);
		mainWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		Game g = new Game();
	}
}