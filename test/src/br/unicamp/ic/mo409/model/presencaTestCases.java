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
		Integer idTurma1 = 102;
		Integer raAluno1 = -11;
		Integer dataAula1 = -146;
		Integer horaInicio1 = 407;
		Integer numTicks1 = -208;
		Integer raAluno3 = -151;
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
		Integer idTurma1 = 65;
		Integer raAluno1 = -105;
		Integer dataAula1 = -14;
		Integer horaInicio1 = -71;
		Integer numTicks1 = -358;
		Integer raAluno3 = -577;
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
		Integer idTurma1 = 17;
		Integer raAluno1 = 13;
		Integer dataAula1 = -25;
		Integer horaInicio1 = 234;
		Integer numTicks1 = -28;
		Integer raAluno3 = -87;
		assertEquals(true, (oTestObject.state == PresencaState.calculando));
		oTestObject.handleEvent("calcularPresencaEvent", idTurma1, raAluno1, dataAula1, horaInicio1, numTicks1);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		oTestObject.handleEvent("encerrarPresencaEvent", raAluno3);
		assertEquals(true, (oTestObject.state == PresencaState.ausente));
		
	}
	
}