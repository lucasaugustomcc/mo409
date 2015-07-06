package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class Copy_of_diario0705jTestCasesMD1{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(Copy_of_diario0705jTestCasesMD1.class);
	}
	
	@Test
	public void test1()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas2 = -149;
		Integer idTurma3 = -238;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("encerrarDiarioEvent", listaPresencas2);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		oTestObject.handleEvent("consultaFrequenciaTurmaEvent", idTurma3);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		
	}
	
	@Test
	public void test2()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas2 = -330;
		Integer idTurma3 = 302;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("encerrarDiarioEvent", listaPresencas2);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		oTestObject.handleEvent("consultaFrequenciaTurmaEvent", idTurma3);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		
	}
	
	@Test
	public void test3()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas2 = -9;
		Integer idTurma3 = -424;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("encerrarDiarioEvent", listaPresencas2);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		oTestObject.handleEvent("consultaFrequenciaTurmaEvent", idTurma3);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		
	}
	
	@Test
	public void test4()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas2 = -68;
		Integer idAluno3 = -165;
		Integer idTurma3 = 157;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("encerrarDiarioEvent", listaPresencas2);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		oTestObject.handleEvent("consultaFrequenciaAlunoEvent", idAluno3, idTurma3);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		
	}
	
	@Test
	public void test5()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas2 = -134;
		Integer idAluno3 = 4;
		Integer idTurma3 = 158;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("encerrarDiarioEvent", listaPresencas2);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		oTestObject.handleEvent("consultaFrequenciaAlunoEvent", idAluno3, idTurma3);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		
	}
	
	@Test
	public void test6()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas2 = -27;
		Integer idAluno3 = -589;
		Integer idTurma3 = 254;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("encerrarDiarioEvent", listaPresencas2);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		oTestObject.handleEvent("consultaFrequenciaAlunoEvent", idAluno3, idTurma3);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		
	}
	
	@Test
	public void test7()
	{
		Diario oTestObject = new Diario();
		Integer idAluno2 = 255;
		Integer idTurma2 = -42;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaAlunoEvent", idAluno2, idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test8()
	{
		Diario oTestObject = new Diario();
		Integer idAluno2 = 7;
		Integer idTurma2 = 298;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaAlunoEvent", idAluno2, idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test9()
	{
		Diario oTestObject = new Diario();
		Integer idAluno2 = -100;
		Integer idTurma2 = 30;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaAlunoEvent", idAluno2, idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test10()
	{
		Diario oTestObject = new Diario();
		Integer idTurma2 = -258;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaTurmaEvent", idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test11()
	{
		Diario oTestObject = new Diario();
		Integer idTurma2 = -38;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaTurmaEvent", idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test12()
	{
		Diario oTestObject = new Diario();
		Integer idTurma2 = 207;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaTurmaEvent", idTurma2);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
}