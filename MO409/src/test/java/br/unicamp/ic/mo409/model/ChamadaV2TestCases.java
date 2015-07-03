package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class ChamadaV2TestCases{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(ChamadaV2TestCases.class);
	}
	
	@Test
	public void test1()
	{
		Chamada oTestObject = new Chamada();
		Integer latitude1 = -414;
		Integer longitude1 = -194;
		Integer idTurma2 = 27;
		Integer raProfessor2 = -35;
		Integer dataAula2 = 622;
		Integer horaInicio2 = -188;
		Integer horaFim4 = 13;
		Integer listaPresencas6 = -546;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("atribuirLocalizacaoProfessorEvent", latitude1, longitude1);
		assertEquals(true, (oTestObject.state == ChamadaState.atribuindo_localizacao));
		oTestObject.handleEvent("abrirChamadaEvent", idTurma2, raProfessor2, dataAula2, horaInicio2);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		oTestObject.handleEvent("encerrarChamadaEvent", horaFim4);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		oTestObject.handleEvent("calcularPresencaEvent", listaPresencas6);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		
	}
	
	@Test
	public void test2()
	{
		Chamada oTestObject = new Chamada();
		Integer latitude1 = -265;
		Integer longitude1 = -388;
		Integer idTurma2 = -125;
		Integer raProfessor2 = -145;
		Integer dataAula2 = 338;
		Integer horaInicio2 = 709;
		Integer horaFim4 = -106;
		Integer listaPresencas6 = -858;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("atribuirLocalizacaoProfessorEvent", latitude1, longitude1);
		assertEquals(true, (oTestObject.state == ChamadaState.atribuindo_localizacao));
		oTestObject.handleEvent("abrirChamadaEvent", idTurma2, raProfessor2, dataAula2, horaInicio2);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		oTestObject.handleEvent("encerrarChamadaEvent", horaFim4);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		oTestObject.handleEvent("calcularPresencaEvent", listaPresencas6);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		
	}
	
	@Test
	public void test3()
	{
		Chamada oTestObject = new Chamada();
		Integer latitude1 = 26;
		Integer longitude1 = -35;
		Integer idTurma2 = 10;
		Integer raProfessor2 = 244;
		Integer dataAula2 = 64;
		Integer horaInicio2 = -197;
		Integer horaFim4 = 28;
		Integer listaPresencas6 = -171;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("atribuirLocalizacaoProfessorEvent", latitude1, longitude1);
		assertEquals(true, (oTestObject.state == ChamadaState.atribuindo_localizacao));
		oTestObject.handleEvent("abrirChamadaEvent", idTurma2, raProfessor2, dataAula2, horaInicio2);
		assertEquals(true, (oTestObject.state == ChamadaState.aberta));
		oTestObject.handleEvent("encerrarChamadaEvent", horaFim4);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		oTestObject.handleEvent("calcularPresencaEvent", listaPresencas6);
		assertEquals(true, (oTestObject.state == ChamadaState.encerrada));
		
	}
	
	@Test
	public void test4()
	{
		Chamada oTestObject = new Chamada();
		Integer duracao1 = 707;
		Integer porcentagem1 = 119;
		Integer latitude2 = 42;
		Integer longitude2 = -670;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("visualizarParametrosEvent", duracao1, porcentagem1);
		assertEquals(true, (oTestObject.state == ChamadaState.visualizando_parametros));
		oTestObject.handleEvent("atribuirLocalizacaoProfessorEvent", latitude2, longitude2);
		assertEquals(true, (oTestObject.state == ChamadaState.atribuindo_parametros));
		
	}
	
	@Test
	public void test5()
	{
		Chamada oTestObject = new Chamada();
		Integer duracao1 = -71;
		Integer porcentagem1 = -535;
		Integer latitude2 = 18;
		Integer longitude2 = 254;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("visualizarParametrosEvent", duracao1, porcentagem1);
		assertEquals(true, (oTestObject.state == ChamadaState.visualizando_parametros));
		oTestObject.handleEvent("atribuirLocalizacaoProfessorEvent", latitude2, longitude2);
		assertEquals(true, (oTestObject.state == ChamadaState.atribuindo_parametros));
		
	}
	
	@Test
	public void test6()
	{
		Chamada oTestObject = new Chamada();
		Integer duracao1 = -355;
		Integer porcentagem1 = 55;
		Integer latitude2 = 97;
		Integer longitude2 = 477;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("visualizarParametrosEvent", duracao1, porcentagem1);
		assertEquals(true, (oTestObject.state == ChamadaState.visualizando_parametros));
		oTestObject.handleEvent("atribuirLocalizacaoProfessorEvent", latitude2, longitude2);
		assertEquals(true, (oTestObject.state == ChamadaState.atribuindo_parametros));
		
	}
	
	@Test
	public void test7()
	{
		Chamada oTestObject = new Chamada();
		Integer duracao1 = 101;
		Integer porcentagem1 = 153;
		Integer duracao2 = -81;
		Integer porcentagem2 = -26;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("visualizarParametrosEvent", duracao1, porcentagem1);
		assertEquals(true, (oTestObject.state == ChamadaState.visualizando_parametros));
		oTestObject.handleEvent("visualizarParametrosEvent", duracao2, porcentagem2);
		assertEquals(true, (oTestObject.state == ChamadaState.atribuindo_parametros));
		
	}
	
	@Test
	public void test8()
	{
		Chamada oTestObject = new Chamada();
		Integer duracao1 = -355;
		Integer porcentagem1 = -234;
		Integer duracao2 = 502;
		Integer porcentagem2 = -60;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("visualizarParametrosEvent", duracao1, porcentagem1);
		assertEquals(true, (oTestObject.state == ChamadaState.visualizando_parametros));
		oTestObject.handleEvent("visualizarParametrosEvent", duracao2, porcentagem2);
		assertEquals(true, (oTestObject.state == ChamadaState.atribuindo_parametros));
		
	}
	
	@Test
	public void test9()
	{
		Chamada oTestObject = new Chamada();
		Integer duracao1 = -384;
		Integer porcentagem1 = 19;
		Integer duracao2 = 440;
		Integer porcentagem2 = -503;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("visualizarParametrosEvent", duracao1, porcentagem1);
		assertEquals(true, (oTestObject.state == ChamadaState.visualizando_parametros));
		oTestObject.handleEvent("visualizarParametrosEvent", duracao2, porcentagem2);
		assertEquals(true, (oTestObject.state == ChamadaState.atribuindo_parametros));
		
	}
	
}