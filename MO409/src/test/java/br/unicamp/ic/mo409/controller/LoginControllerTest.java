package br.unicamp.ic.mo409.controller;

//import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import net.dontdrinkandroot.example.angularrestspringsecurity.rest.TokenUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.GenericXmlWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.unicamp.ic.mo409.dao.TurmaDAO;
import br.unicamp.ic.mo409.dao.UsuarioDAO;
import br.unicamp.ic.mo409.model.Usuario;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		loader=GenericXmlWebContextLoader.class,
		value={
			//"file:src/test/resources/spring-security.xml",
			"file:src/test/resources/applicationContext.xml"
		})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class LoginControllerTest {

	@Autowired
	private FilterChainProxy springSecurityFilterChain;
	
	@Mock
	private TurmaDAO turmaDAO;

	@Autowired
	private LoginController loginController;
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {

		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.loginController)
		 .addFilters(this.springSecurityFilterChain)
				.build();

	}
	
	@Test ()
	public void testUsuarioProfessorCorreto() throws Exception {
		// TODO: erro
		this.mockMvc
				.perform(
						post("/rest/user/authenticate")
								.contentType(MediaType.APPLICATION_FORM_URLENCODED)
								.param("username", "35")
								.param("password", "user"))
				.andExpect(content().string(containsString("{\"token\":")))
				.andExpect(status().is(200));
	}
	
	@Test ()
	public void testUsuarioAlunoCorreto() throws Exception {
		this.mockMvc
				.perform(
						post("/rest/user/authenticate")
								.contentType(MediaType.APPLICATION_FORM_URLENCODED)
								.param("username", "17")
								.param("password", "user"))
				.andExpect(content().string(containsString("{\"token\":")))
				.andExpect(status().is(200));
	}
	
	@Test ()
	public void testUsuarioIncorreto() throws Exception {
		this.mockMvc
				.perform(
						post("/rest/user/authenticate")
								.contentType(MediaType.APPLICATION_FORM_URLENCODED)
								.param("username", "17")
								.param("password", "senhaincorreta"))
				.andExpect(content().json("{\"error\":\"exception\", \"message\":\"Bad credentials\"}"))
				.andExpect(status().isUnauthorized());
	}
	
	@Test ()
	public void testTokenValidoProfessor() throws Exception {
		 
		UserDetails user = usuarioDAO.loadUserByUsername("35");
		String token = TokenUtils.createToken(user);
		ResultActions resultActions = this.mockMvc
				.perform(
						get("/rest/user")
								.header("X-Auth-Token", token));		 
		 
		 MvcResult mvcResult = resultActions.andReturn();
//		 String headerValue =
//		 mvcResult.getResponse().getHeader("X-Auth-Token");
//		 String content = mvcResult.getResponse().getContentAsString();
//		 System.out.println(headerValue);
//		 System.out.println(content);
//		 System.out.println(mvcResult.getResponse().toString());
//		 System.out.println(resultActions.andDo(MockMvcResultHandlers.print()));
		 
		 resultActions.andExpect(content().json("{\"name\":\"35\",\"roles\":{\"ROLE_PROFESSOR\":true}}"))
			.andExpect(status().isOk());
	}
	
	@Test(expected = AccessDeniedException.class)
	public void testTokenInvalido() throws Exception {
		 
		 ResultActions resultActions = this.mockMvc
				.perform(
						get("/rest/user")
								.header("X-Auth-Token", "35:1435929168114:6ffce54a9b3d4317cfe04c250afd1c32"));		 		 
		 
		 resultActions.andExpect(content().json("{\"error\":\"exception\", \"message\":\"token inválido\"}"))
			.andExpect(status().isUnauthorized());
	}
}
