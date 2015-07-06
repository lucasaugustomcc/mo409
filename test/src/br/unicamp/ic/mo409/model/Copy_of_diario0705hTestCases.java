package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class Copy_of_diario0705hTestCases{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(Copy_of_diario0705hTestCases.class);
	}
	
	@Test
	public void test1()
	{
		Diario oTestObject = new Diario();
		Integer idAluno2 = 20;
		Integer idTurma2 = -258;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaAlunoEvent", idAluno2, idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test2()
	{
		Diario oTestObject = new Diario();
		Integer idAluno2 = -51;
		Integer idTurma2 = -111;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaAlunoEvent", idAluno2, idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test3()
	{
		Diario oTestObject = new Diario();
		Integer idAluno2 = -57;
		Integer idTurma2 = 275;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaAlunoEvent", idAluno2, idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test4()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas2 = 2;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("encerrarDiarioEvent", listaPresencas2);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		
	}
	
	@Test
	public void test5()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas2 = 0;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("encerrarDiarioEvent", listaPresencas2);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		
	}
	
	@Test
	public void test6()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas2 = -17;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("encerrarDiarioEvent", listaPresencas2);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		
	}
	
	@Test
	public void test7()
	{
		Diario oTestObject = new Diario();
		Integer idTurma2 = -59;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaTurmaEvent", idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test8()
	{
		Diario oTestObject = new Diario();
		Integer idTurma2 = -283;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaTurmaEvent", idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test9()
	{
		Diario oTestObject = new Diario();
		Integer idTurma2 = -313;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaTurmaEvent", idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
}