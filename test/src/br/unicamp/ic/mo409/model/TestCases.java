package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class TestCases{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(TestCases.class);
	}
	
	@Test
	public void test1()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks5 = 495;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("checkOutPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test2()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks5 = 331;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("checkOutPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test3()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks5 = 186;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("checkOutPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test4()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks3 = 79;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("receberTickEvent", numTicks3);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		
	}
	
	@Test
	public void test5()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks3 = 37;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("receberTickEvent", numTicks3);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		
	}
	
	@Test
	public void test6()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks3 = -41;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("receberTickEvent", numTicks3);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		
	}
	
	@Test
	public void test7()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks3 = 546;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test8()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks3 = 167;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test9()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks3 = -724;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test10()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks1 = -192;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks1);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test11()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks1 = -97;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks1);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test12()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks1 = -700;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks1);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
}