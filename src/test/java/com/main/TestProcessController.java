package com.main;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.main.ProcessController;
import com.utility.*;

import static org.easymock.EasyMock.*;  

public class TestProcessController {
	private ProcessController pc;
	private Proc p;
	private Reference ref;
	private Frame frame;
	private GraphicController gc;
	private int id;
	private int value;
	private int frameSize;
	private Page[][] testPageTbl;
	private Frame[] testFrame;
	
	@Before
	public void setup()
	{
		this.pc = new ProcessController();

		id = 0;
		value = 43;
		frameSize = 50;
		testPageTbl = new Page[5][10];
		testFrame = new Frame[frameSize];
		
		this.gc = createNiceMock("SampleGUI", GraphicController.class);
		this.p = createMock("SampleProcess", Proc.class);
		this.ref = createMock("SampleReference", Reference.class);
		this.frame = createMock("SampleFrame", Frame.class);
		
		this.pc = new ProcessController(gc, 5);
	}

	
	@Test
	public void testGetProcess()
	{
		Proc testProc = pc.getProcess();
		assertSame(Proc.class, testProc.getClass());
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
	public void testSetReferenceNotSetMainMemNotFull()
	{
		expect(ref.isSet()).andReturn(false);
		expect(ref.getValue()).andStubReturn(1);
		expect(ref.getTblIndex()).andStubReturn(1);	
		expect(ref.getPageIndex()).andStubReturn(1);
		
		ref.set();
		expectLastCall();
		replay(ref);
		
		Boolean shouldNotBeFull = pc.isMainMemoryFull();
		Boolean result = pc.setReference(ref);
		
		assertTrue(result);
		assertFalse(shouldNotBeFull);
		
		verify(ref);
		ref = null;
	}
	
	@Test
	public void testSetReferenceNotSetMainMemFull()
	{
		// Fill Main Memory
		for(int i = 0; i < 30; i++)
		{
			pc.addToMainMem(new Frame(4), i);
		}
		
		// Setup Mock calls
		expect(ref.isSet()).andReturn(false);
		expect(ref.getValue()).andStubReturn(1);;
		expect(ref.getTblIndex()).andStubReturn(1);	
		expect(ref.getPageIndex()).andStubReturn(1);
		
		ref.set();
		expectLastCall();
		replay(ref);
		
		Boolean shouldBeFull = pc.isMainMemoryFull();
		Boolean result = pc.setReference(ref);
		
		assertTrue(result);
		assertTrue(shouldBeFull);
		
		verify(ref);
		ref = null;
	}
	
	@Test
	public void testSetReferenceNotSetMainAndVirtFull()
	{
		// Fill Main Memory
		for(int i = 0; i < 30; i++)
		{
			pc.addToMainMem(new Frame(4), i);
		}
		// Fill Virt Mem
		for(int i = 0; i < frameSize; i++)
		{
			pc.addToVirtMem(new Frame(4), i);
		}
		
		// Setup Mock calls
		expect(ref.isSet()).andReturn(false);
		expect(ref.getValue()).andStubReturn(1);;
		expect(ref.getTblIndex()).andStubReturn(1);	
		expect(ref.getPageIndex()).andStubReturn(1);
		
		replay(ref);
		
		Boolean shouldBeFull = pc.isMainMemoryFull();
		Boolean shouldIsFull = pc.isVirtMemoryFull();
		Boolean result = pc.setReference(ref);
		
		assertFalse(result);
		assertTrue(shouldBeFull);
		assertTrue(shouldIsFull);
		
		verify(ref);
		ref = null;
	}
	
	@Test
	public void testSetReferenceAlreadySet()
	{
		expect(ref.isSet()).andReturn(true);
		replay(ref);
		
		Boolean result = pc.setReference(ref);
		
		assertTrue(result);
		
		verify(ref);
		ref = null;
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
		
		for(int i = 0; i < 50; i++)
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
		for(int i = 0; i < 50; i++)
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
	
	@Test
	public void testWork()
	{		
		expect(gc.getDelaySlider()).andReturn(10000);
		replay(gc);
		
		try {
			pc.work(true);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(expected=RuntimeException.class)
	public void testWorkWhenMemFull()
	{
		for(int i = 0; i < 30; i++)
			pc.addToMainMem(new Frame(4), i);
		
		for(int i = 0; i < 50; i++)
			pc.addToVirtMem(new Frame(4), i);
		
		expect(gc.getDelaySlider()).andReturn(1);
		replay(gc);
		
		try {
			pc.work(true);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testWorkWhenTLBContainsPage()
	{		
		expect(gc.getDelaySlider()).andReturn(10000);
		replay(gc);
		
		pc.setWorkTest(1);
		
		try {
			pc.work(true);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testWorkWhenTLBLacksPage()
	{		
		expect(gc.getDelaySlider()).andReturn(10000);
		replay(gc);
		
		pc.setWorkTest(2);
		
		try {
			pc.work(true);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testWorkWhenPageFault()
	{		
		expect(gc.getDelaySlider()).andReturn(10000);
		replay(gc);
		
		pc.setWorkTest(3);
		
	
		try {
			pc.work(true);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testWorkWhenNoFault()
	{		
		expect(gc.getDelaySlider()).andReturn(10000);
		replay(gc);
		
		try {
			pc.work(true);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
