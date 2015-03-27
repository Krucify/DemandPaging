package com.utility;

import java.util.Random;

public class Proc {
	private int id;
	private Reference[] references;
	private Random rn;
	
	public Proc(int id)
	{
		this.id = id;
		references = new Reference[10];
		this.rn = new Random();
		rn.setSeed(id);
		
		for(int i = 0; i < 10; i++)
		{
			references[i] = new Reference(id, i, rn.nextInt(2000));
		}
	}
	
	public Reference getReference()
	{
		int rand = rn.nextInt(10);
		return references[rand];
	}
}
