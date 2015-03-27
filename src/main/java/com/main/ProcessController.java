package com.main;

import java.util.ArrayList;
import java.util.List;

import com.utility.*;

public class ProcessController {

	private GraphicController graphics;
	private VictimSelector victim;
	private Frame[] mainMem;
	private Frame[] virtMem;
	private boolean memFilled;
	private Page[][] pageTbl;
	private List<Proc> processes;
	private float delay; 
	
	public ProcessController()
	{
		setup();
	}
	
	public ProcessController(GraphicController graphics)
	{
		this.graphics = graphics;
		setup();
	}
	
	public void setup()
	{
		this.graphics = new GraphicController();
		this.victim = new VictimSelector(graphics, pageTbl);
		this.mainMem = new Frame[30];
		this.virtMem = new Frame[500];
		this.memFilled = false;
		this.processes = new ArrayList<Proc>();
		
		for(int i = 1; i <= 5; i++)
		{
			Proc temp = new Proc(i);
			processes.add(temp);
		}
	}
	
	public void work()
	{
		Proc process;
		Reference reference;
		Page demPage;
		
		while(true)
		{
			process = getProcess();
			reference = getReference(process);
			
			if(setReference(reference))
			{
				demPage = pageTbl[reference.getTblIndex()][reference.getPageIndex()];
				
				if(scanTLB(demPage))
				{
					//page is in tlb, skip straight to memory
					//draw
				}
				
				if(isPageFault(demPage))
					resolvePageFault(demPage);
				
				//Draw page in page table
				//Get from memory, draw in memory
				Frame frame = mainMem[demPage.getMemIndex()];
			}
		}
	}
	
	public void setDelay(float delay)
	{
		this.delay = delay;
	}
	
	public Proc getProcess()
	{
		Proc dummy = new Proc(4);
		return dummy;
	}
	
	public Reference getReference(Proc process)
	{
		return process.getReference();
	}
	
	public boolean setReference(Reference reference)
	{
		if(!reference.isSet())
		{
			//Set into memory AND page table
			//****'done, draw to graphics' 
			
			return false;
		}
		
		return true;
	}
	
	public boolean scanTLB(Page page)
	{
		for(Page tlbPage : pageTbl[0])
		{
			//more drawing
			if(tlbPage == page && page.isValid())
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isPageFault(Page demPage)
	{
		if(demPage.isValid())
			return false;
		else
			return true;
	}
	
	public Page resolvePageFault(Page demPage)
	{
		Page vicPage;
		Frame dem, vic;
		
		//PAGE FAULT
		vicPage = victim.findVictim();
		int mainMemLoc, virtMemLoc;
		
		mainMemLoc = vicPage.getMemIndex();
		virtMemLoc = demPage.getMemIndex();
		
		dem = virtMem[virtMemLoc];
		vic = mainMem[mainMemLoc];
		
		mainMem[mainMemLoc] = dem;
		virtMem[virtMemLoc] = vic;
		//draw all of this
		
		return demPage;
	}
	
	public void memorySwap(Page demanded, Page victem)
	{
		
	}
	
	public void addToMainMem(Frame frame, int index)
	{
		if(mainMem[index] != null)
			throw new IllegalStateException("Attempting to overwrite main memory.");
		else
			mainMem[index] = frame;
	}
	
	public void addToVirtMem(Frame frame, int index)
	{
		if(virtMem[index] != null)
			throw new IllegalStateException("Attempting to overwrite virtual memory.");
		else
			virtMem[index] = frame;
	}
	
	public boolean isMainMemoryFull()
	{
		if(memFilled)
			return true;
		
		for(int i = 0; i < mainMem.length; i++)
		{
			if(mainMem[i] == null)
				return false;
		}
		
		memFilled = true;
		return true;
	}
	
	public boolean isVirtMemoryFull()
	{
		for(int i = 0; i < virtMem.length; i++)
		{
			if(virtMem[i] == null)
				return false;
		}
		
		return true;
	}
	
	public int getVirtSlot()
	{
		for(int i = 0; i < virtMem.length; i++)
		{
			if(virtMem[i] == null)
				return i;
		}
		
		return -1;
	}
	
	public int getMainSlot()
	{
		for(int i = 0; i < mainMem.length; i++)
		{
			if(mainMem[i] == null)
				return i;
		}
		
		return -1;
	}
}
