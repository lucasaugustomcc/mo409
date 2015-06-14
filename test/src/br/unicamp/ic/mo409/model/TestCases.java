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
		Integer idTurma1 = 435;
		Integer raProfessor1 = -5;
		Integer dataAula1 = 35;
		Integer horaInicio1 = 14;
		Integer idTurma3 = 141;
		Integer raProfessor3 = 651;
		Integer dataAula3 = -69;
		Integer horaInicio3 = -149;
		Integer idTurma5 = -272;
		Integer raAluno5 = -68;
		Integer dataAula5 = 150;
		Integer horaInicio5 = -577;
		Integer numTicks5 = 252;
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
		Integer idTurma1 = 0;
		Integer raProfessor1 = -389;
		Integer dataAula1 = -7;
		Integer horaInicio1 = 153;
		Integer idTurma3 = -611;
		Integer raProfessor3 = 110;
		Integer dataAula3 = 59;
		Integer horaInicio3 = 31;
		Integer idTurma5 = -28;
		Integer raAluno5 = 251;
		Integer dataAula5 = 122;
		Integer horaInicio5 = 211;
		Integer numTicks5 = -47;
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
		Integer idTurma1 = 49;
		Integer raProfessor1 = 60;
		Integer dataAula1 = -21;
		Integer horaInicio1 = 375;
		Integer idTurma3 = -801;
		Integer raProfessor3 = -70;
		Integer dataAula3 = 507;
		Integer horaInicio3 = 286;
		Integer idTurma5 = 239;
		Integer raAluno5 = 105;
		Integer dataAula5 = 24;
		Integer horaInicio5 = -353;
		Integer numTicks5 = 258;
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
		Integer idTurma1 = -419;
		Integer raProfessor1 = 40;
		Integer dataAula1 = -73;
		Integer horaInicio1 = -389;
		Integer idTurma3 = -74;
		Integer raAluno3 = 813;
		Integer dataAula3 = -588;
		Integer horaInicio3 = 761;
		Integer numTicks3 = -530;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent", idTurma1, raProfessor1, dataAula1, horaInicio1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("receberTickEvent", idTurma3, raAluno3, dataAula3, horaInicio3, numTicks3);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		
	}
	
	@Test
	public void test5()
	{
		Presenca oTestObject = new Presenca();
		Integer idTurma1 = -171;
		Integer raProfessor1 = -141;
		Integer dataAula1 = -34;
		Integer horaInicio1 = 353;
		Integer idTurma3 = -300;
		Integer raAluno3 = -180;
		Integer dataAula3 = 192;
		Integer horaInicio3 = -106;
		Integer numTicks3 = 250;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent", idTurma1, raProfessor1, dataAula1, horaInicio1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("receberTickEvent", idTurma3, raAluno3, dataAula3, horaInicio3, numTicks3);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		
	}
	
	@Test
	public void test6()
	{
		Presenca oTestObject = new Presenca();
		Integer idTurma1 = -641;
		Integer raProfessor1 = -555;
		Integer dataAula1 = 211;
		Integer horaInicio1 = 749;
		Integer idTurma3 = 311;
		Integer raAluno3 = 229;
		Integer dataAula3 = 278;
		Integer horaInicio3 = 559;
		Integer numTicks3 = -97;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent", idTurma1, raProfessor1, dataAula1, horaInicio1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("receberTickEvent", idTurma3, raAluno3, dataAula3, horaInicio3, numTicks3);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		
	}
	
	@Test
	public void test7()
	{
		Presenca oTestObject = new Presenca();
		Integer idTurma1 = -32;
		Integer raProfessor1 = -241;
		Integer dataAula1 = -73;
		Integer horaInicio1 = 398;
		Integer idTurma3 = 190;
		Integer raAluno3 = -123;
		Integer dataAula3 = 169;
		Integer horaInicio3 = 324;
		Integer numTicks3 = 14;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent", idTurma1, raProfessor1, dataAula1, horaInicio1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("calcularPresencaEvent", idTurma3, raAluno3, dataAula3, horaInicio3, numTicks3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test8()
	{
		Presenca oTestObject = new Presenca();
		Integer idTurma1 = -431;
		Integer raProfessor1 = -214;
		Integer dataAula1 = 234;
		Integer horaInicio1 = -199;
		Integer idTurma3 = 751;
		Integer raAluno3 = -135;
		Integer dataAula3 = -283;
		Integer horaInicio3 = 321;
		Integer numTicks3 = 387;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent", idTurma1, raProfessor1, dataAula1, horaInicio1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("calcularPresencaEvent", idTurma3, raAluno3, dataAula3, horaInicio3, numTicks3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test9()
	{
		Presenca oTestObject = new Presenca();
		Integer idTurma1 = -374;
		Integer raProfessor1 = 376;
		Integer dataAula1 = 167;
		Integer horaInicio1 = 1;
		Integer idTurma3 = 33;
		Integer raAluno3 = -15;
		Integer dataAula3 = 935;
		Integer horaInicio3 = -403;
		Integer numTicks3 = -389;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("checkInPresencaEvent", idTurma1, raProfessor1, dataAula1, horaInicio1);
		assertEquals(true, (oTestObject.state == PresencaState.em_aula));
		oTestObject.handleEvent("calcularPresencaEvent", idTurma3, raAluno3, dataAula3, horaInicio3, numTicks3);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test10()
	{
		Presenca oTestObject = new Presenca();
		Integer idTurma1 = -58;
		Integer raAluno1 = 0;
		Integer dataAula1 = 347;
		Integer horaInicio1 = 472;
		Integer numTicks1 = 9;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("calcularPresencaEvent", idTurma1, raAluno1, dataAula1, horaInicio1, numTicks1);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test11()
	{
		Presenca oTestObject = new Presenca();
		Integer idTurma1 = -553;
		Integer raAluno1 = -326;
		Integer dataAula1 = -527;
		Integer horaInicio1 = -22;
		Integer numTicks1 = 46;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("calcularPresencaEvent", idTurma1, raAluno1, dataAula1, horaInicio1, numTicks1);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
	@Test
	public void test12()
	{
		Presenca oTestObject = new Presenca();
		Integer idTurma1 = 169;
		Integer raAluno1 = 452;
		Integer dataAula1 = -184;
		Integer horaInicio1 = -169;
		Integer numTicks1 = 1;
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		oTestObject.handleEvent("calcularPresencaEvent", idTurma1, raAluno1, dataAula1, horaInicio1, numTicks1);
		assertEquals(true, (oTestObject.state == PresencaState.presente));
		
	}
	
}