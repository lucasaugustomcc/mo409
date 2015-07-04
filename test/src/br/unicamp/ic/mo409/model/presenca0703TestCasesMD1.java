package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class presenca0703TestCasesMD1{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(presenca0703TestCasesMD1.class);
	}
	
	@Test
	public void test1()
	{
		Presenca oTestObject = new Presenca();
		Boolean chamada1 = true;
		Boolean chamada3 = true;
		Boolean chamada5 = true;
		Integer numTicks5 = 65;
		Boolean chamada8 = true;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (chamada1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", chamada1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("checkOutPresencaEvent", chamada3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		assertEquals(true, (chamada5.booleanValue() == true));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5, chamada5);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		assertEquals(true, (chamada8.booleanValue() == true));
		oTestObject.handleEvent("visualizarPresencaEvent", chamada8);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test2()
	{
		Presenca oTestObject = new Presenca();
		Boolean chamada1 = true;
		Boolean chamada3 = true;
		Boolean chamada5 = true;
		Integer numTicks5 = 60;
		Boolean chamada8 = true;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (chamada1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", chamada1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("checkOutPresencaEvent", chamada3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		assertEquals(true, (chamada5.booleanValue() == true));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5, chamada5);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		assertEquals(true, (chamada8.booleanValue() == true));
		oTestObject.handleEvent("visualizarPresencaEvent", chamada8);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test3()
	{
		Presenca oTestObject = new Presenca();
		Boolean chamada1 = true;
		Boolean chamada3 = true;
		Boolean chamada5 = true;
		Integer numTicks5 = -440;
		Boolean chamada8 = true;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (chamada1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", chamada1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("checkOutPresencaEvent", chamada3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		assertEquals(true, (chamada5.booleanValue() == true));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5, chamada5);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		assertEquals(true, (chamada8.booleanValue() == true));
		oTestObject.handleEvent("visualizarPresencaEvent", chamada8);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test4()
	{
		Presenca oTestObject = new Presenca();
		Boolean chamada1 = true;
		Boolean chamada3 = true;
		Boolean chamada5 = true;
		Integer numTicks5 = -1000;
		Boolean chamada8 = true;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (chamada1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", chamada1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("checkOutPresencaEvent", chamada3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		assertEquals(true, (chamada5.booleanValue() == true));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5, chamada5);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		assertEquals(true, (chamada8.booleanValue() == true));
		oTestObject.handleEvent("visualizarPresencaEvent", chamada8);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test5()
	{
		Presenca oTestObject = new Presenca();
		Boolean chamada1 = true;
		Boolean chamada3 = true;
		Boolean chamada5 = true;
		Integer numTicks5 = 999;
		Boolean chamada8 = true;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (chamada1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", chamada1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("checkOutPresencaEvent", chamada3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		assertEquals(true, (chamada5.booleanValue() == true));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5, chamada5);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		assertEquals(true, (chamada8.booleanValue() == true));
		oTestObject.handleEvent("visualizarPresencaEvent", chamada8);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test6()
	{
		Presenca oTestObject = new Presenca();
		Boolean chamada1 = true;
		Boolean chamada3 = true;
		Boolean chamada5 = true;
		Integer numTicks5 = -31;
		Boolean chamada8 = true;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (chamada1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", chamada1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("checkOutPresencaEvent", chamada3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		assertEquals(true, (chamada5.booleanValue() == true));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5, chamada5);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		assertEquals(true, (chamada8.booleanValue() == true));
		oTestObject.handleEvent("visualizarPresencaEvent", chamada8);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test7()
	{
		Presenca oTestObject = new Presenca();
		Boolean chamada1 = true;
		Boolean chamada3 = true;
		Boolean chamada5 = true;
		Integer numTicks5 = 95;
		Boolean chamada8 = true;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (chamada1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", chamada1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("checkOutPresencaEvent", chamada3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		assertEquals(true, (chamada5.booleanValue() == true));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5, chamada5);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		assertEquals(true, (chamada8.booleanValue() == true));
		oTestObject.handleEvent("visualizarPresencaEvent", chamada8);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test8()
	{
		Presenca oTestObject = new Presenca();
		Boolean chamada1 = true;
		Boolean chamada3 = true;
		Boolean chamada5 = true;
		Integer numTicks5 = 455;
		Boolean chamada8 = true;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (chamada1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", chamada1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("checkOutPresencaEvent", chamada3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		assertEquals(true, (chamada5.booleanValue() == true));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5, chamada5);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		assertEquals(true, (chamada8.booleanValue() == true));
		oTestObject.handleEvent("visualizarPresencaEvent", chamada8);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test9()
	{
		Presenca oTestObject = new Presenca();
		Boolean chamada1 = true;
		Boolean chamada3 = true;
		Boolean chamada5 = true;
		Integer numTicks5 = -1000;
		Boolean chamada8 = true;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (chamada1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", chamada1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("checkOutPresencaEvent", chamada3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		assertEquals(true, (chamada5.booleanValue() == true));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5, chamada5);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		assertEquals(true, (chamada8.booleanValue() == true));
		oTestObject.handleEvent("visualizarPresencaEvent", chamada8);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test10()
	{
		Presenca oTestObject = new Presenca();
		Boolean chamada1 = true;
		Boolean chamada3 = true;
		Boolean chamada5 = true;
		Integer numTicks5 = 999;
		Boolean chamada8 = true;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (chamada1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", chamada1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("checkOutPresencaEvent", chamada3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		assertEquals(true, (chamada5.booleanValue() == true));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5, chamada5);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		assertEquals(true, (chamada8.booleanValue() == true));
		oTestObject.handleEvent("visualizarPresencaEvent", chamada8);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test11()
	{
		Presenca oTestObject = new Presenca();
		Boolean chamada1 = true;
		Boolean chamada3 = true;
		Integer numTicks3 = -358;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (chamada1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", chamada1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (chamada3.booleanValue() == true));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks3, chamada3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test12()
	{
		Presenca oTestObject = new Presenca();
		Boolean chamada1 = true;
		Boolean chamada3 = true;
		Integer numTicks3 = 17;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (chamada1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", chamada1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (chamada3.booleanValue() == true));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks3, chamada3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test13()
	{
		Presenca oTestObject = new Presenca();
		Boolean chamada1 = true;
		Boolean chamada3 = true;
		Integer numTicks3 = -293;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (chamada1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", chamada1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (chamada3.booleanValue() == true));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks3, chamada3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test14()
	{
		Presenca oTestObject = new Presenca();
		Boolean chamada1 = true;
		Boolean chamada3 = true;
		Integer numTicks3 = -1000;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (chamada1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", chamada1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (chamada3.booleanValue() == true));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks3, chamada3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test15()
	{
		Presenca oTestObject = new Presenca();
		Boolean chamada1 = true;
		Boolean chamada3 = true;
		Integer numTicks3 = 999;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (chamada1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", chamada1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (chamada3.booleanValue() == true));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks3, chamada3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test16()
	{
		Presenca oTestObject = new Presenca();
		Boolean chamada1 = true;
		Boolean chamada3 = true;
		Integer numTicks3 = -248;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (chamada1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", chamada1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (chamada3.booleanValue() == true));
		oTestObject.handleEvent("receberTickEvent", numTicks3, chamada3);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		
	}
	
	@Test
	public void test17()
	{
		Presenca oTestObject = new Presenca();
		Boolean chamada1 = true;
		Boolean chamada3 = true;
		Integer numTicks3 = -42;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (chamada1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", chamada1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (chamada3.booleanValue() == true));
		oTestObject.handleEvent("receberTickEvent", numTicks3, chamada3);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		
	}
	
	@Test
	public void test18()
	{
		Presenca oTestObject = new Presenca();
		Boolean chamada1 = true;
		Boolean chamada3 = true;
		Integer numTicks3 = -156;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (chamada1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", chamada1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (chamada3.booleanValue() == true));
		oTestObject.handleEvent("receberTickEvent", numTicks3, chamada3);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		
	}
	
	@Test
	public void test19()
	{
		Presenca oTestObject = new Presenca();
		Boolean chamada1 = true;
		Integer numTicks1 = -725;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (chamada1.booleanValue() == true));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks1, chamada1);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test20()
	{
		Presenca oTestObject = new Presenca();
		Boolean chamada1 = true;
		Integer numTicks1 = 23;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (chamada1.booleanValue() == true));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks1, chamada1);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test21()
	{
		Presenca oTestObject = new Presenca();
		Boolean chamada1 = true;
		Integer numTicks1 = 95;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (chamada1.booleanValue() == true));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks1, chamada1);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
}