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



	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {

		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.addFilters(this.springSecurityFilterChain)
				.build();

	}

	@Test
	public void testLoginUser() throws Exception {

		// when(turmaDAO.saveFrom(any(Aluno.class)))
		// .thenThrow(new NoResultException("For Testing"));


//		ResultActions resultActions = 
//		this.mockMvc.perform(post("/professor/chamada/abrir").contentType(MediaType.APPLICATION_JSON)
//				.content("[{ 'idTurmas':1 }, { 'idTurmas':2 } ]"));
//		;
//		MvcResult mvcResult = resultActions.andReturn();
//		String headerValue = mvcResult.getResponse().getHeader("X-Auth-Token");
//		String content = mvcResult.getResponse().getContentAsString();
//		System.out.println(headerValue);
//		System.out.println(content);
//		System.out.println(mvcResult.getResponse().toString());
//		System.out.println(resultActions.andDo(print()));
//		
//		resultActions.andExpect(status().is(400))				
//		.andExpect(content().string(""))
//		.andExpect(header().string( "X-Auth-Token", notNullValue() ));
		
//		this.mockMvc.perform(
//				get("/professor/chamada/turmas").header("X-Auth-Token",
//						headerValue).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().is(200));

	}
	
	@Test ()
	public void testAuthenticateUser() throws Exception {
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
