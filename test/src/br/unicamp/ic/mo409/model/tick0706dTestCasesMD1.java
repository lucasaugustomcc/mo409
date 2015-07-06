package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class tick0706dTestCasesMD1{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(tick0706dTestCasesMD1.class);
	}
	
	@Test
	public void test1()
	{
		Tick oTestObject = new Tick();
		Integer timestamp1 = -280;
		Integer latitude1 = -447;
		Integer longitude1 = -405;
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		oTestObject.handleEvent("registraTickEvent", timestamp1, latitude1, longitude1);
		assertEquals(true, (oTestObject.state == TickState.invalido));
		
	}
	
	@Test
	public void test2()
	{
		Tick oTestObject = new Tick();
		Integer timestamp1 = -222;
		Integer latitude1 = -305;
		Integer longitude1 = 2;
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		oTestObject.handleEvent("registraTickEvent", timestamp1, latitude1, longitude1);
		assertEquals(true, (oTestObject.state == TickState.invalido));
		
	}
	
	@Test
	public void test3()
	{
		Tick oTestObject = new Tick();
		Integer timestamp1 = -27;
		Integer latitude1 = -79;
		Integer longitude1 = -108;
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		oTestObject.handleEvent("registraTickEvent", timestamp1, latitude1, longitude1);
		assertEquals(true, (oTestObject.state == TickState.invalido));
		
	}
	
}