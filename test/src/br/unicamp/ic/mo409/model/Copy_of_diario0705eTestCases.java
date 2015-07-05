package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class Copy_of_diario0705eTestCases{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(Copy_of_diario0705eTestCases.class);
	}
	
	@Test
	public void test1()
	{
		Diario oTestObject = new Diario();
		Integer idTurma1 = 250;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaTurmaEvent", idTurma1);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test2()
	{
		Diario oTestObject = new Diario();
		Integer idTurma1 = 86;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaTurmaEvent", idTurma1);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test3()
	{
		Diario oTestObject = new Diario();
		Integer idTurma1 = 104;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaTurmaEvent", idTurma1);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test4()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas1 = -127;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("encerrarDiarioEvent", listaPresencas1);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		
	}
	
	@Test
	public void test5()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas1 = -105;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("encerrarDiarioEvent", listaPresencas1);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		
	}
	
	@Test
	public void test6()
	{
		Diario oTestObject = new Diario();
		Integer listaPresencas1 = -236;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("encerrarDiarioEvent", listaPresencas1);
		assertEquals(true, (oTestObject.state == DiarioState.encerrado));
		
	}
	
	@Test
	public void test7()
	{
		Diario oTestObject = new Diario();
		Integer idAluno1 = 231;
		Integer idTurma1 = 160;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaAlunoEvent", idAluno1, idTurma1);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test8()
	{
		Diario oTestObject = new Diario();
		Integer idAluno1 = 12;
		Integer idTurma1 = -113;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaAlunoEvent", idAluno1, idTurma1);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
	@Test
	public void test9()
	{
		Diario oTestObject = new Diario();
		Integer idAluno1 = 216;
		Integer idTurma1 = 555;
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		oTestObject.handleEvent("consultaFrequenciaAlunoEvent", idAluno1, idTurma1);
		assertEquals(true, (oTestObject.state == DiarioState.aberto));
		
	}
	
}