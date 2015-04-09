package com.utility;

import java.util.Random;

public class Proc {
	private int id;
	private Reference[] references;
	
	public Proc(int id)
	{
		this.id = id;
		references = new Reference[10];
		Random rn = new Random();
		rn.setSeed(id);
		rn.setSeed(rn.nextLong());
		for(int i = 0; i < 10; i++)
		{
			references[i] = new Reference(id, i, rn.nextInt(2000));
		}
	}
	
	public Reference getReference()
	{
		Random rn = new Random();
		rn.setSeed(rn.nextLong());
		int rand = rn.nextInt(10);
		return references[rand];
	}
	
	public int getId()
	{
		return this.id;
	}
}
