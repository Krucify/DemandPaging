package com.main;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.utility.Page;

public class TestVictimSelector {
	
	private Page[][] pageTbl;
	private VictimSelector vs;
	
	@Before
	public void setup()
	{
		pageTbl = null;
		GraphicController mockGraphic = new GraphicController(); //make this a mock
		vs = new VictimSelector(mockGraphic, pageTbl);
	}
	
	@Test
	public void testAddToQueue()
	{
		Page dummy = new Page(12);
		vs.addToQueue(dummy);
		assertTrue("Page is added to queue: ",vs.isInQueue(dummy));
	}
	
	@Test
	public void testRemoveFromQueue()
	{
		Page dummy = new Page(12);
		vs.addToQueue(dummy);
		assertTrue("Page is added to queue: ",vs.isInQueue(dummy));
		vs.removeFromQueue(dummy);
		assertFalse("Page is removed from queue: ", vs.isInQueue(dummy));
	}
	
	@Test
	public void testSecondChance()
	{
		Page dummy = EasyMock.createMock("DirtyPage", Page.class);
		Page dummyVic = EasyMock.createMock("VictimPage", Page.class);
		
		EasyMock.expect(dummy.isDirty()).andReturn(true);
		EasyMock.expect(dummyVic.isDirty()).andReturn(false);
		dummy.setDirty(false);
		EasyMock.expectLastCall();
		
		EasyMock.replay(dummy);
		EasyMock.replay(dummyVic);
		
		vs.addToQueue(dummy);
		vs.addToQueue(dummyVic);
		
		Page testPage = vs.findVictim();
		
		EasyMock.verify(dummy);
		EasyMock.verify(dummyVic);
		
		assertSame("Victim should be undirtied page: ", testPage, dummyVic);
	}
}
