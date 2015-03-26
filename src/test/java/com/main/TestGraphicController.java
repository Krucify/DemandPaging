
package com.main;

import org.junit.Test;

public class TestGraphicController {
	@Test(expected=RuntimeException.class)
	public void testStart()
	{
		throw new RuntimeException("boopadoop");
	}
	
	@Test(expected=RuntimeException.class)
	public void testPause()
	{
		throw new RuntimeException("boopadoop");
	}
	
	@Test(expected=RuntimeException.class)
	public void testTimeSlider()
	{
		throw new RuntimeException("boopadoop");
	}
}
