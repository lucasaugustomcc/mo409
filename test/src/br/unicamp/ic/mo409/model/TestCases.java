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
		Integer idTurma1 = 412;
		Integer raAluno1 = 133;
		Integer dataAula1 = 116;
		Integer horaInicio1 = 469;
		Integer numTicks1 = -312;
		Integer raAluno3 = -7;
		assertEquals(true, (oTestObject.state == PresencaState.calculando));
		oTestObject.handleEvent("calcularPresencaEvent", idTurma1, raAluno1, dataAula1, horaInicio1, numTicks1);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		oTestObject.handleEvent("encerrarPresencaEvent", raAluno3);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test2()
	{
		Presenca oTestObject = new Presenca();
		Integer idTurma1 = -560;
		Integer raAluno1 = -124;
		Integer dataAula1 = -29;
		Integer horaInicio1 = -89;
		Integer numTicks1 = 226;
		Integer raAluno3 = -128;
		assertEquals(true, (oTestObject.state == PresencaState.calculando));
		oTestObject.handleEvent("calcularPresencaEvent", idTurma1, raAluno1, dataAula1, horaInicio1, numTicks1);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		oTestObject.handleEvent("encerrarPresencaEvent", raAluno3);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
	@Test
	public void test3()
	{
		Presenca oTestObject = new Presenca();
		Integer idTurma1 = -401;
		Integer raAluno1 = 45;
		Integer dataAula1 = -281;
		Integer horaInicio1 = 804;
		Integer numTicks1 = 208;
		Integer raAluno3 = 58;
		assertEquals(true, (oTestObject.state == PresencaState.calculando));
		oTestObject.handleEvent("calcularPresencaEvent", idTurma1, raAluno1, dataAula1, horaInicio1, numTicks1);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		oTestObject.handleEvent("encerrarPresencaEvent", raAluno3);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
}