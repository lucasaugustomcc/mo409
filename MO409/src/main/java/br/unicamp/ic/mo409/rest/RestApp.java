package br.unicamp.ic.mo409.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.filter.RequestContextFilter;

import br.unicamp.ic.mo409.controller.ChamadaController;

//imports omitted for brevity 

/**
 * Registers the components to be used by the JAX-RS application
 * 
 * @author ama
 * 
 */
public class RestApp extends ResourceConfig {

	/**
	 * Register JAX-RS application components.
	 */
	public RestApp() {
		// register application resources
		register(ChamadaController.class);
		// register filters
		register(RequestContextFilter.class);
		register(CORSResponseFilter.class);
	}

}