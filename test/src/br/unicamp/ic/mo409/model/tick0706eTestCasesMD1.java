package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class tick0706eTestCasesMD1{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(tick0706eTestCasesMD1.class);
	}
	
	@Test
	public void test1()
	{
		Tick oTestObject = new Tick();
		Integer latitude1 = 145;
		Integer longitude1 = -544;
		Integer timestamp1 = 73;
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		oTestObject.handleEvent("registraTickEvent", latitude1, longitude1, timestamp1);
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		
	}
	
	@Test
	public void test2()
	{
		Tick oTestObject = new Tick();
		Integer latitude1 = -222;
		Integer longitude1 = -118;
		Integer timestamp1 = 515;
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		oTestObject.handleEvent("registraTickEvent", latitude1, longitude1, timestamp1);
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		
	}
	
	@Test
	public void test3()
	{
		Tick oTestObject = new Tick();
		Integer latitude1 = 101;
		Integer longitude1 = 219;
		Integer timestamp1 = 49;
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		oTestObject.handleEvent("registraTickEvent", latitude1, longitude1, timestamp1);
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		
	}
	
}