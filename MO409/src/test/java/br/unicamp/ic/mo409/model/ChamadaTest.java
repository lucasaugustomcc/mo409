package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class ChamadaTest{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(ChamadaTest.class);
	}
	
	@Test
	public void test1()
	{
		Chamada oTestObject = new Chamada();
		Integer idTurma1 = -180;
		Integer raProfessor1 = -36;
		Integer dataAula1 = -378;
		Integer horaInicio1 = -155;
		Integer horaFim3 = -14;
		Integer listaPresencas5 = -199;
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
		Integer idTurma1 = -373;
		Integer raProfessor1 = 4;
		Integer dataAula1 = -577;
		Integer horaInicio1 = 33;
		Integer horaFim3 = -247;
		Integer listaPresencas5 = 27;
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
		Integer idTurma1 = 161;
		Integer raProfessor1 = 120;
		Integer dataAula1 = 434;
		Integer horaInicio1 = 107;
		Integer horaFim3 = 817;
		Integer listaPresencas5 = -39;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("abrirChamadaEvent", idTurma1, raProfessor1, dataAula1, horaInicio1);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		oTestObject.handleEvent("encerrarChamadaEvent", horaFim3);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		oTestObject.handleEvent("calcularPresencaEvent", listaPresencas5);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		
	}
	
}
