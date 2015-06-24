package br.unicamp.ic.mo409.controller;

//import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.GenericXmlWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.unicamp.ic.mo409.dao.TurmaDAO;
import br.unicamp.ic.mo409.dao.UsuarioDAO;

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

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {

		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.loginController)
		// .addFilters(this.springSecurityFilterChain)
				.build();

	}
	
	@Test ()
	public void testAuthenticateUser() throws Exception {
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
}
