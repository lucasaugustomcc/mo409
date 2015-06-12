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
		Chamada oTestObject = new Chamada();
		ListaDeDisciplinas disciplina1 = ListaDeDisciplinas.MO409;
		ChamadaState state3 = ChamadaState.nao_aberta;
		Integer tick5 = -67;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("abrirChamadaEvent", disciplina1);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		oTestObject.handleEvent("encerrarChamadaEvent", state3);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		oTestObject.handleEvent("calcularPresencaEvent", tick5);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		
	}
	
	@Test
	public void test2()
	{
		Chamada oTestObject = new Chamada();
		ListaDeDisciplinas disciplina1 = ListaDeDisciplinas.MO409;
		ChamadaState state3 = ChamadaState.nao_aberta;
		Integer tick5 = -176;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("abrirChamadaEvent", disciplina1);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		oTestObject.handleEvent("encerrarChamadaEvent", state3);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		oTestObject.handleEvent("calcularPresencaEvent", tick5);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		
	}
	
	@Test
	public void test3()
	{
		Chamada oTestObject = new Chamada();
		ListaDeDisciplinas disciplina1 = ListaDeDisciplinas.MO409;
		ChamadaState state3 = ChamadaState.nao_aberta;
		Integer tick5 = 304;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("abrirChamadaEvent", disciplina1);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		oTestObject.handleEvent("encerrarChamadaEvent", state3);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		oTestObject.handleEvent("calcularPresencaEvent", tick5);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		
	}
	
	@Test
	public void test4()
	{
		Chamada oTestObject = new Chamada();
		ListaDeDisciplinas disciplina1 = ListaDeDisciplinas.MO409;
		Integer tick3 = -385;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("abrirChamadaEvent", disciplina1);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		assertEquals(true, (oTestObject.recebendo_tick.booleanValue() == true));
		assertEquals(true, (oTestObject.recebendo_tick.booleanValue() != false));
		oTestObject.handleEvent("receberTickEvent", tick3);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		
	}
	
	@Test
	public void test5()
	{
		Chamada oTestObject = new Chamada();
		ListaDeDisciplinas disciplina1 = ListaDeDisciplinas.MO409;
		Integer tick3 = -2;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("abrirChamadaEvent", disciplina1);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		assertEquals(true, (oTestObject.recebendo_tick.booleanValue() == true));
		assertEquals(true, (oTestObject.recebendo_tick.booleanValue() != false));
		oTestObject.handleEvent("receberTickEvent", tick3);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		
	}
	
	@Test
	public void test6()
	{
		Chamada oTestObject = new Chamada();
		ListaDeDisciplinas disciplina1 = ListaDeDisciplinas.MO409;
		Integer tick3 = -217;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("abrirChamadaEvent", disciplina1);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		assertEquals(true, (oTestObject.recebendo_tick.booleanValue() == true));
		assertEquals(true, (oTestObject.recebendo_tick.booleanValue() != false));
		oTestObject.handleEvent("receberTickEvent", tick3);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		
	}
	
}