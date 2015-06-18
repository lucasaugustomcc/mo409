package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class presencaTestCases{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(presencaTestCases.class);
	}
	
	@Test
	public void test1()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks5 = 303;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("checkOutPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test2()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks5 = -111;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("checkOutPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test3()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks5 = 543;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("checkOutPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test4()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks5 = -1000;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("checkOutPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test5()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks5 = 999;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("checkOutPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test6()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks3 = -161;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test7()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks3 = 307;
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
		Integer numTicks3 = -1;
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
		Integer numTicks3 = -1000;
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
		Integer numTicks3 = 999;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test11()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks3 = -216;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("receberTickEvent", numTicks3);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		
	}
	
	@Test
	public void test12()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks3 = 40;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("receberTickEvent", numTicks3);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		
	}
	
	@Test
	public void test13()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks3 = 138;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent");
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("receberTickEvent", numTicks3);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		
	}
	
	@Test
	public void test14()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks1 = 369;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks1);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test15()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks1 = -253;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks1);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test16()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks1 = 157;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks1);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test17()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks1 = -1000;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks1);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test18()
	{
		Presenca oTestObject = new Presenca();
		Integer numTicks1 = 999;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks1);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
}