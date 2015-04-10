package com.main;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.main.ProcessController;

import com.utility.Frame;
import com.utility.Page;

import com.utility.*;
import static org.easymock.EasyMock.*;  

public class TestProcessController {
	private ProcessController pc;
	private Proc p;
	private Reference ref;
	private Frame frame;
	private int id;
	private int value;
	
	@Before
	public void setup()
	{
		this.pc = new ProcessController();

		id = 0;
		value = 43;
		this.pc = new ProcessController();
		this.p = createMock("SampleProcess", Proc.class);
		this.ref = createMock("SampleReference", Reference.class);
		this.frame = createMock("SampleFrame", Frame.class);
	}
	
	@Test
	public void testGetProcess()
	{
		throw new RuntimeException();
	}
	
	@Test
	public void testGetReference()
	{
		expect(p.getReference()).andReturn(ref);
		replay(p);
		Reference testRef = pc.getReference(p);
		assertEquals(ref, testRef);
		verify(p);
		p = null;
	}
	
	@Test
	public void testSetReference()
	{
		expect(ref.isSet()).andReturn(false);
		ref.set();
		expectLastCall();
		replay(ref);
		Boolean result = pc.setReference(ref);
		assertSame(ref.set, result);
		verify(ref);
		ref = null;
	}
	
	@Test
	public void testScanTLB()
	{
		throw new RuntimeException();
	}
	
	@Test
	public void testIsPageFaultWhenNot()
	{
		Page dummy = EasyMock.createMock("ValidPage", Page.class);
		EasyMock.expect(dummy.isValid()).andReturn(true);
		EasyMock.replay(dummy);
		
		assertFalse(pc.isPageFault(dummy));
		
		EasyMock.verify(dummy);
	}
	
	@Test
	public void testIsPageFaultWhenFault()
	{
		Page dummy = EasyMock.createMock("InvalidPage", Page.class);
		EasyMock.expect(dummy.isValid()).andReturn(false);
		EasyMock.replay(dummy);
		
		assertTrue(pc.isPageFault(dummy));
		
		EasyMock.verify(dummy);
	}
	
	@Test
	public void testIsPageFault()
	{
		throw new RuntimeException();
	}
	
	@Test()

	public void testResolvePageFault()
	{
		throw new RuntimeException();
	}
	
	@Test
	public void testMemorySwap()
	{
		
	}
	
	@Test
	public void testIsMainMemoryFull()
	{
		boolean testEmpty = pc.isMainMemoryFull();
		
		assertFalse(testEmpty);
		
		for(int i = 0; i < 30; i++)
		{
			pc.addToMainMem(new Frame(4), i);
		}
		
		boolean testFull = pc.isMainMemoryFull();
		assertTrue(testFull);
	}
	
	@Test
	public void testIsVirtMemoryFull()
	{
		assertFalse(pc.isVirtMemoryFull());
		
		for(int i = 0; i < 500; i++)
		{
			pc.addToVirtMem(new Frame(4), i);
		}
		
		assertTrue(pc.isVirtMemoryFull());
	}
	
	@Test
	public void testGetMainMemSlot()
	{
		int index = pc.getMainSlot();
		
		assertFalse(index == -1);
	}
	
	@Test
	public void testGetMainMemSlotWhenFull()
	{
		for(int i = 0; i < 30; i++)
		{
			pc.addToMainMem(new Frame(4), i);
		}
		
		int index = pc.getMainSlot();
		
		assertTrue(index == -1);
	}
	
	@Test
	public void testGetVirtMemSlot()
	{
		int index = pc.getVirtSlot();
		
		assertFalse(index == -1);
	}
	
	@Test
	public void testGetVirtMemSlotWhenFull()
	{
		for(int i = 0; i < 500; i++)
		{
			pc.addToVirtMem(new Frame(4), i);
		}
		
		int index = pc.getVirtSlot();
		
		assertTrue(index == -1);
	}
	
	@Test
	public void testAddToAndGetMainMem()
	{
		pc.addToMainMem(frame, 0);
		Frame test = pc.getFromMain(0);
		
		assertSame(frame, test);
	}
	
	@Test(expected=RuntimeException.class)
	public void testAddToMainMemOverwrite()
	{
		Frame dummy = new Frame(4);
		pc.addToMainMem(dummy, 0);
		pc.addToMainMem(frame, 0);
	}
	
	@Test
	public void testAddToAndGetVirtMem()
	{
		pc.addToVirtMem(frame, 0);
		Frame test = pc.getFromVirt(0);
		
		assertSame(frame, test);
	}
	
	@Test(expected=RuntimeException.class)
	public void testAddToVirtMemOverwrite()
	{
		Frame dummy = new Frame(4);
		pc.addToVirtMem(dummy, 0);
		pc.addToVirtMem(frame, 0);
	}
	
	
	@Test(expected=RuntimeException.class)
	public void testGetFromMainError()
	{
		pc.getFromMain(0);
	}
	
	
	@Test(expected=RuntimeException.class)
	public void testGetFromVirtError()
	{
		pc.getFromVirt(0);
	}
}
