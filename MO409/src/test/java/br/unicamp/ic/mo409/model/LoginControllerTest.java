package br.unicamp.ic.mo409.model;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.unicamp.ic.mo409.controller.LoginController;
import br.unicamp.ic.mo409.dao.TurmaDAO;
import br.unicamp.ic.mo409.dao.UsuarioDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class LoginControllerTest {

	@Mock
	private TurmaDAO turmaDAO;

	@InjectMocks
	private LoginController professorController;
	
	@InjectMocks
	private UsuarioDAO userService;

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {

		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

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
	
	
	 
    /**
     * Test the INVALID user
     * */
    @Test (expected = AccessDeniedException.class)
    public void testInvalidUser()
    {
        UserDetails userDetails = userService.loadUserByUsername ("admin");
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new GrantedAuthorityImpl("ROLE_INVALID"));
        Authentication authToken = new UsernamePasswordAuthenticationToken (userDetails.getUsername(), userDetails.getPassword(), authorities);
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

	@Test
	public void testCreateSignupFormInvalidUser() throws Exception {

		// when(turmaDAO.saveFrom(any(Aluno.class)))
		// .thenThrow(new NoResultException("For Testing"));
		
		this.mockMvc
				.perform(
						post("/rest/user/authenticate")
								.contentType(MediaType.APPLICATION_FORM_URLENCODED)
								.param("username", "35")
								.param("password", "uuuser"))
				.andExpect(content().json("{'message':'ok'}"))
				.andExpect(status().is(200));

	}
}
