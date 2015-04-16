package com.main;

import java.util.LinkedList;
import java.util.List;

import com.utility.Page;

public class VictimSelector {
	private GraphicController graphics;
	private Page[][] pageTbl;
	private List<Page> pageQueue;
	
	public VictimSelector(GraphicController graphics, Page[][] table)
	{
		this.graphics = graphics;
		this.pageTbl = table;
		this.pageQueue = new LinkedList<Page>();
	}
	
	public Page findVictim()
	{
		Page victem = secondChance();
		
		return victem;
	}
	
	public void addToQueue(Page page)
	{
		removeFromQueue(page);
		pageQueue.add(page);
	}
	
	public boolean isInQueue(Page page)
	{
		if(pageQueue.contains(page))
			return true;
		else
			return false;
	}
	
	public boolean isQueueEmpty()
	{
		return pageQueue.isEmpty();
	}
	
	public void removeFromQueue(Page page)
	{
		if(isInQueue(page))
			pageQueue.remove(page);
		graphics.invokeUpdateVictimQueue(pageQueue.toArray(new Page[pageQueue.size()]));
	}
	
	public Page secondChance()
	{
		Page victim = null;
		boolean vicFound = false;
		
		for(Page page : pageQueue) //Scan through all pages.
		{
			if(page.isDirty()) 			//If current page is dirty, set clean
				page.setDirty(false);
			else if(!vicFound)			//Otherwise if victem is not found, set victem
			{
				victim = page;
				vicFound = true;
			}
		}
		if(!vicFound)
		{
			if(isQueueEmpty())
				throw new RuntimeException("Second Chance: Cannot resolve fault when page queue is empty.");
			else
			{
				victim = pageQueue.get(0);
			}
		}
		
		return victim;
	}
}
