package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class Copy_of_diario0705TestCasesMD1{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(Copy_of_diario0705TestCasesMD1.class);
	}
	
	@Test
	public void test1()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas1 = 198;
		Integer idAluno2 = -337;
		Integer idTurma2 = -502;
		assertEquals(true, (oTestObject.state == DiarioState.nao_aberto));
		oTestObject.handleEvent("abrirDiarioEvent", listaPresencas1);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("getFrequenciaAlunoEvent", idAluno2, idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test2()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas1 = -189;
		Integer idAluno2 = 45;
		Integer idTurma2 = 62;
		assertEquals(true, (oTestObject.state == DiarioState.nao_aberto));
		oTestObject.handleEvent("abrirDiarioEvent", listaPresencas1);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("getFrequenciaAlunoEvent", idAluno2, idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test3()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas1 = 279;
		Integer idAluno2 = -53;
		Integer idTurma2 = 293;
		assertEquals(true, (oTestObject.state == DiarioState.nao_aberto));
		oTestObject.handleEvent("abrirDiarioEvent", listaPresencas1);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("getFrequenciaAlunoEvent", idAluno2, idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test4()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas1 = 255;
		Integer idTurma2 = -340;
		assertEquals(true, (oTestObject.state == DiarioState.nao_aberto));
		oTestObject.handleEvent("abrirDiarioEvent", listaPresencas1);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("getFrequenciaTurmaEvent", idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test5()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas1 = 68;
		Integer idTurma2 = -26;
		assertEquals(true, (oTestObject.state == DiarioState.nao_aberto));
		oTestObject.handleEvent("abrirDiarioEvent", listaPresencas1);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("getFrequenciaTurmaEvent", idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test6()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas1 = -710;
		Integer idTurma2 = 689;
		assertEquals(true, (oTestObject.state == DiarioState.nao_aberto));
		oTestObject.handleEvent("abrirDiarioEvent", listaPresencas1);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("getFrequenciaTurmaEvent", idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test7()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas1 = -482;
		Integer listaPresencas2 = 69;
		assertEquals(true, (oTestObject.state == DiarioState.nao_aberto));
		oTestObject.handleEvent("abrirDiarioEvent", listaPresencas1);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("encerrarDiarioEvent", listaPresencas2);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		
	}
	
	@Test
	public void test8()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas1 = -185;
		Integer listaPresencas2 = 110;
		assertEquals(true, (oTestObject.state == DiarioState.nao_aberto));
		oTestObject.handleEvent("abrirDiarioEvent", listaPresencas1);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("encerrarDiarioEvent", listaPresencas2);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		
	}
	
	@Test
	public void test9()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas1 = -962;
		Integer listaPresencas2 = 84;
		assertEquals(true, (oTestObject.state == DiarioState.nao_aberto));
		oTestObject.handleEvent("abrirDiarioEvent", listaPresencas1);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("encerrarDiarioEvent", listaPresencas2);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		
	}
	
}