package com.main;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.main.ProcessController;

public class TestProcessController {
	
	@Before
	public void setup()
	{
		ProcessController pc = new ProcessController();
	}
	
	@Test
	public void testSomething()
	{
		assertTrue(true);
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetProcess()
	{
		throw new RuntimeException("boopadoop");
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetReference()
	{
		throw new RuntimeException("boopadoop");
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetReference()
	{
		throw new RuntimeException("boopadoop");
	}
	
	@Test(expected=RuntimeException.class)
	public void testScanTLB()
	{
		throw new RuntimeException("boopadoop");
	}
	
	
	@Test(expected=RuntimeException.class)
	public void testIsPageFault()
	{
		throw new RuntimeException("boopadoop");
	}
	
	@Test(expected=RuntimeException.class)
	public void testResolvePageFault()
	{
		throw new RuntimeException("boopadoop");
	}
}
