package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class tick0706bTestCasesMD1{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(tick0706bTestCasesMD1.class);
	}
	
	@Test
	public void test1()
	{
		Tick oTestObject = new Tick();
		Integer timestamp1 = 190;
		Integer latitude1 = 34;
		Integer longitude1 = 17;
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		oTestObject.handleEvent("registrarTickEvent", timestamp1, latitude1, longitude1);
		assertEquals(true, (oTestObject.state == TickState.invalido));
		
	}
	
	@Test
	public void test2()
	{
		Tick oTestObject = new Tick();
		Integer timestamp1 = 130;
		Integer latitude1 = 562;
		Integer longitude1 = 525;
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		oTestObject.handleEvent("registrarTickEvent", timestamp1, latitude1, longitude1);
		assertEquals(true, (oTestObject.state == TickState.invalido));
		
	}
	
	@Test
	public void test3()
	{
		Tick oTestObject = new Tick();
		Integer timestamp1 = 23;
		Integer latitude1 = 92;
		Integer longitude1 = 57;
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		oTestObject.handleEvent("registrarTickEvent", timestamp1, latitude1, longitude1);
		assertEquals(true, (oTestObject.state == TickState.invalido));
		
	}
	
}