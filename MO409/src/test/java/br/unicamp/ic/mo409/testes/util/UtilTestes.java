package br.unicamp.ic.mo409.testes.util;

import java.nio.charset.Charset;

import org.springframework.http.MediaType;

public class UtilTestes {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),                        
            Charset.forName("utf8")                     
            );
	
	// when(turmaDAO.saveFrom(any(Aluno.class)))
	// .thenThrow(new NoResultException("For Testing"));

	// ResultActions resultActions =
	// this.mockMvc.perform(post("/professor/chamada/abrir").contentType(MediaType.APPLICATION_JSON)
	// .content("[{ 'idTurmas':1 }, { 'idTurmas':2 } ]"));
	// ;
	// MvcResult mvcResult = resultActions.andReturn();
	// String headerValue =
	// mvcResult.getResponse().getHeader("X-Auth-Token");
	// String content = mvcResult.getResponse().getContentAsString();
	// System.out.println(headerValue);
	// System.out.println(content);
	// System.out.println(mvcResult.getResponse().toString());
	// System.out.println(resultActions.andDo(print()));
	//
	// resultActions.andExpect(status().is(400))
	// .andExpect(content().string(""))
	// .andExpect(header().string( "X-Auth-Token", notNullValue() ));

	// this.mockMvc.perform(
	// get("/professor/chamada/turmas").header("X-Auth-Token",
	// headerValue).contentType(MediaType.APPLICATION_JSON))
	// .andExpect(status().is(200));
}
