package br.unicamp.ic.mo409.model;

import org.junit.*;

import static org.junit.Assert.*;

// JUnit 4.3
public class DiarioTestCases{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(DiarioTestCases.class);
	}
	
	@Test
	public void test1()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas1 = 181;
		Integer listaPresencas2 = 45;
		assertEquals(true, (oTestObject.state == DiarioState.vazio));
		oTestObject.handleEvent("preencherDiarioEvent", listaPresencas1);
		assertEquals(true, (oTestObject.state == DiarioState.sendo_preenchido));
		oTestObject.handleEvent("getFrequenciaAlunoEvent", listaPresencas2);
		assertEquals(true, (oTestObject.state == DiarioState.nao_vazio));
		
	}
	
	@Test
	public void test2()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas1 = -137;
		Integer listaPresencas2 = -4;
		assertEquals(true, (oTestObject.state == DiarioState.vazio));
		oTestObject.handleEvent("preencherDiarioEvent", listaPresencas1);
		assertEquals(true, (oTestObject.state == DiarioState.sendo_preenchido));
		oTestObject.handleEvent("getFrequenciaAlunoEvent", listaPresencas2);
		assertEquals(true, (oTestObject.state == DiarioState.nao_vazio));
		
	}
	
	@Test
	public void test3()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas1 = -57;
		Integer listaPresencas2 = -500;
		assertEquals(true, (oTestObject.state == DiarioState.vazio));
		oTestObject.handleEvent("preencherDiarioEvent", listaPresencas1);
		assertEquals(true, (oTestObject.state == DiarioState.sendo_preenchido));
		oTestObject.handleEvent("getFrequenciaAlunoEvent", listaPresencas2);
		assertEquals(true, (oTestObject.state == DiarioState.nao_vazio));
		
	}
	
	@Test
	public void test4()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas1 = 124;
		Integer idTurma2 = -210;
		assertEquals(true, (oTestObject.state == DiarioState.vazio));
		oTestObject.handleEvent("preencherDiarioEvent", listaPresencas1);
		assertEquals(true, (oTestObject.state == DiarioState.sendo_preenchido));
		oTestObject.handleEvent("getFrequenciaTurmaEvent", idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.nao_vazio));
		
	}
	
	@Test
	public void test5()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas1 = 54;
		Integer idTurma2 = 581;
		assertEquals(true, (oTestObject.state == DiarioState.vazio));
		oTestObject.handleEvent("preencherDiarioEvent", listaPresencas1);
		assertEquals(true, (oTestObject.state == DiarioState.sendo_preenchido));
		oTestObject.handleEvent("getFrequenciaTurmaEvent", idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.nao_vazio));
		
	}
	
	@Test
	public void test6()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas1 = 29;
		Integer idTurma2 = -658;
		assertEquals(true, (oTestObject.state == DiarioState.vazio));
		oTestObject.handleEvent("preencherDiarioEvent", listaPresencas1);
		assertEquals(true, (oTestObject.state == DiarioState.sendo_preenchido));
		oTestObject.handleEvent("getFrequenciaTurmaEvent", idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.nao_vazio));
		
	}
	
	@Test
	public void test7()
	{
		Diario oTestObject = new Diario();
		assertEquals(true, (oTestObject.state == DiarioState.frequencia_turma));
		
	}
	
}