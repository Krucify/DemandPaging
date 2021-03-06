package com.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Set;

import com.utility.*;

public class ProcessController {
	//INPUT VARIABLES
	private int virtMemSize;

	private GraphicController graphics;
	private VictimSelector victim;
	private Frame[] mainMem;
	private Frame[] virtMem;
	private boolean memFilled;
	private Page[][] pageTbl;
	private List<Proc> processes;
	private float delay; 
	private Map<Page, Integer> TLB;
	private int testVar;
	
	public ProcessController()
	{
		setup(5);
	}
	
	public ProcessController(GraphicController graphics, int processes)
	{
		this.graphics = graphics;
		graphics.setVisible(true);
		setup(processes);
		graphics.invokeUpdatePageTable(pageTbl);
	}
	
	public void setup(int numProc)
	{
		this.victim = new VictimSelector(graphics, pageTbl);
		this.mainMem = new Frame[30];
		this.virtMem = new Frame[numProc*10];
		this.memFilled = false;
		this.processes = new ArrayList<Proc>();
		this.pageTbl = new Page[numProc][10];
		this.TLB = new HashMap<Page, Integer>();
		this.delay = 1;
		
		for(int i = 0; i < numProc; i++)
		{
			Proc temp = new Proc(i);
			processes.add(temp);
		}
	}
	
	public void setWorkTest(int test)
	{
		this.testVar = test;
	}
	
	public void work(boolean debug) throws InterruptedException
	{
		Proc process = null;
		Reference reference = null;
		Page demPage = null;
		
		do							 													//Work infinitely
		{
			setDelay(graphics.getDelaySlider());										//Set delay
			if(!debug)
			{
				process = getProcess();														//Get random process
				reference = getReference(process);											//Get random reference
			} else
			{
				switch(testVar)
				{
				case 1:
					process = processes.get(0);
					reference = new Reference(0, 0, 100);
					reference.set();
					pageTbl[0][0] = new Page(0, false);
					mainMem[0] = new Frame(100);
					TLB.put(pageTbl[0][0], 1);
					break;
				case 2:
					process = processes.get(0);
					reference = new Reference(0, 0, 100);
					reference.set();
					pageTbl[0][0] = new Page(0, false);
					mainMem[0] = new Frame(100);
					for(int i=0; i<10; i++)
						TLB.put(new Page(4, false), 5);
					TLB.put(pageTbl[0][0], 1);
					break;
				case 3:
					process = processes.get(0);
					reference = new Reference(0, 0, 100);
					reference.set();
					Page testPage = new Page(0, true);
					Page faultPage = new Page(0, false);
					pageTbl[0][0] = testPage;
					pageTbl[1][0] = faultPage;
					victim.addToQueue(faultPage);
					mainMem[0] = new Frame(100);
					virtMem[0] = new Frame(500);
					break;
				default:
					process = getProcess();	
					reference = getReference(process);
					break;
				}
			}
			
			System.out.println("Got Process: " + process.getId());
			graphics.invokeUpdateConsole("Got Process: " + process.getId());
			graphics.setProcessInfo("Process: " + process.getId());
			
			System.out.println("Got Process' reference in Table:  " + reference.getTblIndex() + "; Page: " + reference.getPageIndex());
			graphics.invokeUpdateConsole("Got Process' reference in Table:  " + reference.getTblIndex() + "; Page: " + reference.getPageIndex());
			graphics.setReferencePane(String.valueOf(reference.getTblIndex()) + '-' + String.valueOf(reference.getPageIndex()));
			
			if(setReference(reference))													//Test if reference is set, if not, set
			{
				demPage = pageTbl[reference.getTblIndex()][reference.getPageIndex()];	//Get page for TLB referencing
				graphics.invokeUpdatePageTable(pageTbl);								//GUI: Update Page Table
				
				if(TLB.containsKey(demPage))											//Is page in TLB?
				{
					System.out.println("Scanning TLB for page.");
					graphics.invokeUpdateConsole("Scanning TLB for page.");
					Thread.sleep((long)(200 * delay));
					if(scanTLB(demPage)) //scanning needs to take time					//Scan TLB, takes time
					{
						System.out.println("Found page in TLB");
						graphics.invokeUpdateConsole("Found page in TLB");
					} else
					{
						System.out.println("Not in TLB, accessing Page Table");
						graphics.invokeUpdateConsole("Not in TLB, accessing Page Table");
						Thread.sleep((long)(500 * delay));
						System.out.println("Got page: " + demPage.getMemIndex());
						graphics.invokeUpdateConsole("Got page: " + demPage.getMemIndex());
					}
					TLB.put(demPage, TLB.get(demPage) + 1);								//Increment usage
				}
				else
					TLB.put(demPage, 1);												//Otherwise, place
					
				if(isPageFault(demPage))												//Is page in virt mem?
				{	
					System.out.println("Page is invalid. Index in Virtual Memory: " + demPage.getMemIndex());
					graphics.invokeUpdateConsole("Page is invalid. Index in Virtual Memory: " + demPage.getMemIndex());
					resolvePageFault(demPage);											//Resolve fault. Takes time
					Thread.sleep((long)(1000 * delay));
					System.out.println("Victim page found. Fault resolved. Index in Main Memory: " + demPage.getMemIndex());
					graphics.invokeUpdateConsole("Victim page found. Fault resolved. Index in Main Memory: " + demPage.getMemIndex());
				}
				
				System.out.println("Accessing Memory for reference's value.");
				graphics.invokeUpdateConsole("Accessing Memory for reference's value.");
				Thread.sleep((long)(500 * delay));
				demPage.setDirty(true);													//Set page dirty
				victim.addToQueue(demPage);												//Set page into current page queue
				//Draw page in page table
				//Get from memory, draw in memory
				Frame frame = getFromMain(demPage.getMemIndex());
				System.out.println("Reference Value: " + reference.getValue() + "; Frame Value: " + frame.getValue() + "\n");
				graphics.invokeUpdateConsole("Reference Value: " + reference.getValue() + "; Frame Value: " + frame.getValue() + "\n");
				graphics.setReferencePane(String.valueOf(frame.getValue()));
			} else
			{
				System.err.println("VIRTUAL MEMORY FULL.");
				throw new RuntimeException("All Memory Is Full");
			}
		} while(!debug);
	}
	
