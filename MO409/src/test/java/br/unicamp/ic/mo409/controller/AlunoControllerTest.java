package br.unicamp.ic.mo409.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

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

import br.unicamp.ic.mo409.dao.ChamadaDAO;
import br.unicamp.ic.mo409.dao.PresencaDAO;
import br.unicamp.ic.mo409.dao.ProfessorDAO;
import br.unicamp.ic.mo409.dao.TickDAO;
import br.unicamp.ic.mo409.dao.TurmaDAO;
import br.unicamp.ic.mo409.dao.UsuarioDAO;
import br.unicamp.ic.mo409.model.Aluno;
import br.unicamp.ic.mo409.model.Chamada;
import br.unicamp.ic.mo409.model.ChamadaState;
import br.unicamp.ic.mo409.model.Presenca;
import br.unicamp.ic.mo409.model.PresencaState;
import br.unicamp.ic.mo409.model.Professor;
import br.unicamp.ic.mo409.model.Turma;
import br.unicamp.ic.mo409.model.Usuario;
import br.unicamp.ic.mo409.testes.builders.AlunoBuilder;
import br.unicamp.ic.mo409.testes.builders.ChamadaBuilder;
import br.unicamp.ic.mo409.testes.builders.PresencaBuilder;
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
public class AlunoControllerTest
{

	private MockMvc mockMvc;

	@Mock
	private UsuarioDAO usuarioDAO;

	@Configuration
	static class AlunoControllerTestConfiguration
	{

		@Bean
		public TurmaDAO TurmaDAO()
		{
			return Mockito.mock(TurmaDAO.class);
		}

		@Bean
		public UsuarioDAO UsuarioDAO()
		{
			return Mockito.mock(UsuarioDAO.class);
		}

		@Bean
		public ChamadaDAO ChamadaDAO()
		{
			return Mockito.mock(ChamadaDAO.class);
		}

		@Bean
		public PresencaDAO PresencaDAO()
		{
			return Mockito.mock(PresencaDAO.class);
		}
		
