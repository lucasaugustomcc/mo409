package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class Copy_of_diario0705hTestCasesMD1{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(Copy_of_diario0705hTestCasesMD1.class);
	}
	
	@Test
	public void test1()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas2 = 212;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("encerrarDiarioEvent", listaPresencas2);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		
	}
	
	@Test
	public void test2()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas2 = 108;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("encerrarDiarioEvent", listaPresencas2);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		
	}
	
	@Test
	public void test3()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas2 = 293;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("encerrarDiarioEvent", listaPresencas2);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		
	}
	
	@Test
	public void test4()
	{
		Diario oTestObject = new Diario();
		Integer idAluno2 = 83;
		Integer idTurma2 = 93;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaAlunoEvent", idAluno2, idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test5()
	{
		Diario oTestObject = new Diario();
		Integer idAluno2 = -228;
		Integer idTurma2 = 721;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaAlunoEvent", idAluno2, idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test6()
	{
		Diario oTestObject = new Diario();
		Integer idAluno2 = 313;
		Integer idTurma2 = 469;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaAlunoEvent", idAluno2, idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test7()
	{
		Diario oTestObject = new Diario();
		Integer idTurma2 = 38;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaTurmaEvent", idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test8()
	{
		Diario oTestObject = new Diario();
		Integer idTurma2 = 892;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaTurmaEvent", idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test9()
	{
		Diario oTestObject = new Diario();
		Integer idTurma2 = 297;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaTurmaEvent", idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
}