	public void setDelay(float delay)
	{
		if (delay==0)
			delay = 1;
		
		this.delay = (1/delay)*50;
	}
	
	public Proc getProcess()
	{
		Random rn = new Random();
		rn.setSeed(rn.nextLong());
		int rand = rn.nextInt(processes.size());
		Proc process = processes.get(rand);
		return process;
	}
	
	public Reference getReference(Proc process)
	{
		return process.getReference();
	}
	
	public boolean setReference(Reference reference)
	{
		if(!reference.isSet())
		{
			int memIndex;
			Frame frame;
			Page page;
			
			if(!isMainMemoryFull())
			{
				memIndex = getMainSlot();
				frame = new Frame(reference.getValue());
				mainMem[memIndex] = frame;
				
				page = new Page(memIndex, false);
				pageTbl[reference.getTblIndex()][reference.getPageIndex()] = page;
				graphics.invokeUpdatePageTable(pageTbl);
				
				reference.set();
				System.out.println("Reference set in main memory. Index: " + memIndex);
				graphics.invokeUpdateConsole("Reference set in main memory. Index: " + memIndex);
				graphics.invokeUpdateMainMemory(mainMem);
				return true;
			} else
			{
				if(isVirtMemoryFull())
					return false;
				
				memIndex = getVirtSlot();
				frame = new Frame(reference.getValue());
				virtMem[memIndex] = frame;
				
				page = new Page(memIndex, true);
				pageTbl[reference.getTblIndex()][reference.getPageIndex()] = page;
				graphics.invokeUpdatePageTable(pageTbl);
				
				reference.set();
				System.out.println("Reference set in virtual memory. Index: " + memIndex);
				graphics.invokeUpdateConsole("Reference set in virtual memory. Index: " + memIndex);
				graphics.invokeUpdateVirtualMemory(virtMem);
				return true;
			}
			//Set into memory AND page table
			//****'done, draw to graphics
			 
		}
		System.out.println("Reference is already set in memory.");
		graphics.invokeUpdateConsole("Reference is already set in memory.");
		
		return true;
	}
	
	public boolean scanTLB(Page page)
	{
		MapUtil mapper = new MapUtil();
		
		Map<Page, Integer> sortedMap = mapper.sortByValue(TLB);
		graphics.invokeUpdateTLB(sortedMap.keySet().toArray(new Page[5]));

		int i = 0;
		for(Entry<Page, Integer> entry : sortedMap.entrySet())
		{
			if(page == entry.getKey()) {
				//draw this ordered TLB to graphics
				return true;
			}
			
			if(++i > 5)
				break;
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
		vicPage = victim.findVictim();		//Gets victim from vicSelector
		int mainMemLoc, virtMemLoc;
		
		mainMemLoc = vicPage.getMemIndex();	//Gets memory indexes for swap
		virtMemLoc = demPage.getMemIndex();
		
		dem = virtMem[virtMemLoc];			//Get frames before swap
		vic = mainMem[mainMemLoc];
		
		mainMem[mainMemLoc] = dem;			//Swap frames
		virtMem[virtMemLoc] = vic;

		graphics.invokeUpdateMainMemory(mainMem);
		graphics.invokeUpdateVirtualMemory(virtMem);
		
		vicPage.setDirty(false);			//Adjust page values.
		vicPage.setValid(false);
		vicPage.setMemIndex(virtMemLoc);
		
		demPage.setDirty(true);
		demPage.setValid(true);
		demPage.setMemIndex(mainMemLoc);
		
		victim.removeFromQueue(vicPage);
		
		return demPage;						//Return page
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
	
	public Frame getFromMain(int index)
	{
		if(mainMem[index] == null)
			throw new RuntimeException("Attempting to access null memory from Main.");
		
		return mainMem[index];
	}
	
	public Frame getFromVirt(int index)
	{
		if(virtMem[index] == null)
			throw new RuntimeException("Attempting to access null memory from Virt.");		
		
		return virtMem[index];
	}
}
