package br.unicamp.ic.mo409.model;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.util.IllegalFormatException;

import javax.transaction.Transactional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import br.unicamp.ic.mo409.testes.builders.ChamadaBuilder;
import br.unicamp.ic.mo409.testes.builders.TurmaBuilder;

// JUnit 4.3
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class ChamadaJUnitTest
{
	public static junit.framework.Test suite()
	{
		return new junit.framework.JUnit4TestAdapter(ChamadaJUnitTest.class);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCalcularNumMinTicksParametroDuracao()
	{
		Turma turma = new TurmaBuilder()
			.withParametro(new Parametro(50,50))
			.build();
		Chamada chamada = new ChamadaBuilder()
			.withHoraFim(new Time(12,0,0))
			.withHoraInicio(new Time(10,00,0))
			.withTurma(turma)			
			.build();
		assertEquals(true, (chamada.calcularNumMinTicks() == 5));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCalcularNumMinTicksDuracaoChamada()
	{
		Turma turma = new TurmaBuilder()
			.withParametro(new Parametro(50,50))
			.build();
		Chamada chamada = new ChamadaBuilder()
			.withHoraFim(new Time(11,0,0))
			.withHoraInicio(new Time(10,30,0))
			.withTurma(turma)			
			.build();
		assertEquals(true, (chamada.calcularNumMinTicks() == 3));
	}
	
	@Test()
	public void testFormatoValidoParametros()
	{	    
		Turma turma = new TurmaBuilder()
			.withParametro(new Parametro(50,50))
			.build();
		Chamada chamada = new ChamadaBuilder()
			.withTurma(turma)	
			.withChamadaState(ChamadaState.visualizando_parametros)
			.build();
		chamada.atribuirParametros(50, 50f);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFormatoInvalidoParametros()
	{	    
		Turma turma = new TurmaBuilder()
			.withParametro(new Parametro(50,50))
			.build();
		Chamada chamada = new ChamadaBuilder()
			.withTurma(turma)	
			.withChamadaState(ChamadaState.visualizando_parametros)
			.build();
		chamada.atribuirParametros(-100, -1f);
	}
	
	@Test()
	public void testFormatoValidoLocalizacao()
	{	    
		Turma turma = new TurmaBuilder()
			.withParametro(new Parametro(50,50))
			.build();
		Chamada chamada = new ChamadaBuilder()
			.withTurma(turma)	
			.withChamadaState(ChamadaState.visualizando_parametros)
			.build();
		chamada.atribuirLocalizacao(90, 180);
		chamada.atribuirLocalizacao(-90, -180);
		chamada.atribuirLocalizacao(0, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFormatoInvalidoLocalizacao()
	{	    
		Turma turma = new TurmaBuilder()
			.withParametro(new Parametro(50,50))
			.build();
		Chamada chamada = new ChamadaBuilder()
			.withTurma(turma)	
			.withChamadaState(ChamadaState.visualizando_parametros)
			.build();
		chamada.atribuirLocalizacao(-100, -1);
	}
	
}
