package br.unicamp.ic.mo409.controller;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.sql.Time;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.GenericXmlWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import br.unicamp.ic.mo409.dao.ChamadaDAO;
import br.unicamp.ic.mo409.dao.PresencaDAO;
import br.unicamp.ic.mo409.dao.ProfessorDAO;
import br.unicamp.ic.mo409.dao.TurmaDAO;
import br.unicamp.ic.mo409.dao.UsuarioDAO;
import br.unicamp.ic.mo409.model.Aluno;
import br.unicamp.ic.mo409.model.Chamada;
import br.unicamp.ic.mo409.model.Professor;
import br.unicamp.ic.mo409.model.Turma;
import br.unicamp.ic.mo409.model.Usuario;
import br.unicamp.ic.mo409.testes.builders.AlunoBuilder;
import br.unicamp.ic.mo409.testes.builders.ChamadaBuilder;
import br.unicamp.ic.mo409.testes.builders.ProfessorBuilder;
import br.unicamp.ic.mo409.testes.builders.TurmaBuilder;
import br.unicamp.ic.mo409.testes.util.UtilTestes;

@WebAppConfiguration
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(loader = GenericXmlWebContextLoader.class, value = {
// "file:src/test/resources/spring-security.xml",
"file:src/test/resources/applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
public class AlunoControllerTest {

	private MockMvc mockMvc;

	@Mock
	private UsuarioDAO usuarioDAO;

	@Configuration
	static class AlunoControllerTestConfiguration {

		@Bean
		public TurmaDAO TurmaDAO() {
			return Mockito.mock(TurmaDAO.class);
		}

		@Bean
		public UsuarioDAO UsuarioDAO() {
			return Mockito.mock(UsuarioDAO.class);
		}

		@Bean
		public ChamadaDAO ChamadaDAO() {
			return Mockito.mock(ChamadaDAO.class);
		}

		@Bean
		public PresencaDAO PresencaDAO() {
			return Mockito.mock(PresencaDAO.class);
		}
	}

	@Mock
	private TurmaDAO turmaDAO;

	@Mock
	private ProfessorDAO professorDAO;

	@Mock
	private PresencaDAO presencaDAO;

	@Mock
	private ChamadaDAO chamadaDAO;

	@InjectMocks
	private AlunoController alunoController;

	@Autowired
	private WebApplicationContext wac;

	@SuppressWarnings("deprecation")
	@Before
	public void setup() {
		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.alunoController)
		// .addFilters(this.springSecurityFilterChain)
				.build();

		Professor prof1 = new ProfessorBuilder().build();

		Aluno aluno = new AlunoBuilder().build();

		Turma turma1 = new TurmaBuilder()
				.withDisciplina("Engenharia de Software I", "MO409")
				.withProfessor(prof1).withAluno(10798, "Daniela Marques")
				.withAluno(23060, "Amaury Bosso André").build();

		Chamada chamada1 = new ChamadaBuilder()
				.withDataChamada(new Date(2015, 06, 10))
				.withHoraInicio(new Time(10, 00, 00))
				.withHoraFim(new Time(12, 00, 00)).withProfessor(prof1)
				.withTurma(turma1).build();

		Mockito.when(this.turmaDAO.find(1)).thenReturn(turma1);
		Mockito.when(this.usuarioDAO.loadUsuarioByUsername("1")).thenReturn(
				aluno.getUsuario());
		Mockito.when(this.professorDAO.find(1)).thenReturn(prof1);
		Mockito.when(this.chamadaDAO.find(1)).thenReturn(chamada1);
		Mockito.when(this.turmaDAO.getTurmaChamadaAbertaAluno(1)).thenReturn(
				turma1);
	}

	@Test()
	public void testAlunoChamada() throws Exception {
		Usuario user = usuarioDAO.loadUsuarioByUsername("1");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);

		mockMvc.perform(get("/aluno/chamada"))
				.andExpect(status().is(200))
				.andExpect(
						content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(
						content()
								.json("{idChamada: 1, horaInicio: \"10:00\", dataChamada: 10/06/2015, "
										+ "turma: { idTurma: 1,codTurma:A, codDisciplina:MO409, nomeDisciplina:\"Engenharia de Software I\", nomeProfessor:\"Eliane Martins\" } }"));
	}

	@Test()
	public void testAlunoChamadaCheckin() throws Exception {

		Usuario user = usuarioDAO.loadUsuarioByUsername("1");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);

		this.mockMvc
				.perform(
						get("/aluno/chamada/checkin").content(
								"{\"idChamada\":1}"))
				.andExpect(status().is(200))
				.andExpect(
						content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(
						content()
								.json("{ \"idTick\": \"1\", \"dataHora\": \"20/05/2015 10:00\", "
										+ "\"chamada\": { \"idChamada\": \"1\", \"dataChamada\": \"10/06/2015\", \"horaInicio\": \"20:00\" }, "
										+ "\"turma\": { \"idTurma\": \"1\", \"codTurma\":\"A\", \"codDisciplina\":\"MO409\", \"nomeDisciplina\":\"Engenharia de Software I\", \"nomeProfessor\":\"Eliane Martins\" } }"));

	}

	@Test()
	public void testAlunoChamadaTick() throws Exception {
		Usuario user = usuarioDAO.loadUsuarioByUsername("1");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);

		this.mockMvc
				.perform(
						post("/aluno/chamada/tick").content("{\"idChamada\":1}")
								.header("content-type", "application/json"))
				.andExpect(status().is(200))
				.andExpect(
						content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(
						content()
								.json("{ \"idTick\": \"1\", \"dataHora\": \"20/05/2015 10:00\", "
										+ "\"chamada\": { \"idChamada\": \"1\", \"dataChamada\": \"10/06/2015\", \"horaInicio\": \"20:00\" }, "
										+ "\"turma\": { \"idTurma\": \"1\", \"codTurma\":\"A\", \"codDisciplina\":\"MO409\", \"nomeDisciplina\":\"Engenharia de Software I\", \"nomeProfessor\":\"Eliane Martins\" } }"));

	}

	@Test()
	public void testAlunoChamadaCheckou() throws Exception {
		Usuario user = usuarioDAO.loadUsuarioByUsername("1");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);

		this.mockMvc
				.perform(
						post("/aluno/chamada/checkout").content("{\"idChamada\":1}")
								.header("content-type", "application/json"))
				.andExpect(status().is(200))
				.andExpect(
						content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(
						content()
								.json("{ \"idTick\": \"1\", \"dataHora\": \"20/05/2015 10:00\", "
										+ "\"chamada\": { \"idChamada\": \"1\", \"dataChamada\": \"10/06/2015\", \"horaInicio\": \"20:00\" }, "
										+ "\"turma\": { \"idTurma\": \"1\", \"codTurma\":\"A\", \"codDisciplina\":\"MO409\", \"nomeDisciplina\":\"Engenharia de Software I\", \"nomeProfessor\":\"Eliane Martins\" } }"));

	}
	
}