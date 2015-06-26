package br.unicamp.ic.mo409.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.GenericXmlWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import br.unicamp.ic.mo409.model.Chamada;
import br.unicamp.ic.mo409.testes.builders.ChamadaBuilder;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration (locations = {"file:src/test/resources/applicationContext.xml"}) 
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false) 
@Transactional 
public class ChamadaDAOTest {
	
	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	@Autowired
	protected ChamadaDAO chamadaDAO;
	
	private Chamada chamada;
	
	private List<Chamada> chamadas;
	
	@Before
	public void setupData() 
	{		 
		chamada = new ChamadaBuilder()
			.withDataChamada(new Date(2015,05,20))
		.build();
		entityManager.merge(chamada);
	}

	@Test
	public void test2() {
		
		
	}

	@After
	public void tearDown() {
		entityManager.clear();
	}
}
