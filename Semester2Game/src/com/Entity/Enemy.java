package com.Entity;

import com.Sprite.Sprite;

public class Enemy extends GameObject
{
	public Enemy(String fileName) 
	{
		super(tmm);
		
		Sprite s = new Sprite(1);
		s.addFrame("/images/" + fileName);
	}
}