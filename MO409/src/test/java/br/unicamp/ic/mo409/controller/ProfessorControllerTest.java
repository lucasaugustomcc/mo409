package br.unicamp.ic.mo409.controller;

import static org.hamcrest.core.StringContains.containsString;
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
import org.springframework.web.util.NestedServletException;

import br.unicamp.ic.mo409.dao.ChamadaDAO;
import br.unicamp.ic.mo409.dao.ProfessorDAO;
import br.unicamp.ic.mo409.dao.TurmaDAO;
import br.unicamp.ic.mo409.dao.UsuarioDAO;
import br.unicamp.ic.mo409.model.Aluno;
import br.unicamp.ic.mo409.model.Chamada;
import br.unicamp.ic.mo409.model.Professor;
import br.unicamp.ic.mo409.model.Turma;
import br.unicamp.ic.mo409.model.Usuario;
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
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class ProfessorControllerTest {	

	private MockMvc mockMvc;
	
	@Mock
	private UsuarioDAO usuarioDAO;
	
	@Configuration
	static class ProfessorControllerTestConfiguration {
		
		@Bean
		public TurmaDAO turmaDAO() {
			return Mockito.mock(TurmaDAO.class);
		}
		
		@Bean
		public UsuarioDAO usuarioDAO() {
			return Mockito.mock(UsuarioDAO.class);
		}
		
		@Bean
		public ChamadaDAO chamadaDAO() {
			return Mockito.mock(ChamadaDAO.class);
		}
	
		@Bean
		public ProfessorController professorController() {
			return new ProfessorController();
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

	@Before
	public void setup() 
	{
		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.professorController)
		// .addFilters(this.springSecurityFilterChain)
				.build();	
		
		Professor prof1 = new ProfessorBuilder().build();
		
		Professor prof2 = new ProfessorBuilder()
			.withNome("Claudia Bauzer Medeiros")
			.withRaProfessor(2)
			.withIdUsuario(2)
		.build();
						
		Turma turma1 = new TurmaBuilder()
			.withDisciplina("Engenharia de Software I", "MO409")
			.withProfessor(prof1)
			.withAluno(10798,"Daniela Marques")
		.build();	
		
		Turma turma2 = new TurmaBuilder()
			.withDisciplina("Análise e Projeto de Sistema de Informação", "MC626")
			.withProfessor(prof1)
			.withIdTurma(2)
		.build();					
		
		List<Turma> turmas = new ArrayList<Turma>();
		turmas.add(turma1);
		turmas.add(turma2);			
		
		Chamada chamada1 = new ChamadaBuilder()
			.withDataChamada(new Date(2015,05,20))
			.withHoraInicio(new Time(20,00,00))		
			.withProfessor(prof1)
			.withTurma(turma1)
		.build();
		
		Chamada chamada2 = new ChamadaBuilder()
			.withDataChamada(new Date(2015,05,20))
			.withHoraInicio(new Time(20,00,00))		
			.withProfessor(prof1)
			.withTurma(turma2)
			.withIdChamada(2)
		.build();
		
		Mockito.when(this.turmaDAO.find(1)).thenReturn(turma1);
		Mockito.when(this.turmaDAO.find(2)).thenReturn(turma1);
		Mockito.when(this.usuarioDAO.loadUsuarioByUsername("35")).thenReturn(prof1.getUsuario());
		Mockito.when(this.usuarioDAO.loadUsuarioByUsername("2")).thenReturn(prof2.getUsuario());
		Mockito.when(this.usuarioDAO.loadUsuarioByUsername("1")).thenReturn(prof1.getUsuario());
		Mockito.when(this.professorDAO.find(1)).thenReturn(prof1);
		Mockito.when(this.professorDAO.find(2)).thenReturn(prof2);
		Mockito.when(this.professorDAO.find(35)).thenReturn(prof1);
		Mockito.when(this.chamadaDAO.find(1)).thenReturn(chamada1);
		Mockito.when(this.chamadaDAO.find(2)).thenReturn(chamada2);
		Mockito.when(this.professorDAO.listTurmasByProfessor(1)).thenReturn(turmas);
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
				.perform(
						get("/professor/chamada/turmas"))
				.andExpect(status().is(200))
				.andExpect(content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(content().json("[{codDisciplina: \"MO409\", \"nomeDisciplina\": \"Engenharia de Software I\", \"codTurma\": \"A\", idTurma:1},"
						+ "{idTurma:2, codDisciplina: \"MC626\", \"nomeDisciplina\": \"Análise e Projeto de Sistema de Informação\", \"codTurma\": \"A\" }]"));
	}
	
	@Test()
	public void testAbrirChamadaProfessorCorreto() throws Exception 
	{
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.professorController).build();

		Usuario user = usuarioDAO.loadUsuarioByUsername("35");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);

		mockMvc
				.perform(
						post("/professor/chamada/abrir").content("[{ \"idTurma\":1}, {\"idTurma\":2 }]").header("content-type", "application/json"))				
				.andExpect(status().is(200))
				.andExpect(content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(content().json("[{idChamada:0},{idChamada:0}]"));
	}
	
	@Test ()
	public void testAbrirChamadaProfessorIncorreto() throws Exception 
	{
		// TODO
		Usuario user = usuarioDAO.loadUsuarioByUsername("2");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);

		this.mockMvc
				.perform(
						post("/professor/chamada/abrir").content("[{ \"idTurma\":1}, {\"idTurma\":2 }]").header("content-type", "application/json"))				
				.andExpect(status().is(200))
				.andExpect(content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(content().json("[{idChamada:0},{idChamada:0}]"));
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
						post("/professor/chamada/encerrar").content("[{ \"idChamada\":1}, {\"idChamada\":2 }]").header("content-type", "application/json"))				
				.andExpect(status().is(200))
				.andExpect(content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(content().json("[{idChamada:1},{idChamada:2}]"));
	}
	
	@Test()
	public void testEncerrarChamadaProfessorIncorreto() throws Exception 
	{
		// TODO
		Usuario user = usuarioDAO.loadUsuarioByUsername("2");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);

		this.mockMvc
				.perform(
						post("/professor/chamada/encerrar").content("[{ \"idChamada\":1}, {\"idChamada\":2 }]").header("content-type", "application/json"))				
				.andExpect(status().is(200))
				.andExpect(content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(content().json("[{idChamada:1},{idChamada:2}]"));
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
						post("/professor/chamada/relatorio").content("[{ \"idChamada\":1},{\"idChamada\":2}]").header("content-type", "application/json"))				
				.andExpect(status().is(200))
				.andExpect(content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(content().json("[{ \"idChamada\": 1, \"turma\": { \"codDisciplina\": \"MO409\", \"nomeDisciplina\": \"Engenharia de Software I\", \"idTurma\": 1, \"codTurma\": \"A\" }}, {\"idChamada\":2 }]"))
				.andExpect(content().string(containsString("\"alunos\":[{\"nomeAluno\":\"Daniela Marques\",\"raAluno\":10798}")));
	}
	
	@Test ()
	public void testRelatorioChamadaProfessorIncorreto() throws Exception 
	{
		// TODO
		Usuario user = usuarioDAO.loadUsuarioByUsername("2");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(
				authenticationToken);

		this.mockMvc
				.perform(
						post("/professor/chamada/relatorio").content("[{ \"idChamada\":1},{\"idChamada\":2}]").header("content-type", "application/json"))				
				.andExpect(status().is(200))
				.andExpect(content().contentType(UtilTestes.APPLICATION_JSON_UTF8))
				.andExpect(content().json("[{ \"idChamada\": 1, \"turma\": { \"codDisciplina\": \"MO409\", \"nomeDisciplina\": \"Engenharia de Software I\", \"idTurma\": 1, \"codTurma\": \"A\" }}, {\"idChamada\":2 }]"))
				.andExpect(content().string(containsString("\"alunos\":[{\"nomeAluno\":\"Daniela Marques\",\"raAluno\":10798}")));
	}
}