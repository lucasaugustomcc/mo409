package br.unicamp.ic.mo409.model;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import br.unicamp.ic.mo409.testes.builders.ChamadaBuilder;

// JUnit 4.3
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class ChamadaJUnitTest
{

	public static junit.framework.Test suite()
	{
		return new junit.framework.JUnit4TestAdapter(ChamadaTest.class);
	}

	@Test
	public void test()
	{
		Chamada chamada = new ChamadaBuilder()
			.build();
		assertEquals(true, (chamada.getState() == ChamadaState.nao_aberta));
	}
}
