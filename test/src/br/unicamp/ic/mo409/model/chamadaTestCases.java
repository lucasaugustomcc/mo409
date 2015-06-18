package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class chamadaTestCases{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(chamadaTestCases.class);
	}
	
	@Test
	public void test1()
	{
		Chamada oTestObject = new Chamada();
		Integer idTurma1 = 2;
		Integer raProfessor1 = 745;
		Integer dataAula1 = -125;
		Integer horaInicio1 = -28;
		Integer horaFim3 = -13;
		Integer listaPresencas5 = 600;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("abrirChamadaEvent", idTurma1, raProfessor1, dataAula1, horaInicio1);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		oTestObject.handleEvent("encerrarChamadaEvent", horaFim3);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		oTestObject.handleEvent("calcularPresencaEvent", listaPresencas5);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		
	}
	
	@Test
	public void test2()
	{
		Chamada oTestObject = new Chamada();
		Integer idTurma1 = 318;
		Integer raProfessor1 = 44;
		Integer dataAula1 = 285;
		Integer horaInicio1 = -379;
		Integer horaFim3 = -194;
		Integer listaPresencas5 = 78;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("abrirChamadaEvent", idTurma1, raProfessor1, dataAula1, horaInicio1);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		oTestObject.handleEvent("encerrarChamadaEvent", horaFim3);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		oTestObject.handleEvent("calcularPresencaEvent", listaPresencas5);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		
	}
	
	@Test
	public void test3()
	{
		Chamada oTestObject = new Chamada();
		Integer idTurma1 = 227;
		Integer raProfessor1 = 29;
		Integer dataAula1 = -149;
		Integer horaInicio1 = 167;
		Integer horaFim3 = -691;
		Integer listaPresencas5 = 492;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("abrirChamadaEvent", idTurma1, raProfessor1, dataAula1, horaInicio1);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		oTestObject.handleEvent("encerrarChamadaEvent", horaFim3);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		oTestObject.handleEvent("calcularPresencaEvent", listaPresencas5);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		
	}
	
}