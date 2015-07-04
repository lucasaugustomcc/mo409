package br.unicamp.ic.mo409.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

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
import br.unicamp.ic.mo409.dao.ProfessorDAO;
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
public class ProfessorControllerTest
{

	private MockMvc mockMvc;

	@Mock
	private UsuarioDAO usuarioDAO;

	@Configuration
	static class ProfessorControllerTestConfiguration
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
	}

	@Mock
	private TurmaDAO turmaDAO;

	@Mock
	private ProfessorDAO professorDAO;

	@Mock
	private ChamadaDAO chamadaDAO;

	@InjectMocks
	private ProfessorController professorController;

	@Autowired
	private WebApplicationContext wac;

	@SuppressWarnings("deprecation")
	@Before
	public void setup()
	{
		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(this.professorController)
				// .addFilters(this.springSecurityFilterChain)
				.build();

		Professor prof1 = new ProfessorBuilder().build();

		Professor prof2 = new ProfessorBuilder()
				.withNome("Claudia Bauzer Medeiros").withRaProfessor(2)
				.withIdUsuario(2).build();

		Turma turma1 = new TurmaBuilder()
				.withDisciplina("Engenharia de Software I", "MO409")
				.withProfessor(prof1).withAluno(10798, "Daniela Marques")
				.withAluno(23060, "Amaury Bosso André").build();

		Turma turma2 = new TurmaBuilder()
				.withDisciplina("Análise e Projeto de Sistema de Informação",
						"MC626").withProfessor(prof1).withIdTurma(2).build();

		List<Turma> turmas = new ArrayList<Turma>();
		turmas.add(turma1);
		turmas.add(turma2);

		// abrir chamada
		Chamada chamada1 = new ChamadaBuilder()
				.withDataChamada(new Date(115, 05, 10))
				.withHoraInicio(new Time(10, 00, 00)).withProfessor(prof1)
				.withChamadaState(ChamadaState.atribuindo_localizacao)
				.withTurma(turma1).build();

		Chamada chamada2 = new ChamadaBuilder()
				.withDataChamada(new Date(115, 05, 10))
				.withHoraInicio(new Time(10, 00, 00)).withProfessor(prof1)
				.withChamadaState(ChamadaState.atribuindo_localizacao)
				.withTurma(turma2).withIdChamada(2).build();

		// ver relatório
		Chamada chamada3 = new ChamadaBuilder()
				.withDataChamada(new Date(115, 05, 10))
				.withHoraInicio(new Time(10, 00, 00)).withProfessor(prof1)
				.withTurma(turma1).withChamadaState(ChamadaState.encerrada)
				.withIdChamada(3).build();

		Chamada chamada4 = new ChamadaBuilder()
				.withDataChamada(new Date(115, 05, 10))
				.withHoraInicio(new Time(10, 00, 00)).withProfessor(prof1)
				.withTurma(turma2).withChamadaState(ChamadaState.encerrada)
				.withIdChamada(4).build();
		
		// atribuir parâmetros
		Chamada chamada5 = new ChamadaBuilder()
			.withDataChamada(new Date(115, 05, 10))
			.withHoraInicio(new Time(10, 00, 00)).withProfessor(prof1)
			.withTurma(turma1).withChamadaState(ChamadaState.visualizando_parametros)
			.withIdChamada(5).build();
		
		Chamada chamada6 = new ChamadaBuilder()
			.withDataChamada(new Date(115, 05, 10))
			.withHoraInicio(new Time(10, 00, 00)).withProfessor(prof1)
			.withTurma(turma2).withChamadaState(ChamadaState.visualizando_parametros)
			.withIdChamada(6).build();
		
		// encerrar chamada
		Chamada chamada7 = new ChamadaBuilder()
			.withDataChamada(new Date(115, 05, 10))
			.withHoraInicio(new Time(10, 00, 00)).withProfessor(prof1)
			.withTurma(turma1).withChamadaState(ChamadaState.aberta)
			.withIdChamada(7).build();
	
		Chamada chamada8 = new ChamadaBuilder()
			.withDataChamada(new Date(115, 05, 10))
			.withHoraInicio(new Time(10, 00, 00)).withProfessor(prof1)
			.withTurma(turma2).withChamadaState(ChamadaState.aberta)
			.withIdChamada(8).build();
		
		Aluno aluno1 = new AlunoBuilder()
			.withNome("Daniela Marques")
			.withRaAluno(10798)
			.build();
		Aluno aluno2 = new AlunoBuilder()
			.withNome("José Viana")
			.build();

		Presenca presenca1 = new PresencaBuilder().withChamada(chamada1)
				.withAluno(aluno1).build();

		Presenca presenca2 = new PresencaBuilder().withChamada(chamada1)
				.withAluno(aluno2).withPresencaState(PresencaState.em_aula)
				.build();
		
		List<Presenca> presencas1 = new ArrayList<Presenca>();
		presencas1.add(presenca1);
		
		List<Presenca> presencas2 = new ArrayList<Presenca>();
		presencas2.add(presenca2);
		
		chamada3.setPresencas(presencas1);
		chamada4.setPresencas(presencas2);
		chamada7.setPresencas(presencas1);
		chamada8.setPresencas(presencas2);

		List<Chamada> chamadas = new ArrayList<Chamada>();
		chamadas.add(chamada1);
		chamadas.add(chamada2);
		
		Mockito.when(this.turmaDAO.find(1)).thenReturn(turma1);
		Mockito.when(this.turmaDAO.find(2)).thenReturn(turma1);
		Mockito.when(this.usuarioDAO.loadUsuarioByUsername("35")).thenReturn(
				prof1.getUsuario());
		Mockito.when(this.usuarioDAO.loadUsuarioByUsername("2")).thenReturn(
				prof2.getUsuario());
		Mockito.when(this.usuarioDAO.loadUsuarioByUsername("1")).thenReturn(
				prof1.getUsuario());
		Mockito.when(this.professorDAO.find(1)).thenReturn(prof1);
		Mockito.when(this.professorDAO.find(2)).thenReturn(prof2);
		Mockito.when(this.professorDAO.find(35)).thenReturn(prof1);
		Mockito.when(this.chamadaDAO.find(1)).thenReturn(chamada1);
		Mockito.when(this.chamadaDAO.find(2)).thenReturn(chamada2);
		Mockito.when(this.chamadaDAO.find(3)).thenReturn(chamada3);
		Mockito.when(this.chamadaDAO.find(4)).thenReturn(chamada4);
		Mockito.when(this.chamadaDAO.find(5)).thenReturn(chamada5);
		Mockito.when(this.chamadaDAO.find(6)).thenReturn(chamada6);
		Mockito.when(this.chamadaDAO.find(7)).thenReturn(chamada7);
		Mockito.when(this.chamadaDAO.find(8)).thenReturn(chamada8);
		Mockito.when(this.turmaDAO.listarTurmasProfessor(1)).thenReturn(turmas);
		Mockito.when(this.chamadaDAO.listChamadasAbertasProfessor(1)).thenReturn(chamadas);
	}

	@Test()
	public void testChamadaTurmas() throws Exception
	{
		Usuario user = usuarioDAO.loadUsuarioByUsername("35");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);

		this.mockMvc
				.perform(get("/professor/chamada/turmas"))
				.andExpect(status().is(200))
				.andExpect(
						content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(
						content()
								.json("[{codDisciplina: \"MO409\", \"nomeDisciplina\": \"Engenharia de Software I\", \"codTurma\": \"A\", idTurma:1},"
										+ "{idTurma:2, codDisciplina: \"MC626\", \"nomeDisciplina\": \"Análise e Projeto de Sistema de Informação\", \"codTurma\": \"A\" }]"));
	}
	
	@Test()
	public void testChamadasAbertas() throws Exception
	{
		Usuario user = usuarioDAO.loadUsuarioByUsername("35");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);

		this.mockMvc
				.perform(get("/professor/chamada/abertas"))
				.andExpect(status().is(200))
				.andExpect(
						content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(
						content()
								.json("[{\"idChamada\": 1, \"horaInicio\": \"10:00\", \"dataChamada\": \"10/06/2015\", \"professorChamada\":\"Eliane Martins\", "
										+ "\"turma\": { \"idTurma\": 1,\"codTurma\":A, \"codDisciplina\":\"MO409\", \"nomeDisciplina\":\"Engenharia de Software I\" } },"
										+ "{\"idChamada\": 2 }]"));
	}

	@Test()
	public void testCriarChamadasProfessorCorreto() throws Exception
	{
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(
				this.professorController).build();

		Usuario user = usuarioDAO.loadUsuarioByUsername("35");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);

		mockMvc.perform(
				post("/professor/chamada/criar").content(
						"[{ \"idTurma\":1}, {\"idTurma\":2 }]").header(
						"content-type", "application/json"))
				.andExpect(status().is(200))
				.andExpect(
						content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(content().json("[{idChamada:0, "
										+ "\"turma\": { \"idTurma\": 1,\"codTurma\":A, \"codDisciplina\":\"MO409\", \"nomeDisciplina\":\"Engenharia de Software I\" } },"
										+ "{idChamada:0}]"));
	}
	
	@Test()
	public void testChamadaParametroProfessorCorreto() throws Exception
	{
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(
				this.professorController).build();

		Usuario user = usuarioDAO.loadUsuarioByUsername("35");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);

		mockMvc.perform(
				post("/professor/chamada/parametros").content(
						"{ \"duracao\":\"50\", \"porcentagem\":\"75\", \"chamadas\": [{ \"idChamada\":5}, {\"idChamada\":6 }] }").header(
						"content-type", "application/json"))
				.andExpect(status().is(200))
				.andExpect(
						content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(content().json("[{idChamada:5, \"duracao\":50, \"porcentagem\":75,"
						+ "\"turma\": { \"idTurma\": 1,\"codTurma\":A, \"codDisciplina\":\"MO409\", \"nomeDisciplina\":\"Engenharia de Software I\"}},"
						+ "{idChamada:6}]"));
	}
	
	@Test()
	public void testChamadaLocalizacaoProfessorCorreto() throws Exception
	{
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(
				this.professorController).build();

		Usuario user = usuarioDAO.loadUsuarioByUsername("35");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);

		mockMvc.perform(
				post("/professor/chamada/localizacao").content(
						"{ \"latitude\":\"-10.000\", \"longitude\":\"-10.000\", \"chamadas\": [{ \"idChamada\":1}, {\"idChamada\":2 }] }").header(
						"content-type", "application/json"))
				.andExpect(status().is(200))
				.andExpect(
						content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(content().json("[{idChamada:1, \"latitude\":\"-10.000\", \"longitude\":\"-10.000\","
						+ "\"turma\": { \"idTurma\": 1,\"codTurma\":A, \"codDisciplina\":\"MO409\", \"nomeDisciplina\":\"Engenharia de Software I\"}},"
						+ "{idChamada:2}]"));
	}
	
	@Test()
	public void testAbrirChamadasProfessorCorreto() throws Exception
	{
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(
				this.professorController).build();

		Usuario user = usuarioDAO.loadUsuarioByUsername("35");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);

		mockMvc.perform(
				post("/professor/chamada/abrir").content(
						"[{ \"idChamada\":1}, {\"idChamada\":2 }]").header(
						"content-type", "application/json"))
				.andExpect(status().is(200))
				.andExpect(
						content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(content().json("[{idChamada:1,"
						+ "\"turma\": { \"idTurma\": 1,\"codTurma\":A, \"codDisciplina\":\"MO409\", \"nomeDisciplina\":\"Engenharia de Software I\"}},"
						+ "{idChamada:2}]"));
	}

	@Test ()
	//@Test(expected = NestedServletException.class)
	public void testAbrirChamadaProfessorIncorreto() throws Exception
	{
		Usuario user = usuarioDAO.loadUsuarioByUsername("2");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);

		this.mockMvc
				.perform(
						post("/professor/chamada/abrir").content(
								"[{ \"idChamada\":1}, {\"idChamada\":2 }]").header(
								"content-type", "application/json"))
				.andExpect(status().is(409))
				.andExpect(
						content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(content().json("{ \"error\": \"exception\", \"message\":\"Professor não associado a turma.\" }"));
	}

	@Test()
	public void testEncerrarChamadaProfessorCorreto() throws Exception
	{
		Usuario user = usuarioDAO.loadUsuarioByUsername("35");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);

		this.mockMvc
				.perform(
						post("/professor/chamada/encerrar").content(
								"[{ \"idChamada\":7}, {\"idChamada\":8 }]")
								.header("content-type", "application/json"))
				.andExpect(status().is(200))
				.andExpect(
						content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(content().json("[{idChamada:7},{idChamada:8}]"));
	}

	@Test ()
	//@Test(expected = NestedServletException.class)
	public void testEncerrarChamadaProfessorIncorreto() throws Exception
	{
		Usuario user = usuarioDAO.loadUsuarioByUsername("2");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);

		this.mockMvc
				.perform(
						post("/professor/chamada/encerrar").content(
								"[{ \"idChamada\":7}, {\"idChamada\":8 }]")
								.header("content-type", "application/json"))
				.andExpect(status().is(409))
				.andExpect(
						content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(content().json("{ \"error\": \"exception\", \"message\":\"Professor não associado a turma.\" }"));
	}

	@Test()
	public void testRelatorioChamadaProfessorCorreto() throws Exception
	{
		Usuario user = usuarioDAO.loadUsuarioByUsername("35");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);

		this.mockMvc
				.perform(
						post("/professor/chamada/relatorio").content(
								"[{ \"idChamada\":3},{\"idChamada\":4}]")
								.header("content-type", "application/json"))
				.andExpect(status().is(200))
				.andExpect(
						content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(
						content()
								.json("[{ \"idChamada\": 3, \"numMinTicks\": 5,\"duracao\": 50,\"porcentagem\": 50,"
										+ "\"alunos\":[{\"nomeAluno\":\"Daniela Marques\",\"raAluno\":10798,\"presente\":false}], \"turma\": "
										+ "{ \"codDisciplina\": \"MO409\", \"nomeDisciplina\": \"Engenharia de Software I\", \"idTurma\": 1, \"codTurma\": \"A\" }}, "
									+ "{\"idChamada\":4 }]"))
				;
	}

	@Test ()	
	public void testRelatorioChamadaNaoEncerrada() throws Exception
	{
		Usuario user = usuarioDAO.loadUsuarioByUsername("35");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);

		this.mockMvc
				.perform(
						post("/professor/chamada/relatorio").content(
								"[{ \"idChamada\":1},{\"idChamada\":2}]")
								.header("content-type", "application/json"))
				.andExpect(status().is(409))
				.andExpect(
						content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(
						content()
								.json("{ \"error\": \"exception\", \"message\":\"Chamada não encerrada ainda. Resultado da presença inexistente\" }"))
				;
	}
}