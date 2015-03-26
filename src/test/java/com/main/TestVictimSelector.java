package com.main;

import org.junit.Before;
import org.junit.Test;

import com.utility.Page;

public class TestVictimSelector {
	@Before
	public void setup()
	{
		Page[][] pageTbl = null;
		GraphicController mockGraphic = new GraphicController(); //make this a mock
		VictimSelector vs = new VictimSelector(mockGraphic, pageTbl);
	}
	
	@Test(expected=RuntimeException.class)
	public void findVictem()
	{
		throw new RuntimeException("boopadoop");
	}
	
	@Test(expected=RuntimeException.class)
	public void testAddToQueue()
	{
		throw new RuntimeException("boopadoop");
	}
	
	@Test(expected=RuntimeException.class)
	public void testSecondChance()
	{
		throw new RuntimeException("boopadoop");
	}
}
