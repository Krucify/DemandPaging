
package com.main;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestGraphicController {
	private GraphicController gc;

	
	@Test
	public void testTimeSlider()
	{
		this.gc = new GraphicController(5);
		Integer test = gc.getDelaySlider();
		assertSame(test.getClass(), Integer.class);
	}
}
