package br.unicamp.ic.mo409.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory
            .getLogger(AutorizadorInterceptor.class);
	
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object controller) throws Exception {
		
		
		//logs debug message
		if(logger.isDebugEnabled()){
			logger.debug("getWelcome is executed!");
		}
		
        logger.info("Request URL::" + request.getRequestURL().toString()
                + ":: Start Time=" + System.currentTimeMillis());
        
        String uri = request.getRequestURI();
        if(uri.endsWith("loginForm") || 
            uri.endsWith("efetuaLogin") || 
            uri.endsWith("logout") || 
            uri.endsWith("criarUsuario") || 
                uri.contains("resources")){
          return true;
        }
        
		if (request.getSession().getAttribute("usuarioLogado") != null) {
			return true;
		}

		response.sendRedirect("efetuaLogin");
		return false;
	}
}
