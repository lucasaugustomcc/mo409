//package br.unicamp.ic.mo409.model;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
//
//import static org.hamcrest.Matchers.notNullValue;
//
//import javax.transaction.Transactional;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.security.crypto.codec.Base64;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.transaction.TransactionConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import br.unicamp.ic.mo409.controller.ProfessorController;
//import br.unicamp.ic.mo409.dao.TurmaDAO;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "file:src/test/resources/applicationContext.xml" })
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
//@Transactional
//public class ProfessorControllerTest {
//
//	@Mock
//	private TurmaDAO turmaDAO;
//
//	@InjectMocks
//	private ProfessorController professorController;
//
//	private MockMvc mockMvc;
//
//	@Before
//	public void setup() {
//
//		// Process mock annotations
//		MockitoAnnotations.initMocks(this);
//
//		// Setup Spring test in standalone mode
//		this.mockMvc = MockMvcBuilders.standaloneSetup(professorController)
//				.build();
//
//	}
//
//	@Test
//	public void testLoginUser() throws Exception {
//
//		// when(turmaDAO.saveFrom(any(Aluno.class)))
//		// .thenThrow(new NoResultException("For Testing"));
//
//		String basicDigestHeaderValue = "Basic "
//				+ new String(Base64.encode(("1:senha").getBytes()));
//		ResultActions resultActions = 
//		this.mockMvc.perform(post("/professor/chamada/abrir").contentType(MediaType.APPLICATION_JSON)
//				.content("[{ 'idTurmas':1 }, { 'idTurmas':2 } ]").header(
//				"Authorization", basicDigestHeaderValue));
//		;
//		MvcResult mvcResult = resultActions.andReturn();
//		String headerValue = mvcResult.getResponse().getHeader("X-Auth-Token");
//		String content = mvcResult.getResponse().getContentAsString();
//		System.out.println(headerValue);
//		System.out.println(basicDigestHeaderValue);
//		System.out.println(content);
//		System.out.println(mvcResult.getResponse().toString());
//		System.out.println(resultActions.andDo(print()));
//		
//		resultActions.andExpect(status().is(400))				
//		.andExpect(content().string(""))
//		.andExpect(header().string( "X-Auth-Token", notNullValue() ));
//		
////		this.mockMvc.perform(
////				get("/professor/chamada/turmas").header("X-Auth-Token",
////						headerValue).contentType(MediaType.APPLICATION_JSON))
////				.andExpect(status().is(200));
//
//	}
//
//	@Test
//	public void testCreateSignupFormInvalidUser() throws Exception {
//
//		// when(turmaDAO.saveFrom(any(Aluno.class)))
//		// .thenThrow(new NoResultException("For Testing"));
//		String basicDigestHeaderValue = "Basic "
//				+ new String(
//						Base64.encode(("<username>:<password>").getBytes()));
//		this.mockMvc
//				.perform(
//						get("/professor/chamada/turmas")
//								.header("Authorization", basicDigestHeaderValue)
//								.contentType(MediaType.APPLICATION_JSON)
//								.param("email", "mvcemail@test.com")
//								.param("firstName", "mvcfirst")
//								.param("lastName", "mvclastname"))
//				// .andExpect(content().json("{'message':'ok'}");
//				.andExpect(status().is(200));
//
//	}
//}
