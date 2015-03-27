package com.main;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.main.ProcessController;
import com.utility.Frame;
import com.utility.Page;

public class TestProcessController {
	
	private ProcessController pc;
	
	@Before
	public void setup()
	{
		this.pc = new ProcessController();
	}
	
	@Test
	public void testSomething()
	{
		assertTrue(true);
	}
	
	@Test
	public void testGetProcess()
	{
		throw new RuntimeException("boopadoop");
	}
	
	@Test
	public void testGetReference()
	{
		throw new RuntimeException("boopadoop");
	}
	
	@Test
	public void testSetReference()
	{
		throw new RuntimeException("boopadoop");
	}
	
	@Test
	public void testScanTLB()
	{
		throw new RuntimeException("boopadoop");
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
	public void testResolvePageFault()
	{
		throw new RuntimeException("boopadoop");
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
			pc.addToMainMem(new Frame(), i);
		}
		
		boolean testFull = pc.isMainMemoryFull();
		assertTrue(testFull);
	}
	
	public void testIsVirtMemoryFull()
	{
		assertFalse(pc.isVirtMemoryFull());
		
		for(int i = 0; i < 500; i++)
		{
			pc.addToVirtMem(new Frame(), i);
		}
		
		assertTrue(pc.isVirtMemoryFull());
	}
}
