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
		ChamadaState state1 = ChamadaState.nao_aberta;
		ChamadaState state3 = ChamadaState.nao_aberta;
		ChamadaState state5 = ChamadaState.nao_aberta;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("abrirChamadaEvent", state1);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		oTestObject.handleEvent("encerrarChamadaEvent", state3);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		oTestObject.handleEvent("encerrarChamadaEvent", state5);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		
	}
	
	@Test
	public void test2()
	{
		Chamada oTestObject = new Chamada();
		ChamadaState state1 = ChamadaState.nao_aberta;
		ChamadaState state3 = ChamadaState.nao_aberta;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("abrirChamadaEvent", state1);
		assertEquals(true, (state1 == ChamadaState.nao_aberta));
		assertEquals(true, (oTestObject.recebendo_tick.booleanValue() == true));
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		assertEquals(true, (oTestObject.recebendo_tick.booleanValue() == true));
		assertEquals(true, (oTestObject.recebendo_tick.booleanValue() != false));
		oTestObject.handleEvent("receberTickEvent", state3);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		
	}
	
}