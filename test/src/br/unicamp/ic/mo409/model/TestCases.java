package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class TestCases{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(TestCases.class);
	}
	
	@Test
	public void test1()
	{
		Presenca oTestObject = new Presenca();
		Integer idTurma1 = 165;
		Integer raProfessor1 = -311;
		Integer dataAula1 = 69;
		Integer horaInicio1 = 240;
		Integer idTurma3 = 584;
		Integer raProfessor3 = -890;
		Integer dataAula3 = 57;
		Integer horaInicio3 = -238;
		Integer idTurma5 = 153;
		Integer raAluno5 = 319;
		Integer dataAula5 = -493;
		Integer horaInicio5 = 27;
		Integer numTicks5 = -186;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent", idTurma1, raProfessor1, dataAula1, horaInicio1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("checkOutPresencaEvent", idTurma3, raProfessor3, dataAula3, horaInicio3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		oTestObject.handleEvent("calcularPresencaEvent", idTurma5, raAluno5, dataAula5, horaInicio5, numTicks5);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test2()
	{
		Presenca oTestObject = new Presenca();
		Integer idTurma1 = 47;
		Integer raProfessor1 = -308;
		Integer dataAula1 = -332;
		Integer horaInicio1 = 322;
		Integer idTurma3 = -892;
		Integer raProfessor3 = -65;
		Integer dataAula3 = 244;
		Integer horaInicio3 = 13;
		Integer idTurma5 = -542;
		Integer raAluno5 = 401;
		Integer dataAula5 = -232;
		Integer horaInicio5 = -467;
		Integer numTicks5 = 87;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent", idTurma1, raProfessor1, dataAula1, horaInicio1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("checkOutPresencaEvent", idTurma3, raProfessor3, dataAula3, horaInicio3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		oTestObject.handleEvent("calcularPresencaEvent", idTurma5, raAluno5, dataAula5, horaInicio5, numTicks5);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test3()
	{
		Presenca oTestObject = new Presenca();
		Integer idTurma1 = 70;
		Integer raProfessor1 = -209;
		Integer dataAula1 = -121;
		Integer horaInicio1 = -253;
		Integer idTurma3 = 4;
		Integer raProfessor3 = 160;
		Integer dataAula3 = 98;
		Integer horaInicio3 = -35;
		Integer idTurma5 = 470;
		Integer raAluno5 = 351;
		Integer dataAula5 = 248;
		Integer horaInicio5 = 895;
		Integer numTicks5 = 216;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent", idTurma1, raProfessor1, dataAula1, horaInicio1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("checkOutPresencaEvent", idTurma3, raProfessor3, dataAula3, horaInicio3);
		assertEquals(true, (oTestObject.state == PresencaState.fora_de_aula));
		oTestObject.handleEvent("calcularPresencaEvent", idTurma5, raAluno5, dataAula5, horaInicio5, numTicks5);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test4()
	{
		Presenca oTestObject = new Presenca();
		Integer idTurma1 = -40;
		Integer raProfessor1 = 100;
		Integer dataAula1 = -148;
		Integer horaInicio1 = -871;
		Integer idTurma3 = 105;
		Integer raAluno3 = -423;
		Integer dataAula3 = 82;
		Integer horaInicio3 = -359;
		Integer numTicks3 = 130;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent", idTurma1, raProfessor1, dataAula1, horaInicio1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("calcularPresencaEvent", idTurma3, raAluno3, dataAula3, horaInicio3, numTicks3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test5()
	{
		Presenca oTestObject = new Presenca();
		Integer idTurma1 = 190;
		Integer raProfessor1 = 20;
		Integer dataAula1 = -110;
		Integer horaInicio1 = 134;
		Integer idTurma3 = -411;
		Integer raAluno3 = -25;
		Integer dataAula3 = -380;
		Integer horaInicio3 = 735;
		Integer numTicks3 = 602;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent", idTurma1, raProfessor1, dataAula1, horaInicio1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("calcularPresencaEvent", idTurma3, raAluno3, dataAula3, horaInicio3, numTicks3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test6()
	{
		Presenca oTestObject = new Presenca();
		Integer idTurma1 = 660;
		Integer raProfessor1 = -79;
		Integer dataAula1 = 104;
		Integer horaInicio1 = 201;
		Integer idTurma3 = -440;
		Integer raAluno3 = 283;
		Integer dataAula3 = 238;
		Integer horaInicio3 = 203;
		Integer numTicks3 = -32;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent", idTurma1, raProfessor1, dataAula1, horaInicio1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("calcularPresencaEvent", idTurma3, raAluno3, dataAula3, horaInicio3, numTicks3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test7()
	{
		Presenca oTestObject = new Presenca();
		Integer idTurma1 = 442;
		Integer raAluno1 = -642;
		Integer dataAula1 = -52;
		Integer horaInicio1 = -368;
		Integer numTicks1 = 25;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("calcularPresencaEvent", idTurma1, raAluno1, dataAula1, horaInicio1, numTicks1);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test8()
	{
		Presenca oTestObject = new Presenca();
		Integer idTurma1 = -98;
		Integer raAluno1 = 16;
		Integer dataAula1 = 792;
		Integer horaInicio1 = -392;
		Integer numTicks1 = -118;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("calcularPresencaEvent", idTurma1, raAluno1, dataAula1, horaInicio1, numTicks1);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test9()
	{
		Presenca oTestObject = new Presenca();
		Integer idTurma1 = 877;
		Integer raAluno1 = -368;
		Integer dataAula1 = -89;
		Integer horaInicio1 = -79;
		Integer numTicks1 = -762;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("calcularPresencaEvent", idTurma1, raAluno1, dataAula1, horaInicio1, numTicks1);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
}