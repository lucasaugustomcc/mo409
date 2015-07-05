package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class Copy_of_diario0705bTestCasesMD1{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(Copy_of_diario0705bTestCasesMD1.class);
	}
	
	@Test
	public void test1()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas1 = -19;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("encerrarDiarioEvent", listaPresencas1);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		
	}
	
	@Test
	public void test2()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas1 = -315;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("encerrarDiarioEvent", listaPresencas1);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		
	}
	
	@Test
	public void test3()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas1 = 223;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("encerrarDiarioEvent", listaPresencas1);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		
	}
	
	@Test
	public void test4()
	{
		Diario oTestObject = new Diario();
		Integer idAluno1 = -466;
		Integer idTurma1 = 139;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaAlunoEvent", idAluno1, idTurma1);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test5()
	{
		Diario oTestObject = new Diario();
		Integer idAluno1 = -200;
		Integer idTurma1 = -88;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaAlunoEvent", idAluno1, idTurma1);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test6()
	{
		Diario oTestObject = new Diario();
		Integer idAluno1 = 0;
		Integer idTurma1 = -679;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaAlunoEvent", idAluno1, idTurma1);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test7()
	{
		Diario oTestObject = new Diario();
		Integer idTurma1 = -366;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaTurmaEvent", idTurma1);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test8()
	{
		Diario oTestObject = new Diario();
		Integer idTurma1 = -89;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaTurmaEvent", idTurma1);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test9()
	{
		Diario oTestObject = new Diario();
		Integer idTurma1 = -373;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaTurmaEvent", idTurma1);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
}