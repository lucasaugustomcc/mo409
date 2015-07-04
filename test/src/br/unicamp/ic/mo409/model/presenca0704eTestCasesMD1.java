package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class presenca0704eTestCasesMD1{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(presenca0704eTestCasesMD1.class);
	}
	
	@Test
	public void test1()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = true;
		Boolean isChamadaAberta3 = true;
		Boolean isChamadaAberta5 = false;
		Integer numTicks5 = -73;
		Boolean isChamadaAberta8 = false;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (isChamadaAberta3.booleanValue() == true));
		oTestObject.handleEvent("checkOutPresencaEvent", isChamadaAberta3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		assertEquals(true, (isChamadaAberta5.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5, isChamadaAberta5);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		assertEquals(true, (isChamadaAberta8.booleanValue() == false));
		oTestObject.handleEvent("visualizarPresencaEvent", isChamadaAberta8);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test2()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = true;
		Boolean isChamadaAberta3 = true;
		Boolean isChamadaAberta5 = false;
		Integer numTicks5 = -1;
		Boolean isChamadaAberta8 = false;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (isChamadaAberta3.booleanValue() == true));
		oTestObject.handleEvent("checkOutPresencaEvent", isChamadaAberta3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		assertEquals(true, (isChamadaAberta5.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5, isChamadaAberta5);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		assertEquals(true, (isChamadaAberta8.booleanValue() == false));
		oTestObject.handleEvent("visualizarPresencaEvent", isChamadaAberta8);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test3()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = true;
		Boolean isChamadaAberta3 = true;
		Boolean isChamadaAberta5 = false;
		Integer numTicks5 = -72;
		Boolean isChamadaAberta8 = false;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (isChamadaAberta3.booleanValue() == true));
		oTestObject.handleEvent("checkOutPresencaEvent", isChamadaAberta3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		assertEquals(true, (isChamadaAberta5.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5, isChamadaAberta5);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		assertEquals(true, (isChamadaAberta8.booleanValue() == false));
		oTestObject.handleEvent("visualizarPresencaEvent", isChamadaAberta8);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test4()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = true;
		Boolean isChamadaAberta3 = true;
		Boolean isChamadaAberta5 = false;
		Integer numTicks5 = -1000;
		Boolean isChamadaAberta8 = false;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (isChamadaAberta3.booleanValue() == true));
		oTestObject.handleEvent("checkOutPresencaEvent", isChamadaAberta3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		assertEquals(true, (isChamadaAberta5.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5, isChamadaAberta5);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		assertEquals(true, (isChamadaAberta8.booleanValue() == false));
		oTestObject.handleEvent("visualizarPresencaEvent", isChamadaAberta8);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test5()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = true;
		Boolean isChamadaAberta3 = true;
		Boolean isChamadaAberta5 = false;
		Integer numTicks5 = 999;
		Boolean isChamadaAberta8 = false;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (isChamadaAberta3.booleanValue() == true));
		oTestObject.handleEvent("checkOutPresencaEvent", isChamadaAberta3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		assertEquals(true, (isChamadaAberta5.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5, isChamadaAberta5);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		assertEquals(true, (isChamadaAberta8.booleanValue() == false));
		oTestObject.handleEvent("visualizarPresencaEvent", isChamadaAberta8);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test6()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = true;
		Boolean isChamadaAberta3 = true;
		Boolean isChamadaAberta5 = false;
		Integer numTicks5 = -551;
		Boolean isChamadaAberta8 = false;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (isChamadaAberta3.booleanValue() == true));
		oTestObject.handleEvent("checkOutPresencaEvent", isChamadaAberta3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		assertEquals(true, (isChamadaAberta5.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5, isChamadaAberta5);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		assertEquals(true, (isChamadaAberta8.booleanValue() == false));
		oTestObject.handleEvent("visualizarPresencaEvent", isChamadaAberta8);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test7()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = true;
		Boolean isChamadaAberta3 = true;
		Boolean isChamadaAberta5 = false;
		Integer numTicks5 = 127;
		Boolean isChamadaAberta8 = false;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (isChamadaAberta3.booleanValue() == true));
		oTestObject.handleEvent("checkOutPresencaEvent", isChamadaAberta3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		assertEquals(true, (isChamadaAberta5.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5, isChamadaAberta5);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		assertEquals(true, (isChamadaAberta8.booleanValue() == false));
		oTestObject.handleEvent("visualizarPresencaEvent", isChamadaAberta8);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test8()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = true;
		Boolean isChamadaAberta3 = true;
		Boolean isChamadaAberta5 = false;
		Integer numTicks5 = 69;
		Boolean isChamadaAberta8 = false;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (isChamadaAberta3.booleanValue() == true));
		oTestObject.handleEvent("checkOutPresencaEvent", isChamadaAberta3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		assertEquals(true, (isChamadaAberta5.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5, isChamadaAberta5);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		assertEquals(true, (isChamadaAberta8.booleanValue() == false));
		oTestObject.handleEvent("visualizarPresencaEvent", isChamadaAberta8);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test9()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = true;
		Boolean isChamadaAberta3 = true;
		Boolean isChamadaAberta5 = false;
		Integer numTicks5 = -1000;
		Boolean isChamadaAberta8 = false;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (isChamadaAberta3.booleanValue() == true));
		oTestObject.handleEvent("checkOutPresencaEvent", isChamadaAberta3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		assertEquals(true, (isChamadaAberta5.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5, isChamadaAberta5);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		assertEquals(true, (isChamadaAberta8.booleanValue() == false));
		oTestObject.handleEvent("visualizarPresencaEvent", isChamadaAberta8);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test10()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = true;
		Boolean isChamadaAberta3 = true;
		Boolean isChamadaAberta5 = false;
		Integer numTicks5 = 999;
		Boolean isChamadaAberta8 = false;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (isChamadaAberta3.booleanValue() == true));
		oTestObject.handleEvent("checkOutPresencaEvent", isChamadaAberta3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		assertEquals(true, (isChamadaAberta5.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks5, isChamadaAberta5);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		assertEquals(true, (isChamadaAberta8.booleanValue() == false));
		oTestObject.handleEvent("visualizarPresencaEvent", isChamadaAberta8);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test11()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = true;
		Boolean isChamadaAberta3 = true;
		Integer numTicks3 = -326;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (isChamadaAberta3.booleanValue() == true));
		oTestObject.handleEvent("receberTickEvent", numTicks3, isChamadaAberta3);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		
	}
	
	@Test
	public void test12()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = true;
		Boolean isChamadaAberta3 = true;
		Integer numTicks3 = 107;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (isChamadaAberta3.booleanValue() == true));
		oTestObject.handleEvent("receberTickEvent", numTicks3, isChamadaAberta3);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		
	}
	
	@Test
	public void test13()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = true;
		Boolean isChamadaAberta3 = true;
		Integer numTicks3 = 168;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (isChamadaAberta3.booleanValue() == true));
		oTestObject.handleEvent("receberTickEvent", numTicks3, isChamadaAberta3);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		
	}
	
	@Test
	public void test14()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = true;
		Boolean isChamadaAberta3 = false;
		Integer numTicks3 = -427;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (isChamadaAberta3.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks3, isChamadaAberta3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test15()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = true;
		Boolean isChamadaAberta3 = false;
		Integer numTicks3 = -318;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (isChamadaAberta3.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks3, isChamadaAberta3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test16()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = true;
		Boolean isChamadaAberta3 = false;
		Integer numTicks3 = 71;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (isChamadaAberta3.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks3, isChamadaAberta3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test17()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = true;
		Boolean isChamadaAberta3 = false;
		Integer numTicks3 = -1000;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (isChamadaAberta3.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks3, isChamadaAberta3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test18()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = true;
		Boolean isChamadaAberta3 = false;
		Integer numTicks3 = 999;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (isChamadaAberta3.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks3, isChamadaAberta3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test19()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = false;
		Integer numTicks1 = -13;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks1, isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test20()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = false;
		Integer numTicks1 = -19;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks1, isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test21()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = false;
		Integer numTicks1 = -286;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks1, isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
}