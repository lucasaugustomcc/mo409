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
		Integer idTurma1 = -90;
		Integer raProfessor1 = 357;
		Integer dataAula1 = -722;
		Integer horaInicio1 = -320;
		Integer horaFim3 = 275;
		Integer listaPresencas5 = 832;
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
		Integer idTurma1 = 16;
		Integer raProfessor1 = 257;
		Integer dataAula1 = 477;
		Integer horaInicio1 = -35;
		Integer horaFim3 = 10;
		Integer listaPresencas5 = 695;
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
		Integer idTurma1 = -271;
		Integer raProfessor1 = 185;
		Integer dataAula1 = 567;
		Integer horaInicio1 = -302;
		Integer horaFim3 = 40;
		Integer listaPresencas5 = 97;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("abrirChamadaEvent", idTurma1, raProfessor1, dataAula1, horaInicio1);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		oTestObject.handleEvent("encerrarChamadaEvent", horaFim3);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		oTestObject.handleEvent("calcularPresencaEvent", listaPresencas5);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		
	}
	
}