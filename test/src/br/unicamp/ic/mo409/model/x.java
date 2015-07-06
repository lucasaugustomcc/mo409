package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class x{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(x.class);
	}
	
	@Test
	public void test1()
	{
		Tick oTestObject = new Tick();
		Integer timestamp1 = 2;
		Integer latitude1 = 312;
		Integer longitude1 = -328;
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		oTestObject.handleEvent("registraTickEvent", timestamp1, latitude1, longitude1);
		assertEquals(true, (oTestObject.state == TickState.invalido));
		
	}
	
	@Test
	public void test2()
	{
		Tick oTestObject = new Tick();
		Integer timestamp1 = 151;
		Integer latitude1 = 25;
		Integer longitude1 = 244;
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		oTestObject.handleEvent("registraTickEvent", timestamp1, latitude1, longitude1);
		assertEquals(true, (oTestObject.state == TickState.invalido));
		
	}
	
	@Test
	public void test3()
	{
		Tick oTestObject = new Tick();
		Integer timestamp1 = -27;
		Integer latitude1 = 114;
		Integer longitude1 = 304;
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		oTestObject.handleEvent("registraTickEvent", timestamp1, latitude1, longitude1);
		assertEquals(true, (oTestObject.state == TickState.invalido));
		
	}
	
}