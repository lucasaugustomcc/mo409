package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class presenca0704jTestCasesMD1{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(presenca0704jTestCasesMD1.class);
	}
	
	@Test
	public void test1()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = true;
		Boolean isChamadaAberta3 = true;
		Boolean isChamadaAberta5 = false;
		Integer numTicks5 = -102;
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
		Integer numTicks5 = 877;
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
		Integer numTicks5 = -24;
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
		Integer numTicks5 = 12;
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
		Integer numTicks5 = 299;
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
		Integer numTicks5 = -645;
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
		Boolean isChamadaAberta3 = false;
		Integer numTicks3 = 89;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (isChamadaAberta3.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks3, isChamadaAberta3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test12()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = true;
		Boolean isChamadaAberta3 = false;
		Integer numTicks3 = -41;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (isChamadaAberta3.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks3, isChamadaAberta3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test13()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = true;
		Boolean isChamadaAberta3 = false;
		Integer numTicks3 = -512;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == true));
		oTestObject.handleEvent("checkInPresencaEvent", isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		assertEquals(true, (isChamadaAberta3.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks3, isChamadaAberta3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test14()
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
	public void test15()
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
	public void test16()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = false;
		Integer numTicks1 = -515;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks1, isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test17()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = false;
		Integer numTicks1 = 636;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks1, isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test18()
	{
		Presenca oTestObject = new Presenca();
		Boolean isChamadaAberta1 = false;
		Integer numTicks1 = -135;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		assertEquals(true, (isChamadaAberta1.booleanValue() == false));
		oTestObject.handleEvent("calcularPresencaEvent", numTicks1, isChamadaAberta1);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
}