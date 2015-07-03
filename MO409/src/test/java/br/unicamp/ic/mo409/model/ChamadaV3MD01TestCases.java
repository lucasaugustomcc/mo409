package br.unicamp.ic.mo409.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import static org.junit.Assert.*;

// JUnit 4.3
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration (locations = {"file:src/test/resources/applicationContext.xml"}) 
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false) 
@Transactional 
public class ChamadaV3MD01TestCases{
	
	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(ChamadaV3MD01TestCases.class);
	}
	
	@Test
	public void test1()
	{
		Chamada oTestObject = new Chamada(entityManager);
		Integer duracao2 = -506;
		Integer porcentagem2 = 927;
		Integer latitude3 = 488;
		Integer longitude3 = -212;
		Integer idTurma4 = 2;
		Integer raProfessor4 = -103;
		Integer dataAula4 = 353;
		Integer horaInicio4 = 249;
		Integer horaFim6 = -159;
		Integer listaPresencas8 = -336;
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
		Chamada oTestObject = new Chamada(entityManager);
		Integer duracao2 = -279;
		Integer porcentagem2 = 46;
		Integer latitude3 = 728;
		Integer longitude3 = -431;
		Integer idTurma4 = -21;
		Integer raProfessor4 = -12;
		Integer dataAula4 = -294;
		Integer horaInicio4 = -171;
		Integer horaFim6 = -226;
		Integer listaPresencas8 = 39;
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
	public void test3()
	{
		Chamada oTestObject = new Chamada(entityManager);
		Integer duracao2 = -404;
		Integer porcentagem2 = 185;
		Integer latitude3 = 151;
		Integer longitude3 = 13;
		Integer idTurma4 = -934;
		Integer raProfessor4 = -784;
		Integer dataAula4 = -26;
		Integer horaInicio4 = -383;
		Integer horaFim6 = 43;
		Integer listaPresencas8 = -448;
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
	public void test4()
	{
		Chamada oTestObject = new Chamada(entityManager);
		Integer latitude2 = 226;
		Integer longitude2 = -57;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("visualizarParametrosEvent");
		assertEquals(true, (oTestObject.state == ChamadaState.visualizando_parametros));
		oTestObject.handleEvent("atribuirLocalizacaoProfessorEvent", latitude2, longitude2);
		assertEquals(true, (oTestObject.state == ChamadaState.atribuindo_localizacao));
		
	}
	
	@Test
	public void test5()
	{
		Chamada oTestObject = new Chamada(entityManager);
		Integer latitude2 = 857;
		Integer longitude2 = 212;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("visualizarParametrosEvent");
		assertEquals(true, (oTestObject.state == ChamadaState.visualizando_parametros));
		oTestObject.handleEvent("atribuirLocalizacaoProfessorEvent", latitude2, longitude2);
		assertEquals(true, (oTestObject.state == ChamadaState.atribuindo_localizacao));
		
	}
	
	@Test
	public void test6()
	{
		Chamada oTestObject = new Chamada(entityManager);
		Integer latitude2 = 212;
		Integer longitude2 = -688;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("visualizarParametrosEvent");
		assertEquals(true, (oTestObject.state == ChamadaState.visualizando_parametros));
		oTestObject.handleEvent("atribuirLocalizacaoProfessorEvent", latitude2, longitude2);
		assertEquals(true, (oTestObject.state == ChamadaState.atribuindo_localizacao));
		
	}
	
	@Test
	public void test7()
	{
		Chamada oTestObject = new Chamada(entityManager);
		Integer latitude1 = 388;
		Integer longitude1 = -20;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("atribuirLocalizacaoProfessorEvent", latitude1, longitude1);
		assertEquals(true, (oTestObject.state == ChamadaState.atribuindo_localizacao));
		
	}
	
	@Test
	public void test8()
	{
		Chamada oTestObject = new Chamada(entityManager);
		Integer latitude1 = 68;
		Integer longitude1 = 817;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("atribuirLocalizacaoProfessorEvent", latitude1, longitude1);
		assertEquals(true, (oTestObject.state == ChamadaState.atribuindo_localizacao));
		
	}
	
	@Test
	public void test9()
	{
		Chamada oTestObject = new Chamada(entityManager);
		Integer latitude1 = -258;
		Integer longitude1 = -659;
		assertEquals(true, (oTestObject.state == ChamadaState.nao_aberta));
		oTestObject.handleEvent("atribuirLocalizacaoProfessorEvent", latitude1, longitude1);
		assertEquals(true, (oTestObject.state == ChamadaState.atribuindo_localizacao));
		
	}
	
}
