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
	}
	
	@Test
	public void testSomething()
	{
		throw new RuntimeException();
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
