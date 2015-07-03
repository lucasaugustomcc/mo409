package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class chamada0703TestCasesMD0{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(chamada0703TestCasesMD0.class);
	}
	
	@Test
	public void test1()
	{
		Chamada oTestObject = new Chamada();
		Integer duracao2 = -811;
		Integer porcentagem2 = 64;
		Integer latitude3 = 17;
		Integer longitude3 = 43;
		Integer idTurma4 = -1;
		Integer raProfessor4 = -47;
		Integer dataAula4 = 733;
		Integer horaInicio4 = -237;
		Integer horaFim6 = -484;
		Integer listaPresencas8 = -915;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("visualizarParametrosEvent");
		assertEquals(true, (oTestObject.state == ChamadaState.visualizando_parametros));
		oTestObject.handleEvent("atribuirParametrosEvent", duracao2, porcentagem2);
		assertEquals(true, (oTestObject.state == ChamadaState.atribuindo_parametros));
		oTestObject.handleEvent("atribuirLocalizacaoProfessorEvent", latitude3, longitude3);
		assertEquals(true, (oTestObject.state == ChamadaState.atribuindo_localizacao));
		oTestObject.handleEvent("abrirChamadaEvent", idTurma4, raProfessor4, dataAula4, horaInicio4);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		oTestObject.handleEvent("encerrarChamadaEvent", horaFim6);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		oTestObject.handleEvent("calcularPresencaEvent", listaPresencas8);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		
	}
	
	@Test
	public void test2()
	{
		Chamada oTestObject = new Chamada();
		Integer latitude2 = 212;
		Integer longitude2 = 894;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("visualizarParametrosEvent");
		assertEquals(true, (oTestObject.state == ChamadaState.visualizando_parametros));
		oTestObject.handleEvent("atribuirLocalizacaoProfessorEvent", latitude2, longitude2);
		assertEquals(true, (oTestObject.state == ChamadaState.atribuindo_localizacao));
		
	}
	
	@Test
	public void test3()
	{
		Chamada oTestObject = new Chamada();
		Integer latitude1 = -41;
		Integer longitude1 = 200;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("atribuirLocalizacaoProfessorEvent", latitude1, longitude1);
		assertEquals(true, (oTestObject.state == ChamadaState.atribuindo_localizacao));
		
	}
	
}