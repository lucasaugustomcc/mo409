package br.unicamp.ic.mo409.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.stereotype.Component;

@Component
@Path("/")
public class OAuthTokenEndPoint {
	
    @GET
    @Path("class")
    @Produces("application/json")
	public Response authorize(@Context HttpServletRequest request)
			throws OAuthSystemException {

		return new Response() {
			
			@Override
			public int getStatus() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public MultivaluedMap<String, Object> getMetadata() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getEntity() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
}
