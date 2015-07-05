package br.unicamp.ic.mo409.model;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import br.unicamp.ic.mo409.testes.builders.ChamadaBuilder;
import br.unicamp.ic.mo409.testes.builders.PresencaBuilder;

// JUnit 4.3
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class PresencaJUnitTest
{
	public static junit.framework.Test suite()
	{
		return new junit.framework.JUnit4TestAdapter(PresencaJUnitTest.class);
	}

	@Test(expected = IllegalStateException.class)
	public void testChamadaNaoAberta()
	{
		Chamada chamada = new ChamadaBuilder()
			.withParametro(new Parametro(50,50))
			.withChamadaState(ChamadaState.nao_aberta)
			.build();
		Presenca presenca = new PresencaBuilder()
			.withChamada(chamada)
			.build();
		presenca.checkInPresenca();		
	}
	
	@Test()
	public void testChamadaAberta()
	{
		Chamada chamada = new ChamadaBuilder()
			.withParametro(new Parametro(50,50))
			.withChamadaState(ChamadaState.aberta)
			.build();
		Presenca presenca = new PresencaBuilder()
			.withChamada(chamada)
			.build();
		presenca.checkInPresenca();
		presenca.receberTick(-1, -1);
		presenca.checkOutPresenca();
	}
	
	@Test(expected = IllegalStateException.class)
	public void testChamadaNaoEncerrada()
	{
		Chamada chamada = new ChamadaBuilder()
			.withParametro(new Parametro(50,50))
			.withChamadaState(ChamadaState.aberta)
			.build();
		Presenca presenca = new PresencaBuilder()
			.withChamada(chamada)
			.build();
		presenca.calcularPresenca(50);
		presenca.visualizarPresenca();
	}
	
	@Test()
	public void testChamadaEncerrada()
	{
		Chamada chamada = new ChamadaBuilder()
			.withChamadaState(ChamadaState.encerrada)
			.build();
		Presenca presenca = new PresencaBuilder()
			.withChamada(chamada)
			.build();
		presenca.calcularPresenca(50);
		presenca.visualizarPresenca();
	}	
	
	@Test()
	public void testReceberTickContagem()
	{		
		Presenca presenca = new PresencaBuilder()			
			.withPresencaState(PresencaState.em_aula)
			.build();
		assertEquals(true, (presenca.getNumTicks() == 0));
		presenca.receberTick(50, 50);
		assertEquals(true, (presenca.getNumTicks() == 1));
		presenca.receberTick(50, 50);
		assertEquals(true, (presenca.getNumTicks() == 2));
		presenca.receberTick(50, 50);
		assertEquals(true, (presenca.getNumTicks() == 3));
	}	
	
	@Test()
	public void testCalcularPresenca()
	{		
		Chamada chamada = new ChamadaBuilder()
			.withChamadaState(ChamadaState.encerrada)
			.build();
		Presenca presenca = new PresencaBuilder()			
			.withChamada(chamada)
			.build();
		
		presenca.setState(PresencaState.fora_de_aula);
		presenca.setNumTicks(12);
		presenca.calcularPresenca(12);
		assertEquals(true, (presenca.visualizarPresenca() == PresencaState.presente));
		
		presenca.setState(PresencaState.fora_de_aula);
		presenca.setNumTicks(12);
		presenca.calcularPresenca(10);
		assertEquals(true, (presenca.visualizarPresenca() == PresencaState.presente));
		
		presenca.setState(PresencaState.fora_de_aula);
		presenca.setNumTicks(10);
		presenca.calcularPresenca(12);
		assertEquals(true, (presenca.visualizarPresenca() == PresencaState.ausente));
	}	
	
	@Test()
	public void testFormatoValidoLocalizacao()
	{	    
		Presenca presenca = new PresencaBuilder()			
			.withPresencaState(PresencaState.em_aula)
			.build();
		presenca.receberTick(90, 180);
		presenca.receberTick(0, 0);
		presenca.receberTick(-90, -180);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFormatoLatitudeInvalidoLocalizacao()
	{	    
		Presenca presenca = new PresencaBuilder()			
			.withPresencaState(PresencaState.em_aula)
			.build();
		presenca.receberTick(-91, 180);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFormatoLongitudeInvalidoLocalizacao()
	{	    
		Presenca presenca = new PresencaBuilder()			
			.withPresencaState(PresencaState.em_aula)
			.build();
		presenca.receberTick(-90, 181);
	}
	
	@Test()
	public void testReceberTick()
	{	    
		Chamada chamada = new ChamadaBuilder()
			.withLatitude(-22.8137f)
			.withLongitude(-47.064f)
			.withChamadaState(ChamadaState.aberta)
			.build();
		Presenca presenca = new PresencaBuilder()
			.withChamada(chamada)
			.withPresencaState(PresencaState.em_aula)
			.build();
		
		assertEquals(true, (presenca.receberTick(-22.8135f,-47.064f).getDistancia() < 50));		
		assertEquals(true, (presenca.receberTick(-22.8131f,-47.064f).getDistancia() > 50));
	}
	
	@Test()
	/**
	 * referência: http://boulter.com/gps/distance/
	 */
	public void testCalcularDistancia()
	{
		Chamada chamada = new ChamadaBuilder()
			.withLatitude(-22.8137f)
			.withLongitude(-47.064f)
			.build();
		Presenca presenca = new PresencaBuilder()
			.withChamada(chamada)
			.build();
		// LIS: -22.814768,-47.0650752
		// IC3: -22.8137, -47.064
		// IC3,5: -22.8135, -47.064
		assertEquals(true, (presenca.calcularDistancia(-22.8137f,-47.064f) == 0));
		assertEquals(true, (presenca.calcularDistancia(-22.8135f,-47.064f) < 50));
		assertEquals(true, (presenca.calcularDistancia(-22.8131f,-47.064f) > 50));
	}
}
