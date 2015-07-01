package br.unicamp.ic.mo409.dao;

import java.sql.Date;

import static org.junit.Assert.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import br.unicamp.ic.mo409.model.Chamada;
import br.unicamp.ic.mo409.model.Professor;
import br.unicamp.ic.mo409.model.Turma;
import br.unicamp.ic.mo409.testes.builders.ChamadaBuilder;
import br.unicamp.ic.mo409.testes.builders.ProfessorBuilder;
import br.unicamp.ic.mo409.testes.builders.TurmaBuilder;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration (locations = {"file:src/test/resources/applicationContext.xml"}) 
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true) 
@Transactional 
public class ChamadaDAOTest {
	
	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	@Autowired
	protected ChamadaDAO chamadaDAO;
	
	private Chamada chamada;
	
	private List<Chamada> chamadas;
	
	private Professor prof;

	@Autowired
	private TurmaDAO turmaDAO;
	
	@Before
	public void setupData() 
	{		 
		Turma turma = new TurmaBuilder().build();
		prof = new ProfessorBuilder()
			.withRaProfessor(121222)
			.build();
		chamada = new ChamadaBuilder()
			.withDataChamada(new Date(115,05,20))
			.withProfessor(prof)
			.withTurma(turma)
		.build();
		//entityManager.merge(prof);
		//entityManager.merge(chamada);
	}

	@Test
	public void test2() 
	{
		
//		assertThat(turmaDAO.listarTurmasProfessor(prof.getRaProfessor()),
//				CoreMatchers.is(List.class));
	}

	@After
	public void tearDown() {
		entityManager.clear();
	}
}
