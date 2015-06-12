package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class TestCases_AllTransitions_RandomValueSelection{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(TestCases_AllTransitions_RandomValueSelection.class);
	}
	
	@Test
	public void test1()
	{
		Chamada oTestObject = new Chamada();
		Integer disciplina1 = -320;
		ChamadaState state3 = ChamadaState.nao_aberta;
		ChamadaState state5 = ChamadaState.nao_aberta;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("abrirChamadaEvent", disciplina1);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		oTestObject.handleEvent("encerrarChamadaEvent", state3);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		oTestObject.handleEvent("calcularPresencaEvent", state5);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		
	}
	
	@Test
	public void test2()
	{
		Chamada oTestObject = new Chamada();
		Integer disciplina1 = 407;
		Integer tick3 = 788;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("abrirChamadaEvent", disciplina1);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		assertEquals(true, (oTestObject.recebendo_tick.booleanValue() == true));
		assertEquals(true, (oTestObject.recebendo_tick.booleanValue() != false));
		oTestObject.handleEvent("receberTickEvent", tick3);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		
	}
	
}