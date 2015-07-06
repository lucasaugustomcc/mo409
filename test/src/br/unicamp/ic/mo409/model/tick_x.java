package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class tick_x{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(tick_x.class);
	}
	
	@Test
	public void test1()
	{
		Presenca oTestObject = new Presenca();
		Integer latitude1 = 398;
		Integer longitude1 = 634;
		Integer timestamp1 = -105;
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		oTestObject.handleEvent("registraTickEvent", latitude1, longitude1, timestamp1);
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		
	}
	
	@Test
	public void test2()
	{
		Presenca oTestObject = new Presenca();
		Integer latitude1 = -2;
		Integer longitude1 = 180;
		Integer timestamp1 = -691;
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		oTestObject.handleEvent("registraTickEvent", latitude1, longitude1, timestamp1);
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		
	}
	
	@Test
	public void test3()
	{
		Presenca oTestObject = new Presenca();
		Integer latitude1 = -519;
		Integer longitude1 = 0;
		Integer timestamp1 = -777;
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		oTestObject.handleEvent("registraTickEvent", latitude1, longitude1, timestamp1);
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		
	}
	
}