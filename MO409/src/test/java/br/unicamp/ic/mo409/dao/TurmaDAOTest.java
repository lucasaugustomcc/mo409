package br.unicamp.ic.mo409.dao;

import static org.junit.Assert.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import br.unicamp.ic.mo409.dao.TurmaDAOTest.InfrastructureContextConfiguration;
import br.unicamp.ic.mo409.model.Professor;
import br.unicamp.ic.mo409.model.Turma;
import br.unicamp.ic.mo409.testes.builders.ProfessorBuilder;
import br.unicamp.ic.mo409.testes.builders.TurmaBuilder;

@WebAppConfiguration
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration (locations = {"file:src/test/resources/applicationContext.xml"}) 
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class TurmaDAOTest {

	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager em;

	@Configuration
	public class InfrastructureContextConfiguration {
		@Autowired
		private DataSource dataSource;

		// some more configurations..
		@Bean
		public DataSource dataSource() {
			EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
			builder.setType(EmbeddedDatabaseType.H2);
			return builder.build();
		}
	}

	@Autowired
	protected TurmaDAO turmaDAO;

	private Turma turma1, turma2;

	private Professor prof1, prof2;

	private List<Turma> Turmas;

	@Before
	public void setupData() {
		prof1 = new ProfessorBuilder().build();

		prof2 = new ProfessorBuilder().withNome("Claudia Bauzer Medeiros")
				.withRaProfessor(2).withIdUsuario(2).build();

		turma1 = new TurmaBuilder()
				.withDisciplina("Engenharia de Software I", "MO409")
				.withProfessor(prof1).withAluno(10798, "Daniela Marques")
				.withAluno(23060, "Amaury Bosso André").build();

		turma2 = new TurmaBuilder()
				.withDisciplina("Análise e Projeto de Sistema de Informação",
						"MC626").withProfessor(prof1).withIdTurma(2).build();

//		em.merge(prof1);
//		em.merge(prof2);
//		em.merge(turma1);
//		em.merge(turma2);
	}

	@Test
	public void test2() {
//		assertThat(turmaDAO.listarTurmasProfessor(prof1.getRaProfessor()),
//				CoreMatchers.is(List.class));
	}

	@After
	public void tearDown() {
		//em.clear();
	}
}