		@Bean
		public TickDAO TickDAO()
		{
			return Mockito.mock(TickDAO.class);
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
	
	@Mock
	private TickDAO tickDAO;

	@InjectMocks
	private AlunoController alunoController;

	@Autowired
	private WebApplicationContext wac;

	@SuppressWarnings("deprecation")
	@Before
	public void setup()
	{
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
					.withAluno(23060, "Amaury Bosso André")
					.withAluno(aluno)
				.build();

		Chamada chamada1 = new ChamadaBuilder()
					.withDataChamada(new Date(115, 5, 10))
					.withHoraInicio(new Time(10, 0, 0))
					.withHoraFim(new Time(12, 0, 0)).withProfessor(prof1)
					.withChamadaState(ChamadaState.aberta).withTurma(turma1)
				.build();

		Presenca presenca1 = new PresencaBuilder()
					.withChamada(chamada1)
					.withAluno(aluno)
				.build();
		
		Presenca presenca2 = new PresencaBuilder()
			.withChamada(chamada1)
			.withAluno(aluno)
			.withPresencaState(PresencaState.em_aula)
		.build();					

		Mockito.when(this.turmaDAO.find(1)).thenReturn(turma1);
		Mockito.when(this.usuarioDAO.loadUsuarioByUsername("1")).thenReturn(
				aluno.getUsuario());
		Mockito.when(this.professorDAO.find(1)).thenReturn(prof1);
		Mockito.when(this.chamadaDAO.find(1)).thenReturn(chamada1);
		Mockito.when(this.chamadaDAO.findChamadaAbertaAluno(1)).thenReturn(
				chamada1);
		Mockito.when(this.presencaDAO.find(1))
				.thenReturn(presenca2);
		Mockito.when(this.presencaDAO.findPresencaChamadaAbertaAluno(1, 1))
				.thenReturn(presenca1);		
		Mockito.when(this.presencaDAO.findPresencaChamadasAluno(1))
		.thenReturn(presenca1);		
	}

	@Test()
	public void testAlunoChamada() throws Exception
	{
		Usuario user = usuarioDAO.loadUsuarioByUsername("1");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);

		mockMvc.perform(get("/aluno/chamada/turmas"))
				.andExpect(status().is(200))
				.andExpect(
						content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(
						content()
								.json("{\"idChamada\": 1, \"horaInicio\": \"10:00\", \"dataChamada\": \"10/06/2015\", \"professorChamada\":\"Eliane Martins\", "
										+ "\"turma\": { \"idTurma\": 1,\"codTurma\":A, \"codDisciplina\":\"MO409\", \"nomeDisciplina\":\"Engenharia de Software I\" } }"));
	}
	
	@Test()
	public void testAlunoPresencaChamadas() throws Exception
	{

		Usuario user = usuarioDAO.loadUsuarioByUsername("1");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);		

		this.mockMvc
				.perform(
						get("/aluno/chamada/presenca")
								.header("content-type","application/json"))
				.andExpect(status().is(200))
				.andExpect(
						content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(
						content()
								.json("{ \"idPresenca\": 1, \"horaInicio\": \"10:00\", \"numTicks\": 0,"
										+ "\"chamada\": { \"idChamada\": 1, \"dataChamada\": \"10/06/2015\", \"horaInicio\": \"10:00\", \"professorChamada\":\"Eliane Martins\" }, "
										+ "\"turma\": { \"idTurma\": 1, \"codTurma\":A, \"codDisciplina\":\"MO409\", \"nomeDisciplina\":\"Engenharia de Software I\" } }"));

	}

	@Test()
	public void testAlunoChamadaCheckin() throws Exception
	{

		Usuario user = usuarioDAO.loadUsuarioByUsername("1");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);
		
		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
		Time horaInicio = new Time(System.currentTimeMillis());

		this.mockMvc
				.perform(
						post("/aluno/chamada/checkin")
								.header("content-type","application/json")
								.content("{\"idChamada\":1}"))
				.andExpect(status().is(200))
				.andExpect(
						content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(
						content()
								.json("{ \"idPresenca\": 1, \"horaInicio\": \""+shf.format(horaInicio)+"\", \"numTicks\": 0,"
										+ "\"chamada\": { \"idChamada\": 1, \"dataChamada\": \"10/06/2015\", \"horaInicio\": \"10:00\", \"professorChamada\":\"Eliane Martins\" }, "
										+ "\"turma\": { \"idTurma\": 1, \"codTurma\":A, \"codDisciplina\":\"MO409\", \"nomeDisciplina\":\"Engenharia de Software I\" } }"));

	}

	@Test()
	public void testAlunoChamadaTick() throws Exception
	{
		Usuario user = usuarioDAO.loadUsuarioByUsername("1");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);
		
		SimpleDateFormat sdhf = new SimpleDateFormat("dd/MM/YYYY HH:mm");
		Date dataHora = new Date(System.currentTimeMillis());

		this.mockMvc
				.perform(
						post("/aluno/chamada/tick")
								.header("content-type", "application/json")
								.content("{\"idPresenca\":1}"))
				.andExpect(status().is(200))
				.andExpect(
						content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(
						content()
								.json("{ \"idTick\": 0, \"dataHora\": \""+sdhf.format(dataHora)+"\", "
										+ "\"presenca\": { \"idPresenca\": 1, \"horaInicio\": \"10:00\", \"numTicks\": 1} }"));
	}

	@Test()
	public void testAlunoChamadaCheckout() throws Exception
	{
		Usuario user = usuarioDAO.loadUsuarioByUsername("1");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);
		
		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
		Time horaFim = new Time(System.currentTimeMillis());

		this.mockMvc
				.perform(
						post("/aluno/chamada/checkout")
								.content("{\"idPresenca\":1}")
								.header("content-type", "application/json"))
				.andExpect(status().is(200))
				.andExpect(
						content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(
						content()
								.json("{ \"idPresenca\": 1, \"horaInicio\": \"10:00\", \"horaFim\": \""+shf.format(horaFim)+"\" }"));

	}

}