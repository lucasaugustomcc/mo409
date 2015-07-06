package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class a{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(a.class);
	}
	
	@Test
	public void test1()
	{
		Tick oTestObject = new Tick();
		Integer timestamp1 = -289;
		Integer latitude1 = 561;
		Integer longitude1 = 375;
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		oTestObject.handleEvent("registraTickEvent", timestamp1, latitude1, longitude1);
		assertEquals(true, (oTestObject.state == TickState.invalido));
		
	}
	
	@Test
	public void test2()
	{
		Tick oTestObject = new Tick();
		Integer timestamp1 = -428;
		Integer latitude1 = 27;
		Integer longitude1 = 140;
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		oTestObject.handleEvent("registraTickEvent", timestamp1, latitude1, longitude1);
		assertEquals(true, (oTestObject.state == TickState.invalido));
		
	}
	
	@Test
	public void test3()
	{
		Tick oTestObject = new Tick();
		Integer timestamp1 = 580;
		Integer latitude1 = 352;
		Integer longitude1 = 263;
		assertEquals(true, (oTestObject.state == TickState.indefinido));
		oTestObject.handleEvent("registraTickEvent", timestamp1, latitude1, longitude1);
		assertEquals(true, (oTestObject.state == TickState.invalido));
		
	}
	
}