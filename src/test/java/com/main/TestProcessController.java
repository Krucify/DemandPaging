package com.main;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.main.ProcessController;
import com.utility.*;
import static org.easymock.EasyMock.*; 
import org.easymock.EasyMockRule; 
import org.easymock.TestSubject; 
import org.easymock.Mock; 
import org.junit.Rule; 
import org.junit.Test; 

public class TestProcessController {
	ProcessController pc;
	Proc p;
	Reference ref;
	int id;
	int value;
	
	@Before
	public void setup()
	{
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
	
	@Test()
	public void testGetProcess()
	{
		throw new RuntimeException();
	}
	
	@Test()
	public void testGetReference()
	{
		expect(p.getReference()).andReturn(ref);
		replay(p);
		Reference testRef = pc.getReference(p);
		assertEquals(ref, testRef);
		verify(p);
		p = null;
	}
	
	@Test()
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
	
	@Test()
	public void testScanTLB()
	{
		throw new RuntimeException();
	}
	
	
	@Test()
	public void testIsPageFault()
	{
		throw new RuntimeException();
	}
	
	@Test()
	public void testResolvePageFault()
	{
		throw new RuntimeException();
	}
}
